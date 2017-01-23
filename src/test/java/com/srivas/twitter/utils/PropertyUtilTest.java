package com.srivas.twitter.utils;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import com.srivas.twitter.utils.PropertyUtil;
import org.junit.Test;

public class PropertyUtilTest {

	@Test
	public void testGetPropertiesFromInputStream() throws IOException {
		String testProperty = "TestPropertyName=TestPropertyValue";
		ByteArrayInputStream bais = new ByteArrayInputStream(testProperty.getBytes());
		
		Properties prop =  PropertyUtil.getPropertiesFromInputStream(bais);
		assertTrue("TestPropertyValue".equals(prop.getProperty("TestPropertyName")));
	}

}
