package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Broadcast;

public class ViewBroadcastDBUtil {
	
	private DataSource dataSource;
	
	public ViewBroadcastDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	@SuppressWarnings("unused")
	public List<Broadcast> getAllBroadcast() throws Exception{
		
		List<Broadcast> broadcast = new ArrayList<>();
		
		Connection conn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			if (conn != null) {
				System.out.println("Can't create a New Connection");
			} 
			else {
				// get a connection
				conn = dataSource.getConnection();
			
			}
			
			//select sql statement
			String sql = "select id,title,description,video from broadcast";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String description = myRs.getString("description");
				String video = myRs.getString("video");
				
				//create new registration object
				Broadcast tempBroadcast = new Broadcast(id, title, description, video);
				
				//add it to the list of accounts
				broadcast.add(tempBroadcast);
				
			}
			
			return broadcast;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(myConn != null) {
				myConn.close();
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
			String sql = "select title,description,video from broadcast where id=?";

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
				String video = myRs.getString("video");
				
				// use the broadcastId during construction
				theBroadcast = new Broadcast(broadcastId, title, description, video);
				
			} else {
				throw new Exception("Could not find broadcast id:" + broadcastId);
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
			String sql = "select * from broadcast where lower(Title) like ? or lower(Description) like ?";		
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
			
			//set params 
			String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";		
			myStmt.setString(1,theSearchNameLike);
			myStmt.setString(2,theSearchNameLike);	
			
			}			
			else{
				//create sql to get all broadcast records
				String sql = "select * from broadcast order by title";
				
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
				String video = myRs.getString("Video");
				
				//create new broadcast record object				
				Broadcast tempBroadcast = new Broadcast(id,title, description, video);
				
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
}
