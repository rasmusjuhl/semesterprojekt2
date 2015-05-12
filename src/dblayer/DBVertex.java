package dblayer;
import modellayer.*;

import java.sql.*;
import java.util.ArrayList;

public class DBVertex {
	
	private Connection con;

	public DBVertex() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Vertex> findAllVertex()
	{
		
		ResultSet rs = null;
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		PreparedStatement findAll;
		try 
		{
			findAll = con.prepareStatement("SELECT * FROM steps");
			findAll.setQueryTimeout(5);
			rs = findAll.executeQuery();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try
		{
			while(rs.next())
			{
				Vertex ver = new Vertex();
				ver.setFromID(rs.getString("fromID"));
				ver.setToID(rs.getString("toID"));
				ver.setDistanceTo(rs.getDouble("distance"));
				list.add(ver);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
