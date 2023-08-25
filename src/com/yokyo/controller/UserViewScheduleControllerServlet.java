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

import com.beans.Schedule;
import com.yokyo.DAO.ScheduleDBUtil;

/**
 * Servlet implementation class UserViewScheduleControllerServlet
 */
@WebServlet("/UserViewScheduleControllerServlet")
public class UserViewScheduleControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ScheduleDBUtil scheduleDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our StaffDBUtil and parse in the connection pool
		scheduleDBUtil = new ScheduleDBUtil(dataSource);
	}
	
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
				listSchedule(request,response);
				break;
				
			case "DELETE":
				deleteSchedule(request,response);
				break;
				
			case "UPDATE":
				updateSchedule(request,response);
				break;
				
			case "LOAD":
				loadSchedule(request, response);
				break;
				
			case "SEARCH":
				searchSchedule(request,response);
				break;
				
			default:
				listSchedule(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
		}
	}
	
	private void searchSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String theSearchName = request.getParameter("theSearchName");
				
		//search staff from DBUtil
		List<Schedule> schedule = scheduleDBUtil.searchSchedule(theSearchName); 
				
		//add students to the request
		request.setAttribute("SCHEDULE_LIST", schedule);
				
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewSchedule.jsp");
		dispatcher.forward(request, response);
		
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
				addSchedule(request,response);
				break;
				
			default:
				listSchedule(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
			}
		
	}
	
	private void loadSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read schedule id from form data
		String theScheduleId = request.getParameter("scheduleId");
				
		//get the schedule from the DB
		Schedule theSchedule = scheduleDBUtil.getSchedule(theScheduleId);
				
		//place the student into the request attribute
		request.setAttribute("THE_SCHEDULE", theSchedule);
				
		//send to jsp page: update_schedule.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_schedule.jsp");
		dispatcher.forward(request, response);
		
	}

	
	private void updateSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read schedule info from the form data
		int id = Integer.parseInt(request.getParameter("scheduleId"));
		String sport = request.getParameter("sport");
		String venue = request.getParameter("venue");
		String time = request.getParameter("time");
		String teams_fixtured = request.getParameter("teams_fixtured");
		String date = request.getParameter("date");
				
		//create a new schedule object
		Schedule theSchedule = new Schedule(id, sport, venue, time, teams_fixtured, date);
				
		//perform an update on the database
		scheduleDBUtil.updateSchedule(theSchedule);
				
		//send back to the list
		listSchedule(request, response);
		
	}
	
	private void deleteSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read schedule id from form data
		String theScheduleId = request.getParameter("scheduleId");
					
		//delete account from database
		scheduleDBUtil.deleteSchedule(theScheduleId);
					
		//send them(accounts) back to "list_schedule" page
		deleteSchedule(request, response);
		
	}

	private void addSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read user information from form data
		String sport = request.getParameter("sport");
		String venue = request.getParameter("venue");
		String time = request.getParameter("time");
		String teams_fixtured = request.getParameter("teams_fixtured");
		String date = request.getParameter("date");
				
		//create a new user object2
		Schedule schedule = new Schedule(sport,venue,time,teams_fixtured,date);
				
		//add the user to the database
		scheduleDBUtil.addSchedule(schedule);
				
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_schedule.jsp");
		dispatcher.forward(request, response);		
		
	}

	private void listSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//get user accounts from registrationDBUtil 
		List<Schedule> schedule = scheduleDBUtil.getAllSchedule();
				
		//add Registered accounts to the request
		request.setAttribute("SCHEDULE_LIST", schedule);
				
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewSchedule.jsp");
		dispatcher.forward(request, response);
		
	}


}
