package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlethttp://marketplace.eclipse.org/marketplace-client-intro?mpc_install=3274405
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=BST";
	        String user = "springstudent";
	        String pass = "springstudent";
	        String driver = "com.mysql.jdbc.Driver";

	        try {
	            PrintWriter out = resp.getWriter();
	            out.println("Connecting to database " + jdbcUrl);

	            Class.forName(driver);
	            Connection myConnn = DriverManager.getConnection(jdbcUrl, user, pass);

	            out.println("Connection successful!!");
	            myConnn.close();

	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	    }
	}
