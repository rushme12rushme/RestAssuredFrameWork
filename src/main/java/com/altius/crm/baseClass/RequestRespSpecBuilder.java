package com.altius.crm.baseClass;

import java.io.IOException;

import org.testng.annotations.BeforeClass;

import com.altius.crm.fileutility.FileUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestRespSpecBuilder {
	
	 public static RequestSpecification reqSpec;
	 public static ResponseSpecification respSpec;
	 FileUtility futil=new FileUtility();
	 
@BeforeClass	 
	public  void requestResponseSpecBuilder() throws IOException
	{
		/*Build common elements in request*/
	RequestSpecBuilder builder=new RequestSpecBuilder();
	builder.setContentType(ContentType.JSON);
	builder.setBaseUri(futil.getDataFromPropFile("BASEURL"));
	reqSpec =builder.build();
	
	/*Build common elements in response*/
	
		ResponseSpecBuilder respBuilder=new ResponseSpecBuilder();
		respBuilder.expectContentType(ContentType.JSON);
		respSpec=respBuilder.build();
	 
	}

}
