package com.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class MultipleSpringSecurityConfig {
	
	@Configuration
	@Order(1)
	public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
	    private AccessDeniedHandler accessDeniedHandler;
		
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
			
			http.antMatcher("/admin*")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("ADMIN")
	           
	          .and()
	          .formLogin()
	          .loginPage("/loginAdmin")
	          .loginProcessingUrl("/admin_login")
	          .failureUrl("/loginAdmin?error=loginError")
	          .defaultSuccessUrl("/admin")
	           
	          .and()
	          .logout()
	          .logoutUrl("/admin_logout")
	          .logoutSuccessUrl("/")
	          .deleteCookies("JSESSIONID")
	           
	          .and()
	          .exceptionHandling()
	          .accessDeniedHandler(accessDeniedHandler)
	           
	          .and()
	          .csrf().disable();
			
		}
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	        auth.inMemoryAuthentication()
	                .withUser("admin").password("password").roles("ADMIN");
	    }
		
	}
	
	@Configuration
	@Order(2)
	public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
	    private AccessDeniedHandler accessDeniedHandler;
		
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
			
			http.antMatcher("/user*")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("USER")
	           
	          .and()
	          .formLogin()
	          .loginPage("/loginUser")
	          .loginProcessingUrl("/user_login")
	          .failureUrl("/loginUser?error=loginError")
	          .defaultSuccessUrl("/userPage")
	           
	          .and()
	          .logout()
	          .logoutUrl("/user_logout")
	          .logoutSuccessUrl("/protectedLinks")
	          .deleteCookies("JSESSIONID")
	           
	          .and()
	          .exceptionHandling()
	          .accessDeniedHandler(accessDeniedHandler)
	           
	          .and()
	          .csrf().disable();
			
		}
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	        auth.inMemoryAuthentication()
	                .withUser("user").password("password").roles("USER");
	    }
		
	}
	
	@Configuration
	@Order(3)
	public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/home", "/about").permitAll();
			
		}
		
	}

}
