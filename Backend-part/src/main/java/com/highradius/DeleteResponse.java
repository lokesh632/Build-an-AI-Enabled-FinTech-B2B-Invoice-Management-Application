package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//@WebServlet("/DeleteResponse")
public class DeleteResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String jdbcURL = "jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root@1234";

	public DeleteResponse() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HashMap<Object, Object> Response = new HashMap<Object, Object>();
			String sl_no = request.getParameter("sl_no");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String query = "delete from winter_internship where sl_no=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, sl_no);

			if (ps.executeUpdate() > 0) {
				Response.put("deleted", true);
			} else {
				Response.put("deleted", false);
			}
			Gson gson = new Gson();
			response.setHeader("Access-Control-Allow-Origin", "*");
			String Responsejson = gson.toJson(Response);
			response.getWriter().append(Responsejson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
