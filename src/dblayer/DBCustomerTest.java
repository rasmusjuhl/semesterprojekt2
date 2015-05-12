package dblayer;
import modellayer.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBCustomerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
//		DBCustomer dbCus = new DBCustomer();
//		Customer cus = new Customer("Hans Hansen", "123", "hans@hansen.dk", "Sofiendalsvej 60", "9000");
//		dbCus.insertCustomer(cus);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testInsertCustomer() {
		DBCustomer dbCus = new DBCustomer();
		Customer cus = new Customer("Hans Hansen", "123", "hans@hansen.dk", "Sofiendalsvej 60", "9000");
		try {
			if(dbCus.insertCustomer(cus) == 1)
			{
				System.out.println("succes");
			}
			else
			{
				fail("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

}
