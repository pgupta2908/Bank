package com.sboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.model.Customer;
import com.sboot.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo crepo;

	Iterable<Customer> customerList = new ArrayList<Customer>();

	/*
	 * 
	 * 
	 * */
	@Override
	public List<Customer> viewAllCustomer() {
		// TODO find all customers

		return (List<Customer>) crepo.findAll();
	}

	/*
	 * taking customer objects to add in url
	 * 
	 */
	@Override
	public String addCustomers(List<Customer> customer) {
		// TODO Auto-generated method stub
		String string = "";
		String checknull="";
		String checkid="";
		String added="";
		Optional<Customer> id = null;

		List<Customer> failedAddCustomers = new ArrayList<Customer>();

try
{
		
for (Customer c : customer) {
	
	try {
				
		id = crepo.findById(c.getCustomerId());
		if (id.isPresent()) 
			{

				c.setOperation(false);
				failedAddCustomers.add(c);
				checkid="customer exists";
			} 
		else
			{
				try {
						if (c.getCustomerId() != null && c.getCustomerAddress() != null
								&& c.getCustomerName() != null && c.getOperation() != null && c.getPaymentMode()!=null)
							{
								c.setOperation(true);
								crepo.save(c);
								added="added succesfully";
							}
					 
						else
							{
								c.setOperation(false);
								failedAddCustomers.add(c);
								checknull = " Data can not be null";
					
							}
						
					} 
				catch (Exception e) {
				string += e.getMessage()+"\n"+checkid+"\n"+checknull+"\n"+added; 	//printing data not null error message
			}
		}
	}catch(Exception e)
		{
			string += e.getMessage()+"\n"+checkid+"\n"+checknull+"\n"+added;		////printing exixting customers message
		}
}
string +="\n"+checkid+"\n"+checknull+"\n"+added;		////printing exixting customers message

}
catch(Exception e)
{
	string += e.getMessage()+"\n"+checkid+"\n"+checknull+"\n"+added;				//printing if failed to add customers
}
finally
		{
			
			if (failedAddCustomers.isEmpty()) 
			{
			
				string += "\n";
			} 
			else
			{
				int size = failedAddCustomers.size();
				int fac[] = new int[size];
				int i = 0;
				for (Customer x : failedAddCustomers) {
				fac[i] = x.getCustomerId();
				i++;
			}
			
			String facstr = Arrays.toString(fac);
			string += "\n"+facstr + " Customers can not be added!\n";			//printing message with id of customer which can not be added
			}
		}


		return string;

	}

	/*
	 * taking ids to remove
	 * 
	 */
	@Override
	public String removeCustomers(List<Integer> removelist) {
		// TODO Auto-generated method stub

		// crepo.deleteById(cid);
		String string = "";
		String checkid="";
		String removed="";
		String undelted="";
		
		List<Integer> failedRemoveCustomers = new ArrayList<Integer>();
		
		int count = removelist.size();

		Iterable<Customer> customerlist = new ArrayList<Customer>();

		customerlist = crepo.findAll();
		
		int t = 0;
		
		try
		{
			for (Integer c : removelist) {
				for (Customer cu : customerlist) {
				if (c == cu.getCustomerId()) 
				{
					
							crepo.deleteById(c);
							removed ="deleted succesfully";
							break;
					}
				else
				 {
					t++;
				
				 }
				}
				if(t==count)
				{
					failedRemoveCustomers.add(c);
					checkid="id does not exists";
					string += "\n"+checkid;
				}
			}
		
			//string += removed+"\n"+checkid;			
		}
		catch(Exception e)
		{
			string +=e.getMessage()+checkid;		//if id did not found
		}

		finally
		{
			if (failedRemoveCustomers.isEmpty()) {
				removed = " Customers removed succesfully!";
			} else {
				int size = failedRemoveCustomers.size();
				int fac[] = new int[size];
				int i = 0;
				for (Integer x : failedRemoveCustomers) {
					fac[i] = x;
					i++;
				}
			String facstr = Arrays.toString(fac);
			undelted = " failed to remove Customers!" + facstr;
			}
		}
		
		return string+"\n"+removed+"\n"+undelted;

	}

	
	
/*
 * 
 * 
 * 
 * 
 * 
 * */
	@Override
	public String updateCustomers(List<Customer> customer) {
		// TODO Auto-generated method stub

		String string = "";
		String checknull="";
		String checkid="";
		String added="";
		Optional<Customer> id = null;

		List<Customer> failedUpdateCustomers = new ArrayList<Customer>();

try
{
		
for (Customer c : customer) {
	
	try {
				
		id = crepo.findById(c.getCustomerId());
		if (id.isPresent()) 
			{
		
			
				try {
						if (c.getCustomerId() != null && c.getCustomerAddress() != null
								&& c.getCustomerName() != null && c.getOperation() != null && c.getPaymentMode()!=null)
							{
								c.setOperation(true);
								crepo.save(c);
								added="updtaed succesfully";
							}
					 
						else
							{
								c.setOperation(false);
								failedUpdateCustomers.add(c);
								checknull = " Data can not be null";
					
							}
						
					} 
				catch (Exception e) {
				string += e.getMessage()+"\n"+checkid+"\n"+checknull+"\n"+added; 	//printing data not null error message
			}
		
			} 
		else
			{


			c.setOperation(false);
			failedUpdateCustomers.add(c);
			checkid="customer does not exists";
		
			
		
		}
	}catch(Exception e)
		{
			string += e.getMessage()+"\n"+checkid+"\n"+checknull+"\n"+added;		////printing exixting customers message
		}
}
string +="\n"+checkid+"\n"+checknull+"\n"+added;		////printing exixting customers message

}
catch(Exception e)
{
	string += e.getMessage()+"\n"+checkid+"\n"+checknull+"\n"+added;				//printing if failed to updateed customers
}
finally
		{
			
			if (failedUpdateCustomers.isEmpty()) 
			{
			
				string += "\n";
			} 
			else
			{
				int size = failedUpdateCustomers.size();
				int fac[] = new int[size];
				int i = 0;
				for (Customer x : failedUpdateCustomers) {
				fac[i] = x.getCustomerId();
				i++;
			}
			
			String facstr = Arrays.toString(fac);
			string += "\n"+facstr + " Customers can not be updated!\n";			//printing message with id of customer which can not be updated
			}
		}


		return string;

	}

/*
 * 
 * find by id, view list of specific customer
 * 
 * 
 * */
public List<Customer> viewcustomerbyid(List<Integer> viewlist)
{
	String string="";
	
	Optional op;
	Object ob;
	boolean check;

	List<Optional> sendfoundcustomers=null;
	List<Optional> sendnotfoundcustomers=null;

	List<Integer> foundcustomers=null;
	List<Integer> notfoundcustomers=null;
	return null;

	
	
}


@Override
public Customer addaCustomers(Customer cust) {
	// TODO Auto-generated method stub
	
	
	
	
	return crepo.save(cust);
}


}




