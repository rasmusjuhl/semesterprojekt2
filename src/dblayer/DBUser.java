package dblayer;
import modellayer.*;

import java.sql.*;

public class DBUser {

	private Connection con;

	public DBUser() 
	{
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * Metode til at insætte en user i DB
	 * @param user
	 * @return rc
	 * @throws Exception
	 */
	public int insertUser(User user) throws Exception
	{
		int rc = -1;
		PreparedStatement insert;		
		try
		{
			insert = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
			insert.setString(1, user.getName());
			insert.setString(2, user.getPhone());
			insert.setString(3, user.getEmail());
			insert.setString(4, user.getPassword());
			rc = insert.executeUpdate();
			insert.close();
		}
		catch(SQLException e)
		{
			System.out.println("User ikke oprettet");
			throw new Exception ("User is not inserted correct");
		}	
		return rc;		
	}

	/**
	 * Metode til at finde en User
	 * @param phone
	 * @return User
	 */
	public User findUser(String phone)
	{
		User userObj = new User();
		ResultSet rs = null;
		PreparedStatement find;
		try
		{
			find = con.prepareStatement("SELECT * FROM users WHERE phoneNo = ?");
			find.setString(1, phone);
			find.setQueryTimeout(5);
			rs = find.executeQuery();			
		}
		catch(SQLException e)
		{
			System.out.println("Query exception: " + e);
		}
		try
		{
			if(rs.next())
			{
				userObj.setName(rs.getString("name"));
				userObj.setEmail(rs.getString("email"));
				userObj.setPhone(rs.getString("phoneNo"));
				userObj.setPassword(rs.getString("password"));
			}
		}

		catch(SQLException e)
		{
			System.out.println("Query exception: " + e);
		}		
		return userObj;
	}


	public int updateUser(User user)
	{
		int rc = -1;
		PreparedStatement update;
		try
		{
			update = con.prepareStatement("UPDATE users SET name = ?, phoneNo = ?, email = ?,"
					+ " password = ?, WHERE phoneNo = ? ");
			update.setString(1, user.getName());
			update.setString(2, user.getPhone());
			update.setString(3, user.getEmail());
			update.setString(4, user.getPassword());

			update.setQueryTimeout(5);
			rc = update.executeUpdate();
			update.close();
		}

		catch(Exception e)
		{
			System.out.println("Update exception in users db: " + e);
		}
		return rc;
	}

	public int deleteUser(String phone)
	{
		int rc = -1;
		PreparedStatement delete;
		try
		{
			delete = con.prepareStatement("DELETE FROM users WHERE phoneNo = ?");
			delete.setString(1, phone);
			delete.setQueryTimeout(5);
			rc = delete.executeUpdate();
			delete.close();
		}
		catch(Exception e)
		{
			System.out.println("Delete exception in customer db: " + e);
		}
		return rc;
	}
}

