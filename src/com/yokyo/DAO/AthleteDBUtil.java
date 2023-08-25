package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Athlete;
import com.beans.Staff;

public class AthleteDBUtil {
	
	private DataSource dataSource;
	
	public AthleteDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public void addAthlete(Athlete athlete) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get DB Connection
			conn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into athlete" + "(forename,surname,country,sport,username,password)" + "values(?,?,?,?,?,?)";
			myStmt = conn.prepareStatement(sql);
			
			//set the param values for the subscriber
			myStmt.setString(1, athlete.getForename());
			myStmt.setString(2, athlete.getSurname());
			myStmt.setString(3, athlete.getCountry());
			myStmt.setString(4, athlete.getSport());
			myStmt.setString(5, athlete.getUsername());
			myStmt.setString(6, athlete.getPassword());
			
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
	public List<Athlete> getAllAthletes() throws Exception{
		
		List<Athlete> athlete = new ArrayList<>();
		
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
			String sql = "select * from athlete order by forename";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String forename = myRs.getString("forename");
				String surname = myRs.getString("surname");
				String country = myRs.getString("country");
				String sport = myRs.getString("sport");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				//create new registration object
				Athlete tempAthlete = new Athlete(id, forename, surname, country,sport,username,password);
				
				//add it to the list of accounts
				athlete.add(tempAthlete);
				
			}
			
			return athlete;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
		}
	}
	
	public List<Athlete> searchAthlete(String theSearchName) throws Exception {
					
			List<Athlete> athlete = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int athleteId;
					
			try {
								
				//get connection to database
				conn = dataSource.getConnection();
						
				//only search by name if theSearchName is not empty
				if (theSearchName !=null && theSearchName.trim().length() > 0){
					
				//create sql to search for the accounts by forename
				String sql = "select * from athlete where lower(forename)like ? or lower(surname)like ?";
						
				//create prepared statement
				myStmt = conn.prepareStatement(sql);
						
				//set params 
				String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
						
				myStmt.setString(1,theSearchNameLike);
				myStmt.setString(2,theSearchNameLike);
						
					}
						
			else
				{
				//create sql to get all user account records
				String sql = "select * from athlete order by surname";
							
				//create prepared statement
				myStmt =  conn.prepareStatement(sql);
							
				}
						
				//execute statement
				myRs = myStmt.executeQuery();
						
				//retrieve data from result set row
				while(myRs.next()){
							
				//retrieve data from user account records result set
				int id = myRs.getInt("id");  
				String forename = myRs.getString("forename");
				String surname = myRs.getString("surname");
				String country = myRs.getString("country");
				String sport = myRs.getString("sport");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
							
				//create new user account record object				
				Athlete tempAthlete = new Athlete(id,forename, surname, country,sport,username,password);
							
				//add it to the list of user account records
				athlete.add(tempAthlete);
				}		
					return athlete;
					
				}
					
			finally {
					//clean up JDBC objects
					close(conn,myStmt, myRs);
					}	
		}
	
	//Update members of staff information
	public void updateAthlete(Athlete theAthlete) throws Exception {
			
		Connection conn = null;
		PreparedStatement myStmt = null;
			
		try {
			//get connection to DB
			conn=dataSource.getConnection();
			
			//create sql update statement
				
			String sql = "Update athlete " + "set forename =?, surname =?, country =?, sport =?, username =?, password =? " + "where id=?";
				
			//create a prepared statement
				
			myStmt = conn.prepareStatement(sql);
				
			//set params
				
			myStmt.setString(1, theAthlete.getForename());
			myStmt.setString(2, theAthlete.getSurname());
			myStmt.setString(3, theAthlete.getCountry());
			myStmt.setString(4, theAthlete.getSport());
			myStmt.setString(5, theAthlete.getUsername());
			myStmt.setString(6, theAthlete.getPassword());
			myStmt.setInt(7, theAthlete.getId());
				
			//execute sql
			myStmt.execute();
			}
			finally {
				//clean up JDBC objects
				close(conn,myStmt,null);
			}
			
		}
	
	public void deleteAthlete(String theAthleteId) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert the account id to int
			int athleteId = Integer.parseInt(theAthleteId);
			
			//get connection to database
			conn = dataSource.getConnection();
			
			//create sql to delete account
			String sql = "delete from athlete where id=?";
					
			//prepare statement
			myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, athleteId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(conn,myStmt,null);
		}
	}
	
	public Athlete getAthlete(String theAthleteId) throws Exception {
		
		Athlete theAthlete = null;
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int athleteId;
		
		try {
			//convert the studentId into an integer
			athleteId = Integer.parseInt(theAthleteId);
			
			//get DB connection 
			conn = dataSource.getConnection();
			
			//create sql statement to get selected student
			String sql = "Select * from athlete where id=?";
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
					
			//set params
			myStmt.setInt(1, athleteId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from the result set
			if (myRs.next()) {
				String forename = myRs.getString("forename");
				String surname = myRs.getString("surname");
				String country = myRs.getString("country");
				String sport = myRs.getString("sport");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				//use the student id during construction
				theAthlete = new Athlete(athleteId, forename, surname, country, sport, username,password);
			}
			else {
				throw new Exception("could not find the athlete ID" + athleteId);
			}
		}
		finally {
			//clean up JDBC objects
			close(conn, myStmt, myRs);
		}
		
		return theAthlete;
	}
}
