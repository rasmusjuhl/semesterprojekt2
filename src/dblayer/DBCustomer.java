package dblayer;
import modellayer.*;

import java.sql.*;
import java.util.ArrayList;

public class DBCustomer {

	private Connection con;

	public DBCustomer() 
	{
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * Metode til at indsætte en customer i DB
	 * @param cus
	 * @return rc
	 * @throws Exception
	 */
	public int insertCustomer(Customer cus) throws Exception
	{
		int rc = -1;
		PreparedStatement insert;		
		try
		{
			insert = con.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?)");
			insert.setString(1, cus.getName());
			insert.setString(2, cus.getAddress());
			insert.setString(3, cus.getZipCode());
			insert.setString(4, cus.getPhone());
			insert.setString(5, cus.getEmail());
			rc = insert.executeUpdate();
			insert.close();
		}
		catch(SQLException e)
		{
			System.out.println("Customer ikke oprettet");
			throw new Exception ("Customer is not inserted correct");
		}	
		return rc;		
	}

	/**
	 * Metode til at finde en Customer
	 * @param phone
	 * @return Customer
	 */
	public Customer findCustomer(String phone)
	{
		Customer cusObj = new Customer();
		ResultSet rs = null;
		PreparedStatement find;
		try
		{
			find = con.prepareStatement("SELECT * FROM customer WHERE phoneNo = ?");
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
				cusObj.setName(rs.getString("name"));
				cusObj.setAddress(rs.getString("address"));
				cusObj.setEmail(rs.getString("email"));
				cusObj.setPhone(rs.getString("phoneNo"));

				String zip = rs.getString("zipCode");
				cusObj.setZipCode(zip);	
				cusObj.setLocation(new DBLocation().findLocation(zip));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Query exception: " + e);
		}		
		return cusObj;
	}
	
	//find all customers
	public ArrayList<Customer> getAllCustomers()
	{		
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		PreparedStatement findAll;
		try 
		{
			findAll = con.prepareStatement("SELECT * FROM customer");
			findAll.setQueryTimeout(5);
			rs = findAll.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				Customer cusObj = new Customer();
				cusObj.setName(rs.getString("name"));
				cusObj.setAddress(rs.getString("address"));
				cusObj.setEmail(rs.getString("email"));
				cusObj.setPhone(rs.getString("phoneNo"));

				String zip = rs.getString("zipCode");
				cusObj.setZipCode(zip);	
				cusObj.setLocation(new DBLocation().findLocation(zip));
				list.add(cusObj);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;		
	}
	

	public int updateCustomer(Customer cus)
	{
		int rc = -1;
		PreparedStatement update;
		try
		{
			update = con.prepareStatement("UPDATE customer SET name = ?, address = ?, zipCode = ?,"
					+ " phoneNo = ?, email = ? WHERE phoneNo = ? ");
			update.setString(1, cus.getName());
			update.setString(2, cus.getAddress());
			update.setString(3, cus.getZipCode());
			update.setString(4, cus.getPhone());
			update.setString(5, cus.getEmail());
			update.setString(6, cus.getPhone());

			update.setQueryTimeout(5);
			rc = update.executeUpdate();
			update.close();
		}
		catch(Exception e)
		{
			System.out.println("Update exception in customer db: " + e);
		}
		return rc;
	}

	public int deleteCustomer(String phone)
	{
		int rc = -1;
		PreparedStatement delete;
		try
		{
			delete = con.prepareStatement("DELETE FROM customer WHERE phoneNo = ?");
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
