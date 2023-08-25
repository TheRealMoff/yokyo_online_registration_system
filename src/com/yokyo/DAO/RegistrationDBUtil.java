package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Registration;

public class RegistrationDBUtil {
	
private DataSource dataSource;
	
	public RegistrationDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public void registerUser(Registration register) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get DB Connection
			conn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into registration" + "(Email,Username,Password,Favourite_sport)" + "values(?,?,?,?)";
			myStmt = conn.prepareStatement(sql);
			
			//set the param values for the subscriber
			myStmt.setString(1, register.getEmail());
			myStmt.setString(2, register.getUsername());
			myStmt.setString(3, register.getPassword());
			myStmt.setString(4, register.getFavourite_sport());
			
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
	public List<Registration> getAllUsers() throws Exception{
		
		List<Registration> register = new ArrayList<>();
		
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
			String sql = "select * from registration order by Email";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String email = myRs.getString("Email");
				String username = myRs.getString("Username");
				String password = myRs.getString("Password");
				String fav_sport = myRs.getString("Favourite_sport");
				
				//create new registration object
				Registration tempRegister = new Registration(id, email, username, password,fav_sport);
				
				//add it to the list of accounts
				register.add(tempRegister);
				
			}
			
			return register;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
		}
	}
		
	@SuppressWarnings("unused")
	public List<Registration> searchRegister(String theSearchName) throws Exception {
				
			List<Registration> register = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int registerId;
				
			try {
							
				//get connection to database
				conn = dataSource.getConnection();
					
				//only search by name if theSearchName is not empty
				if (theSearchName !=null && theSearchName.trim().length() > 0){
					
				//create sql to search for the accounts by sport code
				String sql = "select * from registration where lower(Username)like ? or lower(Favourite_sport)like ?";
					
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
				String sql = "select * from registration order by Email";
						
				//create prepared statement
				myStmt =  conn.prepareStatement(sql);
						
				}
					
				//execute statement
				myRs = myStmt.executeQuery();
					
				//retrieve data from result set row
				while(myRs.next())
					{
					//retrieve data from user account records result set
						
					int id = myRs.getInt("id");  
					String email = myRs.getString("Email");
					String username = myRs.getString("Username");
					String password = myRs.getString("Password");
					String fav_sport = myRs.getString("Favourite_sport");
						
					//create new user account record object				
					Registration tempRegister = new Registration(id, email, username, password, fav_sport);
						
					//add it to the list of user account records
					register.add(tempRegister);
					}
					
					return register;
				}
				
				finally {
					//clean up JDBC objects
					close(conn,myStmt, myRs);
				}	
			
			}
	// Update passwords for users
	public void resetPassword(Registration theRegister) throws Exception
		{
			Connection conn = null;
			PreparedStatement myStmt = null;
			
		try {
			//get db connection
			conn = dataSource.getConnection();
				
			//create sql update statement
				String sql = "update registration set Password=? where id=? ";
				//prepare statement 
				myStmt = conn.prepareStatement(sql);
	
			//set the param values for the broadcast record
			myStmt.setString(1, theRegister.getPassword());		
			myStmt.setInt(2, theRegister.getId());	
				
			//execute SQL statement
			myStmt.execute();	
				
			}
			finally {
				//clean up JDBC objects
				close(conn,myStmt,null);
				}
		}
	
	public void deleteUser(String theRegisterId) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert the account id to int
			int registerId = Integer.parseInt(theRegisterId);
			
			//get connection to database
			conn = dataSource.getConnection();
			
			//create sql to delete account
			String sql = "delete from registration where id=?";
					
			//prepare statement
			myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, registerId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(conn,myStmt,null);
		}
	}
	
	//Update members of staff information
	public void updateRegister(Registration theRegister) throws Exception {
				
		Connection conn = null;
		PreparedStatement myStmt = null;
				
		try {
			//get connection to DB
			conn=dataSource.getConnection();
				
			//create sql update statement
					
			String sql = "Update registration " + "set Email =?, Username =?, Password =?, Favourite_sport =? " + "where id=?";
					
			//create a prepared statement
					
			myStmt = conn.prepareStatement(sql);
					
			//set params
					
			myStmt.setString(1, theRegister.getEmail());
			myStmt.setString(2, theRegister.getUsername());
			myStmt.setString(3, theRegister.getPassword());
			myStmt.setString(4, theRegister.getFavourite_sport());
			myStmt.setInt(7, theRegister.getId());
					
			//execute sql
			myStmt.execute();
			}
			finally {
				
				//clean up JDBC objects
				close(conn,myStmt,null);
				}
				
			}
	
	public Registration getUser(String theRegisterId) throws Exception {
		
		Registration theRegister = null;
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int registerId;
		
		try {
			//convert the studentId into an integer
			registerId = Integer.parseInt(theRegisterId);
			
			//get DB connection 
			conn = dataSource.getConnection();
			
			//create sql statement to get selected student
			String sql = "Select * from registration where id=?";
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
					
			//set params
			myStmt.setInt(1, registerId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from the result set
			if (myRs.next()) {
				String email = myRs.getString("email");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String fav_sport = myRs.getString("favourite_sport");
				
				//use the student id during construction
				theRegister = new Registration(registerId, email, username, password, fav_sport);
			}
			else {
				throw new Exception("could not find the user ID" + registerId);
			}
		}
		finally {
			//clean up JDBC objects
			close(conn, myStmt, myRs);
		}
		
		return theRegister;
	}
}

