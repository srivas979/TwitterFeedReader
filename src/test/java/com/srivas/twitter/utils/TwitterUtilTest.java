package com.srivas.twitter.utils;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

public class TwitterUtilTest {

	@Test
	public void testGetLatestTweetsForUser() throws Exception{
		Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("./src/main/webapp/WEB-INF/properties/twitter.properties");
			prop.load(fis);
			TwitterUtil twitterUtil = new TwitterUtil(prop);
			assertEquals(1, twitterUtil.getLatestTweetsForUser("salesforce", 1).size() );
	}
}
