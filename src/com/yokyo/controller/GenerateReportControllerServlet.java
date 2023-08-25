package com.yokyo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.beans.Staff;
import com.yokyo.DAO.StaffDBUtil;

/**
 * Servlet implementation class GenerateReportControllerServlet
 */
@WebServlet("/GenerateReportControllerServlet")
public class GenerateReportControllerServlet extends HttpServlet {
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
            downloadStaffCsv(response);
            
        } catch (Exception e) {
        	
            throw new ServletException(e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void downloadStaffCsv(HttpServletResponse response) throws Exception {
        List<Staff> staff1 = staffDBUtil.getAllStaff();
        
        // Create CSV content
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("id, staff_forename, staff_surname, username, password\n"); // header
        for (Staff staff : staff1) {
            csvContent.append(staff.getId()).append(",");
            csvContent.append(staff.getStaff_forename()).append(",");
            csvContent.append(staff.getStaff_surname()).append(",");
            csvContent.append(staff.getUsername()).append(",");
            csvContent.append(staff.getPassword()).append("\n");
        }

        // Send CSV content as response
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"staff.csv\"");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(csvContent);
        }
    }
}
