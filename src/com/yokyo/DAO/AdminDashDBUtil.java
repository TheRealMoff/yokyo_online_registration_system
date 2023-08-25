package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Broadcast;

public class AdminDashDBUtil {
	
	private DataSource dataSource;
	
	public AdminDashDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public void addBroadcast(Broadcast theBroadcast) throws Exception{
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get db connection
			conn = dataSource.getConnection();
			
			//sql statement for insert
			String sql = "insert into broadcast" + "(title,description,sport,video,upload_date)" + "values(?,?,?,?,?)";
			
			myStmt = conn.prepareStatement(sql);
			
			//set param values for the broadcast record
			myStmt.setString(1, theBroadcast.getTitle());
			myStmt.setString(2, theBroadcast.getDescription());
			myStmt.setString(3, theBroadcast.getSport());
			myStmt.setString(4, theBroadcast.getVideo());
			myStmt.setString(5, theBroadcast.getUpload_date());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
			//clean up JDBC objects
			close(conn, myStmt, null);
		}
		
	}

	private void close(Connection conn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(conn != null) {
				conn.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myRs != null) {
				myRs.close();
			}
		}
		catch(Exception exec) {
			exec.printStackTrace();
		}
		
	}
	
	
	@SuppressWarnings("unused")
	public List<Broadcast> getBroadcast() throws Exception {
		
		List<Broadcast> broadcast = new ArrayList<>();
		
		Connection conn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			if(conn != null) {
				System.out.println("Cannot create connection");
			}
			else {
				//get a connection
				conn = dataSource.getConnection();
			}
			
			//select sql statement
			String sql = "Select * from broadcast order by title";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String title = myRs.getString("Title");
				String description = myRs.getString("Description");
				String sport = myRs.getString("Sport");
				String video = myRs.getString("Video");
				String upload_date = myRs.getString("Upload_date");
				
				// use the broadcastId during construction
				Broadcast tempBroadcast = new Broadcast(id, title, description, sport, video, upload_date);
				
				//add it to the list of broadcast
				broadcast.add(tempBroadcast);
				
			}
			
			return broadcast;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
			}
		}
	

	@SuppressWarnings("unused")
	public List<Broadcast> getBroadcast1() throws Exception {
		
		List<Broadcast> broadcast = new ArrayList<>();
		
		Connection conn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			if(conn != null) {
				System.out.println("Cannot create connection");
			}
			else {
				//get a connection
				conn = dataSource.getConnection();
			}
			
			//select sql statement
			String sql = "Select Title, Description, Sport, Video";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String title = myRs.getString("Title");
				String description = myRs.getString("Description");
				String sport = myRs.getString("Sport");
				String video = myRs.getString("Video");
				String upload_date = myRs.getString("upload_date");
				
				// use the broadcastId during construction
				Broadcast tempBroadcasts = new Broadcast(id, title, description, sport, video, upload_date);
				
				//add it to the list of broadcast
				broadcast.add(tempBroadcasts);
				
			}
			
			return broadcast;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
			}
		}
	
	public Broadcast getBroadcast(String theBroadcastId) throws Exception {
		Broadcast theBroadcast = null;

		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int broadcastId;

		try {
			// convert broadcast id to int
			broadcastId = Integer.parseInt(theBroadcastId);

			// get connection to database
			conn = dataSource.getConnection();

			// create sql to get the selected broadcast record
			String sql = "select * from broadcast where id=?";

			// create prepared statement
			myStmt = conn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, broadcastId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {

				String title = myRs.getString("Title");
				String description = myRs.getString("Description");
				String sport = myRs.getString("Sport");
				String video = myRs.getString("Video");
				String upload_date = myRs.getString("Upload_date");
				
				// use the broadcastId during construction
				theBroadcast = new Broadcast(broadcastId,title, description, sport, video, upload_date);
			} else {
				throw new Exception("Could not find mail id:" + broadcastId);
			}
			return theBroadcast;
			
		} finally {
			// clean up JDBC objects
			close(conn, myStmt, myRs);
		}
	}
	
	public Broadcast getBroadcast1(String theBroadcastId) throws Exception {
		Broadcast theBroadcast = null;

		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int broadcastId;

		try {
			// convert broadcast id to int
			broadcastId = Integer.parseInt(theBroadcastId);

			// get connection to database
			conn = dataSource.getConnection();

			// create sql to get the selected broadcast record
			String sql = "select * from broadcast where id=?";

			// create prepared statement
			myStmt = conn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, broadcastId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {

				String title = myRs.getString("title");
				String description = myRs.getString("description");
				String sport = myRs.getString("sport");
				String video = myRs.getString("video");
				String upload_date = myRs.getString("upload_date");
				
				// use the broadcastId during construction
				theBroadcast = new Broadcast(broadcastId,title, description, sport,video, upload_date);
			} else {
				throw new Exception("Could not find mail id:" + broadcastId);
			}
			return theBroadcast;
			
		} finally {
			// clean up JDBC objects
			close(conn, myStmt, myRs);
		}
	}
	
	@SuppressWarnings("unused")
	public List<Broadcast> searchBroadcast(String theSearchName)throws Exception{
		
		List<Broadcast> broadcast = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int broadcastId;
		
		try {			
			//get connection to database
			conn = dataSource.getConnection();	
			
			//only search by name if theSearchName is not empty
			if (theSearchName !=null && theSearchName.trim().length() > 0){
				
			//create sql to search for the Broadcast by sport code
			String sql = "select * from broadcast where lower(Sport) like ? or lower(Title) like ?";		
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
			
			//set params 
			String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";		
			myStmt.setString(1,theSearchNameLike);
			myStmt.setString(2,theSearchNameLike);	
			
			}			
			else{
				//create sql to get all broadcast records
				String sql = "select * from broadcast order by Sport";
				
				//create prepared statement
				myStmt =  conn.prepareStatement(sql);	
			}
			
			//execute statement
			myRs = myStmt.executeQuery();	
			
			//retrieve data from result set row
			while(myRs.next()){
				//retrieve data from broadcast records result set				
				int id = myRs.getInt("id");  
				String title = myRs.getString("Title");
				String description = myRs.getString("Description");
				String sport = myRs.getString("Sport");
				String video = myRs.getString("Video");
				String upload_date = myRs.getString("Upload_date");
				
				//create new broadcast record object				
				Broadcast tempBroadcast = new Broadcast(id,title, description, sport, video, upload_date);
				
				//add it to the list of broadcast records
				broadcast.add(tempBroadcast);
			}
			
			return broadcast;
		}
		
		finally {
			//clean up JDBC objects
			close(conn,myStmt, myRs);
		}
	}
	
	public void deleteBroadcast(String theBroadcastId) throws Exception
	{
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert the broadcast id to int
			int broadcastId = Integer.parseInt(theBroadcastId);
			
			//get connection to database
			conn = dataSource.getConnection();
			
			//create sql to delete broadcast
			String sql = "delete from broadcast where id=?";
					
			//prepare statement
					myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, broadcastId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(conn,myStmt,null);
		}
	}
	
	public void updateBroadcast(Broadcast theBroadcast) throws Exception
	
	{
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db connection
			conn = dataSource.getConnection();
			
			//create sql update statement
			String sql = "update broadcast set Title=?, Description=?, Sport=?, Video=?, Upload_date=? where id=?";
			
			//prepare statement 
			myStmt = conn.prepareStatement(sql);

			//set the param values for the broadcast record
			myStmt.setString(1, theBroadcast.getTitle());
			myStmt.setString(2, theBroadcast.getDescription());
			myStmt.setString(3, theBroadcast.getSport());
			myStmt.setString(4, theBroadcast.getVideo());
			myStmt.setString(5, theBroadcast.getUpload_date());			
			myStmt.setInt(6, theBroadcast.getId());
			
			//execute SQL statement
			myStmt.execute();
			
		}
		finally {
			//clean up JDBC objects
			close(conn,myStmt,null);
				}
		}
}