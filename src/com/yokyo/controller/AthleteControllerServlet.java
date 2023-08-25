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

import com.beans.Athlete;
import com.beans.Staff;
import com.yokyo.DAO.AthleteDBUtil;
import com.yokyo.DAO.StaffDBUtil;

/**
 * Servlet implementation class AthleteControllerServlet
 */
@WebServlet("/AthleteControllerServlet")
public class AthleteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AthleteDBUtil athleteDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our StaffDBUtil and parse in the connection pool
		athleteDBUtil = new AthleteDBUtil(dataSource);
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
				listAthlete(request,response);
				break;
				
			case "DELETE":
				deleteAthlete(request,response);
				break;
				
			case "UPDATE":
				updateAthlete(request,response);
				break;
				
			case "LOAD":
				loadAthlete(request, response);
				break;
				
			case "SEARCH":
				searchAthlete(request,response);
				break;
				
			default:
				listAthlete(request,response);
				
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
				addAthlete(request,response);
				break;
				
			default:
				listAthlete(request,response);
			}
		}
		catch (Exception exec) {
			throw new ServletException(exec);
			}
	}
	
private void searchAthlete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String theSearchName = request.getParameter("theSearchName");
				
		//search staff from DBUtil
		List<Athlete> athlete = athleteDBUtil.searchAthlete(theSearchName); 
				
		//add students to the request
		request.setAttribute("ATHLETE_LIST", athlete);
				
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_athletes.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadAthlete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read staff id from form data
		String theAthleteId = request.getParameter("athleteId");
				
		//get the staff from the DB
		Athlete theAthlete = athleteDBUtil.getAthlete(theAthleteId);
				
		//place the student into the request attribute
		request.setAttribute("THE_ATHLETE", theAthlete);
				
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_athlete.jsp");
		dispatcher.forward(request, response);
		
	}

	
	private void updateAthlete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student info from the form data
		int id = Integer.parseInt(request.getParameter("athleteId"));
		String forename = request.getParameter("name");
		String surname = request.getParameter("surname");
		String country = request.getParameter("country");
		String sport = request.getParameter("sport");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		//create a new student object
		Athlete theAthlete = new Athlete(id, forename, surname, country, sport, username, password);
				
		//perform an update on the database
		athleteDBUtil.updateAthlete(theAthlete);
				
		//send back to the list
		listAthlete(request, response);
		
	}
	
	private void deleteAthlete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read account id from form data
		String theAthleteId = request.getParameter("athleteId");
					
		//delete account from database
		athleteDBUtil.deleteAthlete(theAthleteId);
					
		//send them(accounts) back to "list-athlete accounts" page
		listAthlete(request, response);
		
	}

	private void addAthlete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read user information from form data
		String forename = request.getParameter("name");
		String surname = request.getParameter("surname");
		String country = request.getParameter("country");
		String sport = request.getParameter("sport");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		//create a new user object2
		Athlete athlete = new Athlete(forename, surname, country, sport, username, password);
				
		//add the user to the database
		athleteDBUtil.addAthlete(athlete);
				
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_athletes.jsp");
		dispatcher.forward(request, response);		
		
	}

	private void listAthlete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//get user accounts from registrationDBUtil 
		List<Athlete> athlete = athleteDBUtil.getAllAthletes();
				
		//add Registered accounts to the request
		request.setAttribute("ATHLETE_LIST", athlete);
				
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_athletes.jsp");
		dispatcher.forward(request, response);
		
	}


}
