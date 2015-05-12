package ctrlayer;

import modellayer.*;
import dblayer.*;

public class UserCtr {
	
	private DBUser dbUser;

	public UserCtr() 
	{
		dbUser = new DBUser();
	}

	public void insertNewUser(String name, String phone, String email, String password) throws Exception
	{
		User userObj = new User(name, phone, email, password);
		try
		{
			DBConnection.startTransaction();
			dbUser.insertUser(userObj);
			DBConnection.commitTransaction();	
		}
		catch(Exception e)
		{
			DBConnection.rollbackTransaction();
			throw new Exception("User not inserted");
		}
	}
	
	public User findUser(String phoneNo)
	{
		return dbUser.findUser(phoneNo);
	}
	
	public int updateUser(String name, String phone, String email, String password)
	{
		User user = new User(name, phone, email, password);
		return dbUser.updateUser(user);				
	}
	
	public int deleteUser(String phoneNo)
	{
		return dbUser.deleteUser(phoneNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
