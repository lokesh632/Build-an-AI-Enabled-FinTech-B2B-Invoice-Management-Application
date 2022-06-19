package com.highradius;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


//@WebServlet("/addActor")
public class AddResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root@1234";
       
   
    public AddResponse() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;

		try {
			   HashMap<Object, Object> Response = new HashMap<Object, Object>();
			   String business_code = request.getParameter("business_code");
			   String cust_number = request.getParameter("cust_number");
               String clear_date = request.getParameter("clear_date");
               String  buisness_year = request.getParameter("buisness_year");
               String doc_id = request.getParameter("doc_id");
               String posting_date = request.getParameter("posting_date");
               String document_create_date = request.getParameter("document_create_date");
               String document_create_date1 = request.getParameter("document_create_date1");
               String due_in_date = request.getParameter("due_in_date");
               String invoice_currency = request.getParameter("invoice_currency;");
               String document_type = request.getParameter("document_type");
               String posting_id = request.getParameter("posting_id");
               String area_business = request.getParameter("area_business");
			   String total_open_amount = request.getParameter("total_open_amount");
			   String baseline_create_date = request.getParameter("baseline_create_date");
			   String cust_payment_terms = request.getParameter("cust_payment_terms");
			   String invoice_id = request.getParameter(" invoice_id");
			   String isOpen = request.getParameter(" isOpen");
			   String  aging_bucket = request.getParameter(" aging_bucket");
			   String is_deleted = request.getParameter("isDeleted");
			 

			   Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			   PreparedStatement pt = con.prepareStatement("select count(*) from winter_internship"); 
			   // At = 16,after insertion = 17
			   
			   ResultSet rt = pt.executeQuery();
			   int len = 0 ;
			   while (rt.next()) 
			   {			   
				 len = rt.getInt(1);
				 System.out.println("And now the total number of rows (record) =<b> " + len +"</b>");
			   }
			  int sl_no = len + 1;
              String sql = "INSERT INTO winter_internship (sl_no, business_code, cust_number, clear_date, buisness_year, doc_id, posting_date, document_create_date, document_create_date1, due_in_date, invoice_currency, document_type, posting_id, area_business, total_open_amount, baseline_create_date, cust_payment_terms, invoice_id, isOpen, aging_bucket, is_deleted) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             

			   PreparedStatement ps = con.prepareStatement(sql);
			   ps.setInt(1,sl_no);
			   ps.setString(2,business_code);
			   ps.setString(3,cust_number);
			   ps.setString(4,clear_date);
			   ps.setString(5,buisness_year);
			   ps.setString(6,doc_id);
			   ps.setString(7,posting_date);
			   ps.setString(8,document_create_date);
			   ps.setString(9,document_create_date1);
			   ps.setString(10,due_in_date);
			   ps.setString(11,invoice_currency);
			   ps.setString(12,document_type);
			   ps.setString(13,posting_id);
			   ps.setString(14,area_business);
			   ps.setString(15, total_open_amount);
			   ps.setString(16,baseline_create_date);
			   ps.setString(17,cust_payment_terms);
			   ps.setString(18,invoice_id);
			   ps.setString(19,isOpen);
			   ps.setString(20,aging_bucket);
			   ps.setString(21,is_deleted);
			
			   
			   if(ps.executeUpdate()>0) {
				   Response.put("insert", true);
			   }
			   else
			   {
				   Response.put("insert", false);

			   }
			   Gson gson = new Gson();
			   String JSONresponse = gson.toJson(Response);
			   response.setHeader("Access-Control-Allow-Origin","*");
			   response.getWriter().append(JSONresponse);
			   response.setIntHeader("Refresh", 5);

		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){ 
		    System.out.println(ex.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}