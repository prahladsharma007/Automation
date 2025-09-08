package AUT.utilities;

import AUT.constants.CommonConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String getValue(String key) {

		Properties property = new Properties();
		FileInputStream propertyFile = null;


		try {
			propertyFile = new FileInputStream(CommonConstants.getConfigFilePath());
		} catch (FileNotFoundException e) {
			System.out.println("Unable to locate config.properties file.");
		}
		try {
			property.load(propertyFile);
		} catch (IOException e) {
			System.out.println("Unable to open config.properties file.");
		}
		return property.getProperty(key);
	}

}
