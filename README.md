# TwitterFeedReader
Hi,                                
     This is a java application which connects to twitter and gets the 10 most recent tweets from salesforce.
 The List refreshes every one minute. In order for the application to work the twitter.properties  
file located at TwitterFeedReader\src\main\webapp\WEB-INF\properties\twitter.properties would need to
be edited to provide valid OAuth Properties.
	This application makes use of twitter4j library on the server side to retrieve saleforce tweets from Twitter.
The application uses a servlet to connect which calls twitter4j api's and returns back the tweets in json format.
The eclipse project for this application is checked in. The application can be launched running on a standalone jetty
embedded server by running :

mvn clean compile jetty:run

The application would be accessible on http://localhost:8080/
The response in json of the twitter feeds can be seen at http://localhost:8080/tfreader

The basic configuration for the web application like the twitter account to get tweets from and the number 
of tweets to get are configured as config parameters in the web.xml file.

Thanks.
Abhinav
