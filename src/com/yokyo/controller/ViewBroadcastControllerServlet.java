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
import com.yokyo.DAO.ScheduleDBUtil;
import com.yokyo.DAO.ViewBroadcastDBUtil;

/**
 * Servlet implementation class ViewBroadcastControllerServlet
 */
@WebServlet("/ViewBroadcastControllerServlet")
public class ViewBroadcastControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ViewBroadcastDBUtil viewBroadcastDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our StaffDBUtil and parse in the connection pool
		viewBroadcastDBUtil = new ViewBroadcastDBUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the "command parameter"
			String theCommand = request.getParameter("command");
			
			//if the command is missing,then default to listing schedule
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
				
			case "WATCH":
				watchBroadcast(request, response);
				break;
				
			case "SEARCH":
				searchBroadcast(request,response);
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
	
	private void searchBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String theSearchName = request.getParameter("theSearchName");
				
		//search staff from DBUtil
		List<Broadcast> broadcast = viewBroadcastDBUtil.searchBroadcast(theSearchName); 
				
		//add students to the request
		request.setAttribute("BROADCAST_LIST", broadcast);
				
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewBroadcast.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read broadcast id from form data
		String theBroadcastId = request.getParameter("broadcastId");
				
		//get the broadcast from the DB
		Broadcast theBroadcast = viewBroadcastDBUtil.getBroadcast(theBroadcastId);
				
		//place the broadcast into the request attribute
		request.setAttribute("THE_BROADCAST", theBroadcast);
				
		//send to jsp page: update_broadcast.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_broadcast.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void watchBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student info from the form data
		int id = Integer.parseInt(request.getParameter("broadcastId"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String video = request.getParameter("video");
				
		//create a new student object
		Broadcast theBroadcast = new Broadcast(id, title, description, video);
				
		//send to jsp page: update_broadcast.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_broadcast.jsp");
		dispatcher.forward(request, response);
		
	}

	private void listBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user accounts from registrationDBUtil
		List<Broadcast> broadcast = viewBroadcastDBUtil.getAllBroadcast();

		// add Registered accounts to the request
		request.setAttribute("BROADCAST_LIST", broadcast);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewBroadcast.jsp");
		dispatcher.forward(request, response);
		
	}
	private void addBroadcast(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* read user information from form data
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String sport = request.getParameter("sport");
		String video = request.getParameter("video");
		String upload_date = request.getParameter("upload_date");

		// create a new user object
		Broadcast broadcast = new Broadcast(title, description, sport,video, upload_date);

		// add the user to the database
		viewBroadcastDBUtil.addBroadcast(broadcast);

		// send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_broadcast.jsp");
		dispatcher.forward(request, response);*/
		
	}

}
