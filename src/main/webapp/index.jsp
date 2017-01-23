<html>
	<head>
		<title>Simple Salesforce Tweet Reader</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/twitter-feed-reader.css"/>
	<!-- style="display:none" -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/twitter-feed-reader.js"></script>
	</head>
	<body>
		<div class="topContainer">
			<h1>Simple Salesforce Tweet Reader</h1>
			
				<div id="tweetBlock_template" class="tweetBlock"  style="display:none">
					<div  class="userProfileImage">
						<img id="profileImage_template" src="">
					</div>
					<div class="tweetContainer">
						<div class="userInfo">
							<div>Username: <span id="username_template" class="labelText"></span></div>
							<div>ScreenName: <span id="screenName_template" class="labelText"></span></div>
							<div>Retweet Count: <span id="retweetCount_template" class="labelText"></span> </div>
							<div><span id="tweetDate_template"></span>:</div>
							<div id="tweetText_template" class="labelText tweetText"></div>
						</div>
					</div>
				</div>
			<br/>
			<div class="searchBlock">
				<p1>Filter Tweets by keywords</p1>
				<input id="filterTextBox" class="searchComponent" type="text" onKeyUp="filterTweets()"/><input class="searchComponent" type="button" value="Clear" onClick="clearFilterText()" />
			</div>	
			<div id="twitterFeedContainer" class="twitterFeedContainer">
			</div>
		</div>
	</body>
</html>
