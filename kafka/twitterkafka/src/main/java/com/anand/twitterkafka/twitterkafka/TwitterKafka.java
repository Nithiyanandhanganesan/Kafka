package com.anand.twitterkafka.twitterkafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

import twitter4j.*;
import twitter4j.conf.*;

public class TwitterKafka 
{
  public static void main(String[] args) throws Exception 
	 {
	    LinkedBlockingQueue<Status> queue = new LinkedBlockingQueue<Status>(1000);
	      
	      
	    String consumerKey = "";
	    String consumerSecret = "";
	    String accessToken = "";
	    String accessTokenSecret = "";
	    String topicName = "twitter_topic";
//	    String[] arguments = args.clone();
//	    String[] keyWords = Arrays.copyOfRange(arguments, 5, arguments.length);

	      ConfigurationBuilder cb = new ConfigurationBuilder();
	      cb.setDebugEnabled(true)
	         .setOAuthConsumerKey(consumerKey)
	         .setOAuthConsumerSecret(consumerSecret)
	         .setOAuthAccessToken(accessToken)
	         .setOAuthAccessTokenSecret(accessTokenSecret);

	      TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	      StatusListener listener = new StatusListener() {
	        
	       
	         public void onStatus(Status status) {      
//	            queue.offer(status);

	             System.out.println("onstatus @" + status.getRetweetCount() + status.getUser().getScreenName()  + " - " + status.getText());
	             

	            /*for(URLEntity urle : status.getURLEntities()) {
	               System.out.println(urle.getDisplayURL());
	            }*/

	            /*for(HashtagEntity hashtage : status.getHashtagEntities()) {
	               System.out.println(hashtage.getText());
	            }*/
	         }
	         
	       
	         public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	         }
	         
	         
	         public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	             System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	         }

	         
	         public void onScrubGeo(long userId, long upToStatusId) {
	             System.out.println("Got scrub_geo event userId:" + userId +  "upToStatusId:" + upToStatusId);
	         }      
	         
	         
	         public void onStallWarning(StallWarning warning) {
	            System.out.println("Got stall warning:" + warning);
	         }
	         
	         
	         public void onException(Exception ex) {
	            ex.printStackTrace();
	         }
	      };
	      twitterStream.addListener(listener);
	      
	      FilterQuery query = new FilterQuery().track("@Walmart");
	      twitterStream.filter(query);

	      Thread.sleep(5000);
}
}
