package com.srivas.twitter.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import com.srivas.twitter.dto.TweetDto;
public class TwitterUtil {
	
	private Properties properties;
	private TwitterFactory twitterFactory;
	private Twitter twitter;
	
	public TwitterUtil(Properties properties){
		this.properties = properties;
		init();
	}
	
	/**
	 * Method to perform initializations.
	 */
	public void init(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(properties.getProperty(TwitterUtil.OAUTH_CONSUMER_KEY_PROP_NAME))
		.setOAuthConsumerSecret(properties.getProperty(TwitterUtil.OAUTH_CONSUMER_SECRET_PROP_NAME))
		.setOAuthAccessToken(properties.getProperty(TwitterUtil.OAUTH_ACCESS_TOKEN_PROP_NAME))
		.setOAuthAccessTokenSecret(properties.getProperty(TwitterUtil.OAUTH_ACCESS_TOKEN_SECRET_PROP_NAME));

		twitterFactory  = new TwitterFactory(cb.build());
		twitter = twitterFactory.getInstance();   
	}
	
	
	/**
	 * Returns the most recent tweets from a the user , results are limited by the maxCount parameter. 
	 * @param twitterUser String Twitter user whose tweets are to be returned.
	 * @param maxCount int The maximum number of results to return.
	 * @return List<TweetDto> List of the Tweets
	 * @throws TwitterException
	 */
	public List<TweetDto> getLatestTweetsForUser( String twitterUser, int maxCount ) throws TwitterException{
		ArrayList<TweetDto> tweetsList = new  ArrayList<TweetDto>();
	            
	            Paging paging = new Paging(1, maxCount); //page, max count 
	            //get the latest tweets
	            List<Status> statuses = twitter.getUserTimeline(twitterUser, paging);
	            for (Status status : statuses) {
	            	
	            	User user = status.getUser();
	            	if(user == null){
	            		//this can never happen
	            		continue;
	            	}
	            	TweetDto tweet = new  TweetDto();
	            	tweet.setTweetId(status.getId());
	            	tweet.setUserName(user.getName());
	            	tweet.setUserScreenName(user.getScreenName());
	            	tweet.setPathToUserProfileImage(user.getProfileImageURL());
	            	tweet.setTweetContent(status.getText());
	            	tweet.setRetweetCount(status.getRetweetCount());
	            	
	            	SimpleDateFormat sdf = new SimpleDateFormat(TwitterUtil.TWEET_CREATION_DATE_FORMAT_STRING);
	            	String formattedDate = sdf.format(status.getCreatedAt());
	            	tweet.setCreationDateDisplayString(formattedDate);
	            	
	            	//TODO
	            	for(MediaEntity me : status.getMediaEntities()){
	            		
	            		if(TwitterUtil.MEDIA_URL_TYPE_IMAGE.equalsIgnoreCase( me.getType())){
	            			//onlyl add if Media is a picture
	            			tweet.getMediaImageUrlList().add(me.getMediaURL());
	            		}
	            	}
	            	tweetsList.add(tweet);
	            }
		
		return tweetsList;
	}
	 
	 //OAUTH Related Property Names
	 private static final String OAUTH_CONSUMER_KEY_PROP_NAME = "OAuthConsumerKey";
	 private static final String OAUTH_CONSUMER_SECRET_PROP_NAME = "OAuthConsumerSecret";
	 private static final String OAUTH_ACCESS_TOKEN_PROP_NAME = "OAuthAccessToken";
	 private static final String OAUTH_ACCESS_TOKEN_SECRET_PROP_NAME ="OAuthAccessTokenSecret";
	 
	 //Creation date format
	 private static final String TWEET_CREATION_DATE_FORMAT_STRING = "MMM dd, yyyy, hh:mm:ss a";
	 // The Media type for images
	 private static final String MEDIA_URL_TYPE_IMAGE="photo";
	 
}
