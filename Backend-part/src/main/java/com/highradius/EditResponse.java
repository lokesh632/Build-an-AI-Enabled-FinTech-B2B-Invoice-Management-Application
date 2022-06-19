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


//@WebServlet("/EditResponse")
public class EditResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String jdbcURL = "jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root@1234";

	public EditResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HashMap<Object, Object> Response = new HashMap<Object, Object>();
			String slNo = request.getParameter("sl_no");
			int sl_no = Integer.parseInt(slNo);
			String newCurrency = request.getParameter("invoice_currency");
			String newCustPaymentTerm = request.getParameter("cust_payment_terms");

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String query = "UPDATE winter_internship set invoice_currency=?, cust_payment_terms=? where sl_no = ?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, newCurrency);
			ps.setString(2, newCustPaymentTerm);
			ps.setInt(3, sl_no);

			if (ps.executeUpdate() > 0) {
				Response.put("edit", true);
			} else {
				Response.put("edit", false);
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
