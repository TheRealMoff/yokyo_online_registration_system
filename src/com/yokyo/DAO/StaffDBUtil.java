package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Staff;

public class StaffDBUtil {
	
	private DataSource dataSource;
	
	public StaffDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public void addStaff(Staff staff) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get DB Connection
			conn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into staff" + "(staff_forename,staff_surname,username,password)" + "values(?,?,?,?)";
			myStmt = conn.prepareStatement(sql);
			
			//set the param values for the subscriber
			myStmt.setString(1, staff.getStaff_forename());
			myStmt.setString(2, staff.getStaff_surname());
			myStmt.setString(3, staff.getUsername());
			myStmt.setString(4, staff.getPassword());
			
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
	public List<Staff> getAllStaff() throws Exception{
		
		List<Staff> staff = new ArrayList<>();
		
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
			String sql = "select * from staff order by staff_forename";
			
			myStmt = conn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while (myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String forename = myRs.getString("staff_forename");
				String surname = myRs.getString("staff_surname");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				//create new registration object
				Staff tempStaff = new Staff(id, forename, surname, username,password);
				
				//add it to the list of accounts
				staff.add(tempStaff);
				
			}
			
			return staff;
			
		}
		
		finally {
			//close JDBC objects
			close(conn, myStmt, myRs);
			
		}
	}
	
	public List<Staff> searchStaff(String theSearchName) throws Exception {
					
			List<Staff> staff = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int staffId;
					
			try {
								
				//get connection to database
				conn = dataSource.getConnection();
						
				//only search by name if theSearchName is not empty
				if (theSearchName !=null && theSearchName.trim().length() > 0){
					
				//create sql to search for the accounts by forename
				String sql = "select * from staff where lower(staff_forename)like ? or lower(staff_surname)like ?";
						
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
				String sql = "select * from staff order by staff_surname";
							
				//create prepared statement
				myStmt =  conn.prepareStatement(sql);
							
				}
						
				//execute statement
				myRs = myStmt.executeQuery();
						
				//retrieve data from result set row
				while(myRs.next()){
							
				//retrieve data from user account records result set
				int id = myRs.getInt("id");  
				String forename = myRs.getString("staff_forename");
				String surname = myRs.getString("staff_surname");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
							
				//create new user account record object				
				Staff tempStaff = new Staff(id,forename, surname, username,password);
							
				//add it to the list of user account records
				staff.add(tempStaff);
				}		
					return staff;
					
				}
					
			finally {
					//clean up JDBC objects
					close(conn,myStmt, myRs);
					}	
		}
	
	//Update members of staff information
	public void updateStaff(Staff theStaff) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get connection to DB
			conn=dataSource.getConnection();
			
			//create sql update statement
			
			String sql = "Update staff " + "set staff_forename=?,staff_surname=?,username=?,password=?" + "where id=?";
			
			//create a prepared statement
			
			myStmt = conn.prepareStatement(sql);
			
			//set params
			
			myStmt.setString(1, theStaff.getStaff_forename());
			myStmt.setString(2, theStaff.getStaff_surname());
			myStmt.setString(3, theStaff.getUsername());
			myStmt.setString(4, theStaff.getPassword());
			myStmt.setInt(5, theStaff.getId());
			
			//execute sql
			myStmt.execute();
		}
		finally {
			//clean up JDBC objects
			close(conn,myStmt,null);
		}
		
	}
	
	public void deleteStaff(String theStaffId) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert the account id to int
			int staffId = Integer.parseInt(theStaffId);
			
			//get connection to database
			conn = dataSource.getConnection();
			
			//create sql to delete account
			String sql = "delete from staff where id=?";
					
			//prepare statement
			myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, staffId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(conn,myStmt,null);
		}
	}
	
	public Staff getStaff(String theStaffId) throws Exception {
		Staff theStaff = null;
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int staffId;
		
		try {
			//convert the staffId into an integer
			staffId = Integer.parseInt(theStaffId);
			
			//get DB connection 
			conn = dataSource.getConnection();
			
			//create sql statement to get selected staff
			String sql = "Select * from staff where id=?";
			
			//create prepared statement
			myStmt = conn.prepareStatement(sql);
					
			//set params
			myStmt.setInt(1, staffId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from the result set
			if (myRs.next()) {
				String forename = myRs.getString("staff_forename");
				String surname = myRs.getString("staff_surname");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				//use the staff id during construction
				theStaff = new Staff(staffId, forename, surname, username,password);
			}
			else {
				throw new Exception("could not find the staff ID" + staffId);
			}
		}
		finally {
			//clean up JDBC objects
			close(conn, myStmt, myRs);
		}
		
		return theStaff;
	}
}
