package com.shiva.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Read the data give by user in HTML Controls...
		String n=request.getParameter("tbname");
		String e=request.getParameter("tbemail");
		String q=request.getParameter("tbqual");
		String p=request.getParameter("tbpass");
		
		//JDBC to store above data into student table..
		
		//Load driver class
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish connection
			
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root","admin");
				
			//Prepared Statement
				ps=con.prepareStatement("insert into student(name,email,qualif,password)values(?,?,?,?)");
				ps.setString(1, n);
				ps.setString(2, e);
				ps.setString(3, q);
				ps.setString(4, p);
				
				//step4:Execute query
				ps.executeUpdate();
				
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		finally
		{
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(con!=null) {
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
				
				
		}
			PrintWriter pw=response.getWriter();
			pw.append("<h1>Data is inserted...</h1>");	
	}
		
}
}


