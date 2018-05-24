/*package com.training.auditLog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(AuditLogApplication.class)
@SpringBootTest
public class AuditLogApplicationTests {

	@Test
	public void contextLoads() {
	}

	public class AuditLogTesting {

		import static org.junit.Assert.assertEquals;
		import static org.junit.Assert.assertFalse;
		
		import java.util.List;

		import org.junit.Test;
		import org.springframework.web.client.RestTemplate;

		import com.example.model.AuditLog;
		
		RestTemplate rest1=new RestTemplate();
		
		@Test
		public void test1()
		{
			String str1="5b067938dfe0da1504d229e3";
			String url = "http://localhost:8080/auditLog/delete/"+str1;
			
			final Long lon1=rest1.getForObject(url, Long.class);
			Long lon2=new Long(0);
			assertEquals(lon1, lon2);
			
		}
		
		
		
		@Test
		public void test2()
		{
			String str2="Nothing";
			String url="http://localhost:8080/updateByeventType/"+str2;
			AuditLog aud4=rest1.getForObject(url, AuditLog.class);
			String str4="Nothing";
			assertEquals(str4, aud4.getEventType());
			
			
			
		}
		
		
		
		@Test
		public void test3()
		{
			String url="http://localhost:8080/viewAll";
			List<AuditLog>  aud=rest1.getForObject(url, List.class);
			assertFalse(aud.isEmpty());
			
		}
		
		
		@Test
		public void test4()
		{
			String str="abhi";
			String url="http://localhost:8080/viewByName/"+str;
			AuditLog aud=rest1.getForObject(url, AuditLog.class);
			assertEquals(str, aud.getEventName());
		}
	
}
*/