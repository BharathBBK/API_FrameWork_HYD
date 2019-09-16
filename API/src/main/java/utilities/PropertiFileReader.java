package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiFileReader {
	
	
      public static Properties propertie;
      public File file;
      public FileReader filereader;
      
      public  void setup() throws IOException {
    	  
    	  try {
    	  propertie = new Properties();
    	  file = new File(System.getProperty("user.dir") + "\\test\\config\\config.properties");
    	  filereader = new FileReader(file);
    	  propertie.load(filereader);
    	  
    	  }
    	  catch(FileNotFoundException ex){
    		  System.out.println(ex.getMessage());
    	  }
    	  catch (Exception E){
    		  System.out.println(E.getMessage());
    		  
    	  }
      }
      
      
      public static String getValue(String value){
    	  
    	  return propertie.getProperty(value);
      }
	
	
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		PropertiFileReader prop = new PropertiFileReader();
		prop.setup();
		
		System.out.println(PropertiFileReader.getValue("city"));
       }

}
