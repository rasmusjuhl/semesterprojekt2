package dblayer;

import modellayer.*;
import java.sql.*;

public class DBLocation {
	
	private Connection con;
	
	public DBLocation()
	{
		con = DBConnection.getInstance().getDBcon();
	}
	
	public Location findLocation(String zipCode)
	{
		String wClause = " zipCode = '" + zipCode + "'";
		return singleWhere(wClause);
	}
	
	public Location singleWhere(String wClause)
	{
		ResultSet results;
		Location locObj = new Location();
		String query = buildQuery(wClause);
		
//		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if(results.next())
			{
				locObj = buildLocation(results);
				stmt.close();
			}
			else
			{
				locObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Query exception: " + e);
		}
		return locObj;
	}
	
	private String buildQuery(String wClause)
	{
		String query = " SELECT zipCode, city FROM location";
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		return query;
	}
	
	private Location buildLocation(ResultSet results)
	{
		Location locObj = new Location();
		try
		{
			locObj.setZipCode(results.getString("zipCode"));
			locObj.setCity(results.getString("city"));
		}
		catch(Exception e)
		{
			System.out.println("Error in building the location");
		}
		return locObj;
	}
}
