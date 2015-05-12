package ctrlayer;
import java.util.ArrayList;

import modellayer.*;
import dblayer.*;

public class VertexCtr {
	
	private DBVertex dbVer;

	public VertexCtr() {
		dbVer = new DBVertex();
	}

	public ArrayList<Vertex> find()
	{
		return dbVer.findAllVertex();		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
