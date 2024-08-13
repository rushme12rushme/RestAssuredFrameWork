package com.altius.crm.dbutility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterClass;

import com.altius.crm.fileutility.FileUtility;
import com.mysql.jdbc.Driver;

public class DbUtilityClass {
	FileUtility fl=new FileUtility();
	public static Connection conn;
	public static Statement stat;
	
	public void getDbConnection() throws SQLException, IOException
	{
		Driver  driver=new Driver();
		DriverManager.registerDriver(driver);
		String url=fl.getDataFromPropFile("DBURL");
		String un=fl.getDataFromPropFile("DBUSERNAME");
		String pwd=fl.getDataFromPropFile("DBPWD");

		conn=DriverManager.getConnection(url,un,pwd);
		
		
	}
	@AfterClass
	
	public void closeDbConnection() throws SQLException
	{
		conn.close();
	}
	
	public boolean isDataPresentInDB(String query,String expData,int colIndex) throws SQLException, IOException
	{
		getDbConnection();
		stat=conn.createStatement();
		ResultSet result=stat.executeQuery(query);
		boolean flag=false;
		while(result.next())
		{
			if(result.getString(colIndex).equals(expData))
			{
				flag=true;
			}
		}
		
		return flag;
		
	}

}
