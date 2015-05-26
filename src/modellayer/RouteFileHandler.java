package modellayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Filehandler til Route, så det er muligt at gemme og hente ruter.
 * @author Kasper
 */
public class RouteFileHandler 
{
		private String fileName;
		
		public RouteFileHandler(String fileName)
		{
			this.fileName = fileName;
		}
		
		public void saveToFileRoute(ArrayList<Route> routes) throws IOException
		{
			File destination = makeAbsoluteFilename(fileName);
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destination));
			os.writeObject(routes);
			os.close();
		}
		
		public ArrayList<Route> readFromFileRoute() throws IOException, ClassNotFoundException
		{
			ArrayList<Route> returnList = new ArrayList<Route>();
			// Make sure the file can be found.
			URL resource = getClass().getResource(fileName);
			if(resource == null)
			{
				throw new FileNotFoundException(fileName);
			}
			try
			{
				File source = new File(resource.toURI());
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
				returnList = (ArrayList<Route>) is.readObject();
				is.close();
			}
			catch(URISyntaxException e)
			{
				throw new IOException("Unable to make a valid file for " + fileName);
			}
			return returnList;
		}
		
		private File makeAbsoluteFilename(String filename) throws IOException
		{
			try
			{
				File file = new File(filename);
				if(!file.isAbsolute())
				{
					file = new File(getProjectFolder(), filename);
				}
				return file;
			}
			catch(URISyntaxException e)
			{
				throw new IOException("Unable to make a valid filename for " + filename);
			}
		}
		
		private File getProjectFolder() throws URISyntaxException
		{
			String file = getClass().getName();
			String myClassFile = file.substring(11) + ".class";
			URL url = getClass().getResource(myClassFile);
			return new File(url.toURI()).getParentFile();
		}
}
