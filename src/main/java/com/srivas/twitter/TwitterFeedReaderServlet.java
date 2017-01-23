package com.srivas.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;

import com.google.gson.Gson;
import com.srivas.twitter.utils.PropertyUtil;
import com.srivas.twitter.utils.TwitterUtil;
import com.srivas.twitter.dto.TweetDto;

/**
 * Servlet implementation class FeedReaderServlet
 */
@WebServlet("/TwitterFeedReaderServlet")
public class TwitterFeedReaderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config = null;   
    private String twitterUser;
    private int maxCount;
    private String twitterPropertyFileLocation;
    private TwitterUtil twitterUtil;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterFeedReaderServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	this.config = config;
    	twitterUser = config.getInitParameter("twitterUser");
    	maxCount = Integer.valueOf(config.getInitParameter("maxTweetCount"));
    	twitterPropertyFileLocation = config.getInitParameter("twitterPropertyFilePath");
    	//twitterPropertyFileLocation
    	InputStream propFileInputStream = getServletContext().getResourceAsStream(twitterPropertyFileLocation);
    	Properties props = null;
    	try {
			props = PropertyUtil.getPropertiesFromInputStream(propFileInputStream);
		} catch (IOException ioExp) {
			throw new ServletException(ioExp);
		}
    	twitterUtil = new TwitterUtil(props);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		List<TweetDto> tweetList = null;
		String ajaxResponse;
		try {
			tweetList = twitterUtil.getLatestTweetsForUser(twitterUser, maxCount);
			ajaxResponse = gson.toJson(tweetList);
		} catch (TwitterException twitterExp) {
			HashMap<String, String> errorMap = new HashMap<String,String>();
			errorMap.put("errorCode", "1001");
			errorMap.put("errorMsg", "Error Ocurred in getting tweets, please try again later");
			ajaxResponse = gson.toJson(errorMap);
		}
		
		response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().println(ajaxResponse);
		
	}
}
