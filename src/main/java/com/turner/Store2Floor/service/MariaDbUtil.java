package com.turner.Store2Floor.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaDbUtil {
	
	public static Connection conn;
	public static Connection getConnection(){
      String url = "jdbc:mysql://localhost:3306/storagedb";
      String username = "root";
      String password = "letmein";
      /*try {
    	  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(ClassNotFoundException e) {
    	  System.out.println(e);
    	  return null;
      }
      */
      try{
    	  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    	  conn = DriverManager.getConnection(url, username, password);
    	  return conn;
      }
      catch(SQLException e) {
    	  System.out.println(e);
    	  return null;
      }
    }
}
