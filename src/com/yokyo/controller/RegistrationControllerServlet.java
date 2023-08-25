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

import com.beans.Registration;
import com.yokyo.DAO.RegistrationDBUtil;

/**
 * Servlet implementation class RegistrationControllerServlet
 */
@WebServlet("/RegistrationControllerServlet")
public class RegistrationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RegistrationDBUtil registrationDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our RegistrationDBUtil and parse in the connection pool
		registrationDBUtil = new RegistrationDBUtil(dataSource);
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
				listUsers(request,response);
				break;
				
			case "UPDATE":
				updateUsers(request,response);
				break;
				
			case "DELETE":
				deleteUser(request,response);
				break;
				
			case "LOAD":
				loadUsers(request,response);
				break;
				
			case "SEARCH":
				searchUser(request,response);
				break;
				
			default:
				listUsers(request,response);
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
				addUser(request,response);
				break;
				
			default:
				listUsers(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
			}
		
	}
	
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String theSearchName = request.getParameter("theSearchName");
				
		//search staff from DBUtil
		List<Registration> user = registrationDBUtil.searchRegister(theSearchName); 
				
		//add students to the request
		request.setAttribute("REGISTER_LIST", user);
				
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_users.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read staff id from form data
		String theRegisterId = request.getParameter("registerId");
				
		//get the staff from the DB
		Registration theRegister = registrationDBUtil.getUser(theRegisterId);
				
		//place the student into the request attribute
		request.setAttribute("THE_REGISTER", theRegister);
				
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_user_form.jsp");
		dispatcher.forward(request, response);
		
	}

	
	private void updateUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student info from the form data
		int id = Integer.parseInt(request.getParameter("registerId"));
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fav_sport = request.getParameter("fav_sport");
				
		//create a new student object
		Registration theRegister = new Registration(id, email, username, password,fav_sport);
				
		//perform an update on the database
		registrationDBUtil.updateRegister(theRegister);
				
		//send back to the list
		listUsers(request, response);
		
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read account id from form data
		String theRegisterId = request.getParameter("registerId");
					
		//delete account from database
		registrationDBUtil.deleteUser(theRegisterId);
					
		//send them(accounts) back to "list-users accounts" page
		listUsers(request, response);
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read user information from form data
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fav_sport = request.getParameter("favourite_sport");
		
		//create a new user object2
		Registration user = new Registration(email,username,password,fav_sport);
		
		//add the user to the database
		registrationDBUtil.registerUser(user);
		
		
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/successRegistration.jsp");
		dispatcher.forward(request, response);
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//get user accounts from registrationDBUtil 
		List<Registration> register = registrationDBUtil.getAllUsers();
		
		//add Registered accounts to the request
		request.setAttribute("REGISTER_LIST", register);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_users.jsp");
		dispatcher.forward(request, response);
	}
}


