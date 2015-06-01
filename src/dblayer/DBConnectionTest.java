package dblayer;

import static org.junit.Assert.*;

import org.junit.Test;

import dblayer.DBConnection;

public class DBConnectionTest {

	@Test
	public void test() 
	{
		DBConnection dbCon = DBConnection.getInstance();
		if(dbCon.getDBcon() != null)
		{
			System.out.println("Connection to the DB is ok");
		}
		else
		{
			fail("Can't connect to the DB");
		}
	}

}
