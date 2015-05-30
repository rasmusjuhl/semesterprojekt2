package dblayer;
import modellayer.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBCustomerTest {

	@Test
	public void testInsertCustomer() throws Exception {
		DBCustomer dbCus = new DBCustomer();
		Customer cus = new Customer("Hans Hansen", "123", "hans@hansen.dk", "Sofiendalsvej 60", "9000");
			if(dbCus.insertCustomer(cus) == 1)
			{
				System.out.println("insert customer succes");
			}
			else
			{
				fail("fail");
			}
			dbCus.deleteCustomer("123");
			
	}

	@Test
	public void testFindCustomer() {
		DBCustomer dbCus = new DBCustomer();
			if(dbCus.findCustomer("1") != null)
			{
				System.out.println("find customer succes");
			}
			else
			{
				fail("fail");
			}
			
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		DBCustomer dbCus = new DBCustomer();
		Customer cus = new Customer("Hans Hansen", "123", "hans@hansen.dk", "Sofiendalsvej 60", "9000");
		dbCus.insertCustomer(cus);
			if(dbCus.deleteCustomer("123") == 1)
			{
				System.out.println("delete customer succes");
			}
			else
			{
				fail("fail");
			}
	}

}
