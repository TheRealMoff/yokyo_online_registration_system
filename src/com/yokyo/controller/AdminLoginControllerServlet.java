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

import com.beans.Admin;
import com.beans.Registration;
import com.yokyo.DAO.LoginDBUtil;

/**
 * Servlet implementation class AdminLoginControllerServlet
 */
@WebServlet("/AdminLoginControllerServlet")
public class AdminLoginControllerServlet extends HttpServlet {
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
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read user credentials from form data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		//create a user object
		Admin admin = new Admin(username,password);
				
		admin.setUsername(username);
		admin.setPassword(password);
				
		//retrieve user from the database
		try {
			if(loginDBUtil.adminLogIn(admin)) {
			HttpSession session = request.getSession();
			session.setAttribute(username, session);
			response.sendRedirect("AdminDashControllerServlet");
			}
			else {
				response.sendRedirect("admin_login.jsp");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
}
