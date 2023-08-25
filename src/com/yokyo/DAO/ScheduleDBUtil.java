package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Schedule;

public class ScheduleDBUtil {
	
	private DataSource dataSource;
	
	public ScheduleDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public void addSchedule(Schedule schedule) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get DB Connection
			conn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into schedule" + "(sport,venue,time,teams_fixtured,date)" + "values(?,?,?,?,?)";
			myStmt = conn.prepareStatement(sql);
			
			//set the param values for the subscriber
			myStmt.setString(1, schedule.getSport());
			myStmt.setString(2, schedule.getVenue());
			myStmt.setString(3, schedule.getTime());
			myStmt.setString(4, schedule.getTeams_fixtured());
			myStmt.setString(5, schedule.getDate());
			
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
	public List<Schedule> getAllSchedule() throws Exception{
		
		List<Schedule> schedule = new ArrayList<>();
		
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
			String sql = "select * from schedule order by sport";
			
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
				
				//create new schedule object
				Schedule tempSchedule = new Schedule(id, sport, venue, time, teams_fixtured, date);
				
				//add it to the list of schedules
				schedule.add(tempSchedule);
				
			}
			
			return schedule;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
		}
	}
	
	public List<Schedule> searchSchedule(String theSearchName) throws Exception {
		
		List<Schedule> schedule = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int scheduleId;
				
		try {
							
			//get connection to database
			conn = dataSource.getConnection();
					
			//only search by name if theSearchName is not empty
			if (theSearchName !=null && theSearchName.trim().length() > 0){
				
			//create sql to search for the schedule by sport
			String sql = "select * from schedule where lower(sport)like ? or lower(venue)like ?";
					
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
			String sql = "select * from schedule order by venue";
						
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
						
			//create new schedule record object				
			Schedule tempSchedule = new Schedule(id,sport, venue, time, teams_fixtured, date);
						
			//add it to the list of schedule records
			schedule.add(tempSchedule);
			}		
				return schedule;
				
			}
				
		finally {
			//clean up JDBC objects
			close(conn,myStmt, myRs);
			}	
	}
	
	//Update members of staff information
	public void updateSchedule(Schedule theSchedule) throws Exception {
			
		Connection conn = null;
		PreparedStatement myStmt = null;
			
		try {
			//get connection to DB
			conn=dataSource.getConnection();
				
			//create sql update statement
				
			String sql = "Update schedule " + "set sport=?,venue=?,time=?,teams_fixtured=?,date=?" + "where id=?";
				
			//create a prepared statement
				
			myStmt = conn.prepareStatement(sql);
				
			//set params
				
			myStmt.setString(1, theSchedule.getSport());
			myStmt.setString(2, theSchedule.getVenue());
			myStmt.setString(3, theSchedule.getTime());
			myStmt.setString(4, theSchedule.getTeams_fixtured());
			myStmt.setString(5, theSchedule.getDate());
			myStmt.setInt(6, theSchedule.getId());
				
			//execute sql
			myStmt.execute();
			
		}
		finally {
			//clean up JDBC objects
			close(conn,myStmt,null);
		}
			
	}
	
	public void deleteSchedule(String theScheduleId) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert the schedule id to int
			int scheduleId = Integer.parseInt(theScheduleId);
			
			//get connection to database
			conn = dataSource.getConnection();
			
			//create sql to delete schedule
			String sql = "delete from schedule where id=?";
					
			//prepare statement
			myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, scheduleId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(conn,myStmt,null);
		}
	}
	
	public Schedule getSchedule(String theScheduleId) throws Exception {
		
		Schedule theSchedule = null;
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int scheduleId;
		
		try {
			//convert the scheduleId into an integer
			scheduleId = Integer.parseInt(theScheduleId);
			
			//get DB connection 
			conn = dataSource.getConnection();
			
			//create sql statement to get selected schedule
			String sql = "Select * from schedule where id=?";
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
					
			//set params
			myStmt.setInt(1, scheduleId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from the result set
			if (myRs.next()) {
				String sport = myRs.getString("sport");
				String venue = myRs.getString("venue");
				String time = myRs.getString("time");
				String teams_fixtured = myRs.getString("teams_fixtured");
				String date = myRs.getString("date");
				
				//use the schedule id during construction
				theSchedule = new Schedule(scheduleId, sport, venue, time, teams_fixtured, date);
			}
			else {
				throw new Exception("could not find the schedule ID" + scheduleId);
			}
		}
		finally {
			//clean up JDBC objects
			close(conn, myStmt, myRs);
		}
		
		return theSchedule;
	}
}
