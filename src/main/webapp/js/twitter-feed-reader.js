
function loadTweets(){
	$.ajax(
			{
				type: "GET",
				url: "tfreader", 
				async: true,
				dataType: "json",
				success : function(response, textStatus, xhr){
					var tweetList = jQuery.parseJSON(xhr.responseText);
					//var tweetList = ajaxResponse;
					//TODO check for Error
					updateTweetList(tweetList);
				}
			});
	
}

function updateTweetList( tweetList ) {
	//clear all the exisitng tweets
	$("#twitterFeedContainer").find("[id^=tweetBlock_]").remove();
	//loop through the tweets
	for(i=0; i < tweetList.length; i++ ){
		tweet= tweetList[i];
		var tweetBlock = generateTweetBlock( tweet);
		//Appedn the tweets to the DOM
		$("#twitterFeedContainer").append(tweetBlock);
	}
	filterTweets(); //if there are any filter text typed in the search box then filter down the update tweet list
}

function generateTweetBlock(tweet){
	//Clone the Tweet Blocktemplate
	var newTweetBlock = $("#tweetBlock_template").clone(true);
	//set the dynamic values in the new block element
	newTweetBlock.attr("id", "tweetBlock_" + tweet.tweetId );
	//profile imaage
	var imgElem = newTweetBlock.find("img[id='profileImage_template'] ");
	imgElem.attr("id", "profileImage_" + tweet.tweetId );
	imgElem.attr("src", tweet.pathToUserProfileImage );
	
	//tweet user Name 
	var userNameElem =  newTweetBlock.find("span[id='username_template'] ");
	userNameElem.attr("id", "username_" + tweet.tweetId );
	userNameElem.text(tweet.userName);
	
	//screen Name
	var screenNameElem = newTweetBlock.find("span[id='screenName_template'] ");
	screenNameElem.attr("id", "screenName_" + tweet.tweetId );
	screenNameElem.text(tweet.userScreenName);
	
	//retweet Count
	var retweetCountElem =  newTweetBlock.find("span[id='retweetCount_template'] ");
	retweetCountElem.attr("id", "retweetCount_" + tweet.tweetId );
	retweetCountElem.text(tweet.retweetCount);
	
	//Tweet creation date
	var tweetCreationDateElem =  newTweetBlock.find("span[id='tweetDate_template'] ");
	tweetCreationDateElem.attr("id", "tweetDate_" + tweet.tweetId );
	tweetCreationDateElem.text(tweet.creationDateDisplayString);
	//tweet text
	
	var tweetText =  newTweetBlock.find("div[id='tweetText_template'] ");
	tweetText.attr("id", "tweetText_" + tweet.tweetId );
	
	var htmlEscapedTweetText = $('<div/>').text(tweet.tweetContent).html();
	//tweetText.text(htmlEscapedTweetText);
	tweetText.html(tweet.tweetContent);
	
	//make the new tweet block visible
	newTweetBlock.show();
	return newTweetBlock;
}

function filterTweets(){
	//get the filter text
	var filterText = $.trim($("#filterTextBox").val());
	var allTweetBlock = $("#twitterFeedContainer").find("[id^=tweetBlock_]");
	//iternate through all the tweets
	for(i=0; i < allTweetBlock.length; i++ ){
		//if text contains then show it otherwise hide it
		var tweetTextElem = $("#" + allTweetBlock[i].id).find("[id^=tweetText_]");
		var matchFound = tweetTextElem.text().toLowerCase().indexOf(filterText.toLowerCase());
			if ( filterText.length !=0 && matchFound == -1){
				//hide tweets not mathing the filterText
				 $("#" + allTweetBlock[i].id).hide();
			}else{
				//show tweets might have been hidden from previous search
				$("#" + allTweetBlock[i].id).show();
			}
	}
}
function clearFilterText(){
	$("#filterTextBox").val("");
	filterTweets();
}

$(document).ready(function(){
	loadTweets();//initial load
	setInterval(function(){ loadTweets(); }, 60000);
	
});

