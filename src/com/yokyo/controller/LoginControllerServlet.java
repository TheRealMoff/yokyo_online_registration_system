package com.yokyo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.beans.Registration;
import com.yokyo.DAO.LoginDBUtil;
import com.yokyo.DAO.RegistrationDBUtil;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginDBUtil loginDBUtil;
    
	//define datasource/connection pool for resource injection
	@Resource(name = "jdbc/yokyo_registration_system")	
	private DataSource dataSource;
       
    @Override
	public void init() throws ServletException {
		super.init();
		//create our RegistrationDBUtil and parse in the connection pool
		loginDBUtil = new LoginDBUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//read "the command parameter"
			String theCommand = request.getParameter("command");
			
			//route to the appropriate method
			switch(theCommand) {
				
			case "USERLOG":
				userLogIn(request,response);
				break;
			}
		}
		catch(Exception exec) {
			throw new ServletException(exec);
		}
	}

	private void userLogIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read subscriber data from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		//create a new subscriber object
		Registration theUser = new Registration(username, password);
				
		//retrieve the subscriber from the database
		loginDBUtil.userLogIn(theUser);
				
		//retrieve the admin from the database
		loginDBUtil.userLogIn(theUser);
						
		LoginDBUtil login = new LoginDBUtil(dataSource);
						
		if(loginDBUtil.userLogIn(theUser)) {
			HttpSession session = request.getSession();
			session.setAttribute(username, session);
			response.sendRedirect("successLogin.jsp");		
				}
			else {
					
				response.sendRedirect("errorLogin.jsp");	
				}
				
		//send to view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userdash.jsp");
		
	}

}
