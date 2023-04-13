/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package org.reins.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/ListUsers")
public class ListUserServlet extends HttpServlet {
    private static final long serialVersionUID = 18925377774889413L;
    
    @Resource(name="jdbc/sample")
    DataSource ds;

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println("ListUserServlet invoked!");  

        try {
            
            String role = (String) request.getParameter("q");
            System.out.println(role);

            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE role = ?");
            ps.setString(1, role);

            ResultSet rs = ps.executeQuery();
            rs.last();
            int count = rs.getRow();            
        
            if ( count == 0) {            
            	out.println("no such user");
            } else{
            	System.out.println(rs.getString(2));
            	out.println("<table>");
           	    out.println("<tr><td><b> Username: </b></td>" + "<td>"+ rs.getString(2) + "</td></tr>");
           	    out.println("<tr><td><b> Email: </b></td>" + "<td>"+ rs.getString(4) + "</td></tr>");
           	    out.println("<tr><td><b> Password: </b></td>" + "<td>"+ rs.getString(5) + "</td></tr>");

            	out.write("</table>");
            }
        	out.flush();
        } catch(Exception e){
        	e.printStackTrace();
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

