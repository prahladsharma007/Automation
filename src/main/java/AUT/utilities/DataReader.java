package AUT.utilities;

import AUT.constants.CommonConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataReader {

	static Properties property;
	static FileInputStream propertyFile;
	static String url =System.getProperty("url", "https://nvs-test2.appiancloud.com/suite/sites/citadel?signin=native");
	static String env;

	public static String getValue(String key) {

		try {
			property = new Properties();
			propertyFile = new FileInputStream(CommonConstants.getDataFilePath());
		} catch (FileNotFoundException e) {
			System.out.println("Unable to locate data.properties file.");
		}
		try {
			property.load(propertyFile);
			if(url.contains("test2"))
			{
				env="test2";
			}
			else {
				env="preprod";
			}
		} catch (IOException e) {
			System.out.println("Unable to open data.properties file.");
		}
		//return property.getProperty(key);
		return property.getProperty(env +"." +key);
	}

}
