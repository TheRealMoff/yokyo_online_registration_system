package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Results;

public class ResultsDBUtil {

	private DataSource dataSource;
	
	public ResultsDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public void addResults(Results results) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get DB Connection
			conn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into results" + "(sport,venue,time,teams_fixtured,date,results)" + "values(?,?,?,?,?,?)";
			myStmt = conn.prepareStatement(sql);
			
			//set the param values for the subscriber
			myStmt.setString(1, results.getSport());
			myStmt.setString(2, results.getVenue());
			myStmt.setString(3, results.getTime());
			myStmt.setString(4, results.getTeams_fixtured());
			myStmt.setString(5, results.getDate());
			myStmt.setString(6, results.getResults());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
			//clean up jdbc objects
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

	
	@SuppressWarnings("unused")
	public List<Results> getAllResults() throws Exception{
		
		List<Results> results = new ArrayList<>();
		
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
			String sql = "select * from results order by sport";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String sport = myRs.getString("sport");
				String venue = myRs.getString("venue");
				String time = myRs.getString("time");
				String teams_fixtured = myRs.getString("teams_fixtured");
				String date = myRs.getString("date");
				String results1 = myRs.getString("results");
				
				//create new results object
				Results tempResults = new Results(id, sport, venue, time, teams_fixtured, date, results1);
				
				//add it to the list of results
				results.add(tempResults);
				
			}
			
			return results;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
		}
	}
	
	public List<Results> searchResults(String theSearchName) throws Exception {
		
		List<Results> results = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int resultsId;
				
		try {
							
			//get connection to database
			conn = dataSource.getConnection();
					
			//only search by name if theSearchName is not empty
			if (theSearchName !=null && theSearchName.trim().length() > 0){
				
			//create sql to search for the results by sport
			String sql = "select * from results where lower(sport)like ? or lower(venue)like ?";
					
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
					
			//set params 
			String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
					
			myStmt.setString(1,theSearchNameLike);
			myStmt.setString(2,theSearchNameLike);
					
				}
					
		else
			{
			//create sql to get all schedule records
			String sql = "select * from results order by venue";
						
			//create prepared statement
			myStmt =  conn.prepareStatement(sql);
						
			}
					
			//execute statement
			myRs = myStmt.executeQuery();
					
			//retrieve data from result set row
			while(myRs.next()){
						
			//retrieve data from user schedule records result set
			int id = myRs.getInt("id");  
			String sport = myRs.getString("sport");
			String venue = myRs.getString("venue");
			String time = myRs.getString("time");
			String teams_fixtured = myRs.getString("teams_fixtured");
			String date = myRs.getString("date");
			String result = myRs.getString("results");
						
			//create new schedule record object				
			Results tempSchedule = new Results(id,sport, venue, time, teams_fixtured, date, result);
						
			//add it to the list of schedule records
			results.add(tempSchedule);
			}		
				return results;
				
			}
				
		finally {
			//clean up JDBC objects
			close(conn,myStmt, myRs);
			}	
	}
	
	//Update results information
	public void updateResults(Results theResults) throws Exception {
			
		Connection conn = null;
		PreparedStatement myStmt = null;
			
		try {
			//get connection to DB
			conn=dataSource.getConnection();
				
			//create sql update statement
				
			String sql = "Update results " + "set sport=?,venue=?,time=?,teams_fixtured=?,date=?,results=?" + "where id=?";
				
			//create a prepared statement
				
			myStmt = conn.prepareStatement(sql);
				
			//set params
				
			myStmt.setString(1, theResults.getSport());
			myStmt.setString(2, theResults.getVenue());
			myStmt.setString(3, theResults.getTime());
			myStmt.setString(4, theResults.getTeams_fixtured());
			myStmt.setString(5, theResults.getDate());
			myStmt.setString(6, theResults.getResults());
			myStmt.setInt(7, theResults.getId());
				
			//execute sql
			myStmt.execute();
			
		}
		finally {
			//clean up JDBC objects
			close(conn,myStmt,null);
		}
			
	}
	
	public void deleteResults(String theResultsId) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert the results id to int
			int resultsId = Integer.parseInt(theResultsId);
			
			//get connection to database
			conn = dataSource.getConnection();
			
			//create sql to delete schedule
			String sql = "delete from results where id=?";
					
			//prepare statement
			myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, resultsId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(conn,myStmt,null);
		}
	}
	
	public Results getResults(String theResultsId) throws Exception {
		
		Results theResults = null;
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int resultsId;
		
		try {
			//convert the resultsId into an integer
			resultsId = Integer.parseInt(theResultsId);
			
			//get DB connection 
			conn = dataSource.getConnection();
			
			//create sql statement to get selected schedule
			String sql = "Select * from results where id=?";
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
					
			//set params
			myStmt.setInt(1, resultsId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from the result set
			if (myRs.next()) {
				String sport = myRs.getString("sport");
				String venue = myRs.getString("venue");
				String time = myRs.getString("time");
				String teams_fixtured = myRs.getString("teams_fixtured");
				String date = myRs.getString("date");
				String results = myRs.getString("results");
				
				//use the schedule id during construction
				theResults = new Results(resultsId, sport, venue, time, teams_fixtured, date, results);
			}
			else {
				throw new Exception("could not find the results ID" + resultsId);
			}
		}
		finally {
			//clean up JDBC objects
			close(conn, myStmt, myRs);
		}
		
		return theResults;
	}
}
