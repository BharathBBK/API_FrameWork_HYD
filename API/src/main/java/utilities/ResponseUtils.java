package utilities;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseUtils {
	
	    
	    public static Response getresponse(String uri,String baseuri){
		 RestAssured.baseURI = baseuri;
		 RequestSpecification requestSpecification = RestAssured.given() ;
		 Response response = requestSpecification.request(Method.GET, "/"+uri);
		 return response;
	} 
	    public static Response getresponsebyID(String ID,String baseuri){
			RestAssured.baseURI = baseuri;
			RequestSpecification requestSpecification = RestAssured.given() ;
			Response response = requestSpecification.request(Method.GET, "/employee/"+ID);
			return response;
		}   
		  
	    public static Response getPost(String baseuri){
	    	
	    	RestAssured.baseURI = baseuri;
	        RequestSpecification requestSpecification = RestAssured.given();
	        JSONObject json =new JSONObject();
	        json.put("name", "BBK3");
	        json.put("salary", "15");
	        json.put("age", "24");
	        requestSpecification.header("Content-type","application/json");
	        requestSpecification.body(json.toJSONString());
	        Response response = requestSpecification.request(Method.POST, "/create");
	        return response;	
	    
	    }
	    
	    public static Response updatemethod(String baseuri,String uri){
	    	 
	    	 RestAssured.baseURI = baseuri;
	    	 RequestSpecification requestSpecification = RestAssured.given();
	    	 JSONObject jobj = new JSONObject();
	    	 jobj.put("name", "BBK2");
	    	 jobj.put("salary", "15");
	    	 jobj.put("age", "24");
		     requestSpecification.header("Content-type", "application/json");
	    	 requestSpecification.body(jobj.toJSONString());
	    	 Response response = requestSpecification.request(Method.POST, "/"+uri);
	    	 
	    	 return response;
	     }
	    
	    public static Response DeleteMethod(String baseuri,String uri){
	       
	   	    RestAssured.baseURI = baseuri;
            RequestSpecification requestSpecification = RestAssured.given() ;
			Response response = requestSpecification.request(Method.DELETE, "/"+uri);
			return response;
	    }
	    
	    
	   public static void VerifyNewUserID(){
	    	 
	     Response res = ResponseUtils.getPost(PropertiFileReader.getValue("baseuri"));
	     String responseBody = res.getBody().asString();
	     System.out.println(responseBody);
	     JsonPath json = new JsonPath(responseBody);
	     System.out.println(json.get("id")); 
	     res = getresponse(PropertiFileReader.getValue("city"),PropertiFileReader.getValue("baseuri"));
	     JsonPath js= new JsonPath(res.getBody().asString());
	     ArrayList<String> ids = js.get("id");
	     for(String i : ids){
	    	if (i.contains(String.valueOf(json.get("id"))))
	    	{System.out.println("newly created id is inserted in DB>>"+i);}
	    	}
	    
	    }
	    
	    
    	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		PropertiFileReader prop = new PropertiFileReader();
		prop.setup();
		System.out.println(PropertiFileReader.getValue("city"));
		
		ResponseUtils.VerifyNewUserID();
		
     }

}
