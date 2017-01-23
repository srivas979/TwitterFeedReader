package com.srivas.twitter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	/**
	 * Utility method to return the properties object containing properties defined in a file.
	 * @param propertyFilePath String The full path to the property file.
	 * @return Properties The properties object loaded with all the properties in the file.
	 * @throws IOException
	 */
	public static Properties getPropertiesFromFilePath(String propertyFilePath) throws IOException{
		return getPropertiesFromFile(new File(propertyFilePath));
	}

	/**
	 * Utility method to return the properties object containing properties defined in a file.
	 * @param propertyFile File The file object to the property file.
	 * @return Properties The properties object loaded with all the properties in the file.
	 * @throws IOException
	 */
	public static Properties getPropertiesFromFile(File propertyFile) throws IOException{
		return getPropertiesFromInputStream(new FileInputStream(propertyFile));
	}

	/**
	 * Utility method to return the properties object containing properties defined in a file whose stream is passed in.
	 * @param propertyFileStream InputStream The stream opened on the property file.
	 * @return Properties The properties object loaded with all the properties in the file stream .
	 * @throws IOException
	 */
	public static Properties getPropertiesFromInputStream(InputStream propertyFileStream) throws IOException{
		Properties properties = new Properties();
		properties.load(propertyFileStream);
		return 	 properties;
	}

}
