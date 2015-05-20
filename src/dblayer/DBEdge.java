package dblayer;
import modellayer.*;

import java.sql.*;
import java.util.ArrayList;

public class DBEdge {
	
	private Connection con;
	
	
	public DBEdge() {
		con = DBConnection.getInstance().getDBcon();		
	}

	public Edge findEdge(Customer a, Customer b)
	{
		Edge edge = new Edge();
		ResultSet rs = null;
		PreparedStatement find;
		try
		{
			find = con.prepareStatement("SELECT * FROM edges WHERE fromID = ? OR fromID = ? AND toID = ? OR toID = ?");
			find.setString(1, a.getPhone());
			find.setString(2, b.getPhone());
			find.setString(3, a.getPhone());
			find.setString(4, b.getPhone());
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
				edge.setPointA(a);
				edge.setPointB(b);
				edge.setDistance(rs.getDouble("distance"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Query exception: " + e);
		}		
		return edge;		
	}
	
	public ArrayList<Edge> findAllEdges(Customer cus)
	{
		ResultSet rs = null;
		ArrayList<Edge> list = new ArrayList<Edge>();
		PreparedStatement findAll;
		try 
		{
			findAll = con.prepareStatement("SELECT * FROM edges WHERE fromID = ? OR toID = ?");
			findAll.setString(1, cus.getPhone());
			findAll.setString(2, cus.getPhone());
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
				
				DBCustomer dbCus = new DBCustomer();
				Customer a = dbCus.findCustomer(rs.getString("fromID"));
				Customer b = dbCus.findCustomer(rs.getString("toID"));
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
