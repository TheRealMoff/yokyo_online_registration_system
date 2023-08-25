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

import com.beans.Broadcast;
import com.beans.Staff;
import com.yokyo.DAO.AdminDashDBUtil;
import com.yokyo.DAO.BroadcastDBUtil;

/**
 * Servlet implementation class AdminDashControllerServlet
 */
@WebServlet("/AdminDashControllerServlet")
public class AdminDashControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdminDashDBUtil adminDashDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our RegistrationDBUtil and parse in the connection pool
		adminDashDBUtil = new AdminDashDBUtil(dataSource);
	}
    
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
				listBroadcast(request,response);
				break;
				
			case "LOAD":
				loadBroadcast(request, response);
				break;
				
				
			default:
				listBroadcast(request,response);
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
				addBroadcast(request,response);
				break;
				
			default:
				listBroadcast(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
			}
		
	}
	
private void loadBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read staff id from form data
		String theBroadcastId = request.getParameter("broadcastId");
				
		//get the staff from the DB
		Broadcast theBroadcast = adminDashDBUtil.getBroadcast(theBroadcastId);
				
		//place the student into the request attribute
		request.setAttribute("THE_BROADCAST", theBroadcast);
				
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_broadcast.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void listBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user accounts from registrationDBUtil
		List<Broadcast> broadcast = adminDashDBUtil.getBroadcast();

		// add Registered accounts to the request
		request.setAttribute("BROADCAST_LIST", broadcast);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admindash.jsp");
		dispatcher.forward(request, response);
		
	}
	private void addBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user information from form data
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String sport = request.getParameter("sport");
		String video = request.getParameter("video");
		String upload_date = request.getParameter("upload_date");

		// create a new user object2
		Broadcast broadcast = new Broadcast(title, description, sport,video, upload_date);

		// add the user to the database
		adminDashDBUtil.addBroadcast(broadcast);

		// send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_broadcast.jsp");
		dispatcher.forward(request, response);
		
	}

}
