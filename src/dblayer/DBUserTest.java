package dblayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modellayer.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBUserTest {

	@Test
	public void testInsertUser() throws Exception {
		DBUser dbu = new DBUser();
		User user = new User("Hans Hansen", "123", "hans@hansen.dk", "123");
		if(dbu.insertUser(user) == 1)
		{
			System.out.println("insert user succes");
		}
		else
		{
		fail("Not yet implemented");
		}
		dbu.deleteUser("123");
	}

	@Test
	public void testFindUser() {
		DBUser dbu = new DBUser();
		if(dbu.findUser("201") != null)
		{
			System.out.println("find user succes");
		}
		else
		{
		fail("Not yet implemented");
		}
	}

	@Test
	public void testGetAllUsers() { 
		DBUser dbu = new DBUser();
		if(dbu.getAllUsers().size() == 3)
		{
			System.out.println("findall users succes");
		}
		else
		{
		fail("Not yet implemented");
		}
	}

	@Test
	public void testDeleteUser() throws Exception {
		DBUser dbu = new DBUser();
		User user = new User("Hans Hansen", "123", "hans@hansen.dk", "123");
		dbu.insertUser(user);
		if(dbu.deleteUser("123") == 1)
		{
			System.out.println("delete user succes");
		}
		else
		{
		fail("Not yet implemented");
		}
	}

}
