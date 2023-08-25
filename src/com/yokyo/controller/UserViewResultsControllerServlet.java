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

import com.beans.Results;
import com.yokyo.DAO.ResultsDBUtil;

/**
 * Servlet implementation class ResultsControllerServlet
 */
@WebServlet("/UserViewResultsControllerServlet")
public class UserViewResultsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ResultsDBUtil resultsDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our StaffDBUtil and parse in the connection pool
		resultsDBUtil = new ResultsDBUtil(dataSource);
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
				listResults(request,response);
				break;
				
			case "DELETE":
				deleteResults(request,response);
				break;
				
			case "UPDATE":
				updateResults(request,response);
				break;
				
			case "LOAD":
				loadResults(request, response);
				break;
				
			case "SEARCH":
				searchResults(request,response);
				break;
				
			default:
				listResults(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
		}
	}
	
	private void searchResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String theSearchName = request.getParameter("theSearchName");
				
		//search staff from DBUtil
		List<Results> results = resultsDBUtil.searchResults(theSearchName); 
				
		//add students to the request
		request.setAttribute("RESULTS_LIST", results);
				
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewResults.jsp");
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
				addResults(request,response);
				break;
				
			default:
				listResults(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
		}
	}
	
private void loadResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read schedule id from form data
		String theResultsId = request.getParameter("resultsId");
				
		//get the schedule from the DB
		Results theResults = resultsDBUtil.getResults(theResultsId);
				
		//place the student into the request attribute
		request.setAttribute("THE_RESULTS", theResults);
				
		//send to jsp page: update_schedule.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_results.jsp");
		dispatcher.forward(request, response);
		
	}

	
	private void updateResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read results info from the form data
		int id = Integer.parseInt(request.getParameter("scheduleId"));
		String sport = request.getParameter("sport");
		String venue = request.getParameter("venue");
		String time = request.getParameter("time");
		String teams_fixtured = request.getParameter("teams_fixtured");
		String date = request.getParameter("date");
		String results = request.getParameter("results");
				
		//create a new schedule object
		Results theResults = new Results(id, sport, venue, time, teams_fixtured, date, results);
				
		//perform an update on the database
		resultsDBUtil.updateResults(theResults);
				
		//send back to the list
		listResults(request, response);
		
	}
	
	private void deleteResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read schedule id from form data
		String theResultsId = request.getParameter("resultsId");
					
		//delete account from database
		resultsDBUtil.deleteResults(theResultsId);
					
		//send them(accounts) back to "list_schedule" page
		deleteResults(request, response);
		
	}

	private void addResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read user information from form data
		String sport = request.getParameter("sport");
		String venue = request.getParameter("venue");
		String time = request.getParameter("time");
		String teams_fixtured = request.getParameter("teams_fixtured");
		String date = request.getParameter("date");
		String result = request.getParameter("results");
				
		//create a new user object2
		Results results = new Results(sport,venue,time,teams_fixtured,date,result);
				
		//add the user to the database
		resultsDBUtil.addResults(results);
				
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_results.jsp");
		dispatcher.forward(request, response);		
		
	}

	private void listResults(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//get user accounts from registrationDBUtil 
		List<Results> results = resultsDBUtil.getAllResults();
				
		//add Registered accounts to the request
		request.setAttribute("RESULTS_LIST", results);
				
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewResults.jsp");
		dispatcher.forward(request, response);
		
	}

}
