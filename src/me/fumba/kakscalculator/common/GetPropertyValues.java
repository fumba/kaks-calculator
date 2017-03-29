package me.fumba.kakscalculator.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

	public String getPropValues(String key) throws IOException {
		Properties prop = new Properties();
		String propFileName = "error.properties";
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}
		return prop.getProperty(key);
	}
}
