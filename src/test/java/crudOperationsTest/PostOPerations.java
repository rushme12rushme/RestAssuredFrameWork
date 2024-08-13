package crudOperationsTest;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.altius.crm.dbutility.DbUtilityClass;
import com.altius.crm.pojoclassess.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PostOPerations {

	@Test
	public void addSingleProjectWithCreated() throws SQLException, IOException
	{
		
		Random r =new Random();
		int random=r.nextInt(10000);
		String expProjName="NewProj"+random+"";
		String expMsg="Successfully Added";
		ProjectPojo pobj=new ProjectPojo("deepak"+random+"",""+expProjName+"","ongoing",0);
		System.out.println(expProjName);
		
		Response resp=given()
	.contentType(ContentType.JSON)
	.body(pobj)
	.when()
	.post("http://49.249.28.218:8091/addProject");
	resp.then()
	.assertThat().statusCode(201)
	.contentType(ContentType.JSON)
	.assertThat().time(Matchers.lessThan(3000l))
	.assertThat().body("msg", Matchers.equalTo(expMsg))
	.assertThat().body("projectName", Matchers.equalTo(expProjName));
	
	/*Validate in Database*/
	
	DbUtilityClass dbutil=new DbUtilityClass();
	
	String query="select * from project";
	boolean result=dbutil.isDataPresentInDB(query, expProjName, 4);
	Assert.assertTrue(result);
	
	}
}
