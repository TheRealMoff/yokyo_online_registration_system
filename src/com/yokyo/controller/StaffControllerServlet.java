package com.yokyo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.beans.Staff;
import com.yokyo.DAO.StaffDBUtil;

/**
 * Servlet implementation class StaffControllerServlet
 */
@WebServlet("/StaffControllerServlet")
public class StaffControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StaffDBUtil staffDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our StaffDBUtil and parse in the connection pool
		staffDBUtil = new StaffDBUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the "command parameter"
			String theCommand = request.getParameter("command");
			
			//if the command is missing,then default to listing users
			if(theCommand == null) {
				theCommand = "LIST";
				
			}
			
			//route to the appropriate method
			switch(theCommand) {
			
			case "LIST":
				listStaff(request,response);
				break;
				
			case "DELETE":
				deleteStaff(request,response);
				break;
				
			case "UPDATE":
				updateStaff(request,response);
				break;
				
			case "LOAD":
				loadStaff(request, response);
				break;
				
			case "SEARCH":
				searchStaff(request,response);
				break;
				
			default:
				listStaff(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//route to the appropriate method
			switch(theCommand) {
			
			case "ADD":
				addStaff(request,response);
				break;
				
			default:
				listStaff(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
			}
	}
	
	private void searchStaff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String theSearchName = request.getParameter("theSearchName");
				
		//search staff from DBUtil
		List<Staff> staff = staffDBUtil.searchStaff(theSearchName); 
				
		//add students to the request
		request.setAttribute("STAFF_LIST", staff);
				
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_staff.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadStaff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read staff id from form data
		String theStaffId = request.getParameter("staffId");
				
		//get the staff from the DB
		Staff theStaff = staffDBUtil.getStaff(theStaffId);
				
		//place the student into the request attribute
		request.setAttribute("THE_STAFF", theStaff);
				
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_staff.jsp");
		dispatcher.forward(request, response);
		
	}

	
	private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student info from the form data
		int id = Integer.parseInt(request.getParameter("staffId"));
		String forename = request.getParameter("name");
		String surname = request.getParameter("surname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		//create a new student object
		Staff theStaff = new Staff(id, forename, surname, username,password);
				
		//perform an update on the database
		staffDBUtil.updateStaff(theStaff);
				
		//send back to the list
		listStaff(request, response);
		
	}
	
	private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read account id from form data
		String theStaffId = request.getParameter("staffId");
					
		//delete account from database
		staffDBUtil.deleteStaff(theStaffId);
					
		//send them(accounts) back to "list-staff accounts" page
		listStaff(request, response);
		
	}

	private void addStaff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read user information from form data
		String forename = request.getParameter("name");
		String surname = request.getParameter("surname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		//create a new user object
		Staff user = new Staff(forename,surname,username,password);
				
		//add the user to the database
		staffDBUtil.addStaff(user);
				
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_staff.jsp");
		dispatcher.forward(request, response);		
		
	}

	private void listStaff(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//get user accounts from registrationDBUtil 
		List<Staff> staff = staffDBUtil.getAllStaff();
				
		//add Registered accounts to the request
		request.setAttribute("STAFF_LIST", staff);
				
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_staff.jsp");
		dispatcher.forward(request, response);
		
	}

}
