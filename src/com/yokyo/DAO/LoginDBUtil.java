package com.yokyo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.beans.Admin;
import com.beans.Registration;

public class LoginDBUtil {
	
private DataSource dataSource;
	
	public LoginDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
public boolean userLogIn(Registration register) throws Exception {
		
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		boolean status = false;
		
		try {
			//get db connection
			conn = dataSource.getConnection();
			
			//create sql statement
			String sql = "Select * from registration where Username = ? and Password = ?";
			myStmt = conn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, register.getUsername());
			myStmt.setString(2, register.getPassword());
			
			myRs = myStmt.executeQuery();
			status = myRs.next();
		}
		finally {
			close(conn, myStmt, myRs);
		}
		
		return status;
		
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
	
	public boolean adminLogIn(Admin admin) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			boolean status = false;
			
			try {
				//get db connection
				myConn = dataSource.getConnection();
				
				//create sql statement
				String sql = "Select * from admin where username = ? and password = ?";
				myStmt = myConn.prepareStatement(sql);
				
				//set params
				myStmt.setString(1, admin.getUsername());;
				myStmt.setString(2, admin.getPassword());
				
				myRs = myStmt.executeQuery();
				status = myRs.next();
			}
			finally {
				close(myConn, myStmt, myRs);
			}
			
			return status;
		}
	}
	
