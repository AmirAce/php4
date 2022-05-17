/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author aamir
 */
public class TweetModel {
     
    public static ArrayList<Tweet> getTweets(){
         ArrayList<Tweet> tweets = new ArrayList<>();
        
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet results = statement.executeQuery("select * from tweet");
            
            while ( results.next() ){
                int id = results.getInt("id");
                String text = results.getString("text");
                int user_id = results.getInt("user_id");
                Timestamp timestamp = results.getTimestamp("timestamp");
                int likes = results.getInt("likes");
                
                
                Tweet tweet = new Tweet(id, text, timestamp, user_id, likes );
                
                tweets.add(tweet);
            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return tweets;
        
    }
    public static ArrayList<Tweet> getDailyFeed(int user_id){
        ArrayList<Tweet> dailyfeed = new ArrayList<>();
        
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet results = statement.executeQuery("select * from tweet");
//            Tweet tweet = null;
//            while ( results.next() ){
//                int id = results.getInt("id");
//                String text = results.getString("text");
//                int user_id = results.getInt("user_id");
//                Timestamp timestamp = results.getTimestamp("timestamp");
//                int likes = results.getInt("likes");
//                tweet = new Tweet(id, text, timestamp, user_id, likes );
//               
//                
//                
//               
//            }
            
            ArrayList<Following> followers = UserModel.getFollowers(user_id);
            ArrayList<Integer> followid = new ArrayList<>();
            for(Following f : followers){
                followid.add(f.getFollowing_user_id());
            }
            
            ArrayList<Tweet> tweets = TweetModel.getTweets();
//            for(Tweet t : tweets){
                for(Tweet t : tweets){
                   
            if(followid.contains(t.getUser_id())){
                dailyfeed.add(t);
            }
                }
    
//                for(int i = 0; i < followers.size();i++){
//                    followers.add(UserModel.getFollowers(i));
//                    Following f = followers.get(i);
//                    if(tweet_user_id == f.getFollowed_by_user_id() && !dailyfeed.contains(t)){
//                        
//                    dailyfeed.add(t);
                       
                
                
            
                
               
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return dailyfeed;
        
    }
     public static Tweet getTweet(int id){
        Tweet tweet = null;
        try{
            
            Connection connection = DatabaseConnection.getConnection();
            
             String query = "select id, text,TimeStamp,user_id,Likes from tweet "
                    + " where id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet results = statement.executeQuery();
            
            while ( results.next() ){
//                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                int user_id = results.getInt("user_id");
                int likes = results.getInt("likes");
                
               tweet = new Tweet(id, text, timestamp, user_id,likes);
                
            }
            
            results.close();
            statement.close();
            connection.close();

        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return tweet;
    }
    
        public static void createTweet(Tweet tweet){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "insert into tweet ( text, user_id ) "
                    + " values ( ?,? )";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1, why? it's ok to cry
            statement.setString(1, tweet.getText());
            statement.setInt(2, tweet.getUser_id());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
        //create method for sending likes to db
        
    
}
