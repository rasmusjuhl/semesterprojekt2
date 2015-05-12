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
		DBCustomer dbCus = new DBCustomer();
		Customer cus = new Customer("Hans Hansen", "123", "hans@hansen.dk", "Sofiendalsvej 60", "9000");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertCustomer() {
		fail("Not yet implemented");
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
