package com.highradius.DemoFiles;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.highradius.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//Servlet implementation class FetchServlet

@SuppressWarnings("unused")
//@WebServlet("/FetchServlet")
public class FetchServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String jdbcURL = "jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root@1234";

	public FetchServe () {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		 int page = Integer.parseInt(request.getParameter("page"));
//	     int records_per_page = Integer.parseInt(request.getParameter("size"));
//	        
		Connection con = null;
		Statement stmt = null;
		String query = null;
		ResultSet rs = null;

		long sl_no = 0;
		String business_code = null;
		long cust_number = 0;
		String clear_date = null;
		String buisness_year = null;
		String doc_id = null;
		String posting_date = null;
		String document_create_date = null;
		String document_create_date1 = null;
		String due_in_date = null;
		String invoice_currency = null;
		String document_type = null;
		int posting_id = 0;
		String area_business = null;
		double total_open_amount = 0;
		String baseline_create_date = null;
		String cust_payment_terms = null;
		long invoice_id = 0;
		int isOpen = 0;
		int isDeleted = 0;
		String aging_bucket = null;
		String predicted = null;
		
		HashMap<Object, Object> ResponseData = new HashMap<Object, Object>();
		ArrayList<Response> fetchData = new ArrayList<>();
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			stmt = con.createStatement();
			query = "select sl_no, business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,document_create_date1,due_in_date,invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id, isOpen, aging_bucket, is_deleted from winter_internship LIMIT 10"; 
//			+ (page-1)*records_per_page+","+records_per_page;
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Response pojoResponse = new Response();

				pojoResponse.setSl_no(rs.getLong("sl_no"));
				pojoResponse.setBusiness_code(rs.getString("business_code"));
				pojoResponse.setCust_number(rs.getLong("cust_number"));
				pojoResponse.setClear_date(rs.getString("clear_date"));
				pojoResponse.setBuisness_year(rs.getString("buisness_year"));
				pojoResponse.setDoc_id(rs.getString("doc_id"));
				pojoResponse.setPosting_date(rs.getString("posting_date"));
				pojoResponse.setDocument_create_date(rs.getString("document_create_date"));
				pojoResponse.setDocument_create_date1(rs.getString("document_create_date1"));
				pojoResponse.setDue_in_date(rs.getString("due_in_date"));
				pojoResponse.setInvoice_currency(rs.getString("invoice_currency"));
				pojoResponse.setDocument_type(rs.getString("document_type"));
				pojoResponse.setPosting_id(rs.getInt("posting_id"));
				pojoResponse.setArea_business(rs.getString("area_business"));
				pojoResponse.setTotal_open_amount(rs.getDouble("total_open_amount"));
				pojoResponse.setBaseline_create_date(rs.getString("baseline_create_date"));
				pojoResponse.setCust_payment_terms(rs.getString("cust_payment_terms"));
				pojoResponse.setInvoice_id(rs.getInt("invoice_id"));
				pojoResponse.setIsOpen(rs.getInt("isOpen"));
				pojoResponse.setAging_bucket(rs.getString("aging_bucket"));
				pojoResponse.setIsDeleted(rs.getInt("is_deleted"));

				fetchData.add(pojoResponse);
			}
			ResponseData.put("fetchData", fetchData);
			
//			rs = stmt.executeQuery("select count(*) from winter_internship");
//            while(rs.next()) {
//            	int count = rs.getInt(1);
//            	ResponseData.put("count",count);
//            }
            
		}catch(Exception e) {
			e.printStackTrace();
		}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonResponse = gson.toJson(ResponseData);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.getWriter().write(jsonResponse);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}