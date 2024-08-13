package com.altius.crm.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./configData.properties");
		Properties p=new Properties();
		p.load(fis);
		String data=p.getProperty(key);
		return data;
	}

}
