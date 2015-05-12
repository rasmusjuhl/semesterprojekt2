package ctrlayer;
import java.util.ArrayList;

import modellayer.*;
import dblayer.*;

public class CustomerCtr {

	private DBCustomer dbCus;
	
	public CustomerCtr()
	{
		dbCus = new DBCustomer();
	}

	public void insertNewCustomer(String name, String phone, String email, String address, String zipCode) throws Exception
	{
		Customer cusObj = new Customer(name, phone,  email,  address,  zipCode);
		try
		{
			DBConnection.startTransaction();
			dbCus.insertCustomer(cusObj);
			DBConnection.commitTransaction();			
		}
		catch(Exception e)
		{
			DBConnection.rollbackTransaction();
			throw new Exception("Customer not inserted");
		}
	}
	
	//find a customer
	public Customer findByPhoneNo(String phoneNo)
	{
		return dbCus.findCustomer(phoneNo);		
	}
	
	//find all customers
	public ArrayList<Customer> findAllCustomers()
	{
		return dbCus.getAllCustomers();
	}
	
	public int updateCustomer(String name, String phone, String email, String address, String zipCode)
	{
		Customer cus = new Customer(name, phone, email, address, zipCode);
		return dbCus.updateCustomer(cus);		
	}
	
	public int deleteCustomer(String phoneNo)
	{
		return dbCus.deleteCustomer(phoneNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
