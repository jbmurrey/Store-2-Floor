package com.turner.Store2Floor.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.turner.Store2Floor.model.Item;
import com.turner.Store2Floor.model.User;
import com.turner.Store2Floor.service.MariaDbUtil;

public class UserDao {
	//Connection to MariaDB
		private static Connection conn = MariaDbUtil.getConnection();
		//Queries
		private static String selectAllUsers = "select * from user";
		private static String selectUser = "select * from user " + "WHERE ID = ?";
		private static String updateUser = "UPDATE user " + " SET UserName =  ? " + " , FirstName = ? " + " , LastName = ?" + " , Password =  ? " + "  , Email =  ? " + " , Role =  ? " + "WHERE ID = ? ";
		private static String insertUser = "INSERT INTO user (UserName, FirstName, LastName, Password, Email, Role) VALUES(" + "? , " +  "? , " + "? , " + " ? ," + "? ," + "? " + ")";
		private static String deleteUser = "DELETE FROM user" +  " WHERE ID = ?";
		
		public List<User> getAllUsers() {
			Statement statement = null;
			ResultSet result = null;
			try {
			 statement = conn.createStatement();
			 result = statement.executeQuery(selectAllUsers);
			}
			catch(SQLException e){
			}
			return resultToList(result);
		}
		
		public List<User> getUserbyID(int id) {
			PreparedStatement ps = null;
			ResultSet result = null;
			List<User> users = null;
			try{
				ps = conn.prepareStatement(selectUser);
				ps.setString(1,Integer.toString(id));
				result = ps.executeQuery();
				users = resultToList(result);
				ps.close();
			}
			
			catch(SQLException e){
			
			}
			return users;
		}
		
		public User updateUser(User updated_user ) {	
			List<User> user  = getUserbyID(updated_user.getID());
			if(user.size()>0) {
			PreparedStatement ps = null;
			try{
				
				ps = conn.prepareStatement(updateUser);
				ps.setString(1, updated_user.getUserName());
				ps.setString(2, updated_user.getFirstName());
				ps.setString(3, updated_user.getLastName());
				ps.setString(4, updated_user.getPassword());
				ps.setString(5, updated_user.getEmail());
				ps.setString(6, updated_user.getRole());
				ps.setInt(7, updated_user.getID());
				ps.executeUpdate();
				ps.close();
			}
			
			catch(SQLException e){
			
			}
			}
			return updated_user;
			
		}
		
		public User addUser(User new_user ) {
			PreparedStatement ps = null;
			try{
				ps = conn.prepareStatement(insertUser);
				ps.setString(1, new_user.getUserName());
				ps.setString(2,new_user.getFirstName());
				ps.setString(3, new_user.getLastName());
				ps.setString(4, new_user.getPassword());
				ps.setString(5, new_user.getEmail());
				ps.setString(6,new_user.getRole());
				ps.executeUpdate();				
				ps.close();
			}
			
			catch(SQLException e){
			System.out.println(e);
			}
			
			return new_user;
			
		}
		
		public List<User> resultToList(ResultSet result){
			List<User> users = new ArrayList<User>();
			try {
			while(result.next()) {
				 User user = new User();
				 user.setID(result.getInt("ID"));
				 user.setUserName(result.getString("UserName"));
				 user.setFirstName(result.getString("FirstName"));
				 user.setLastName(result.getString("LastName"));
				 user.setPassword(result.getString("Password"));
				 user.setEmail(result.getString("Email"));
				 user.setRole(result.getString("Role"));
				 users.add(user);
			 }
			}
			catch(SQLException e) {
				
			}
			return users;
			}
		
		public boolean deleteUser(int id){
			PreparedStatement ps = null;
			try{
				ps = conn.prepareStatement(deleteUser);
				ps.setInt(1,id);
				ps.executeUpdate();
				ps.close();
				return true;
			}
			
			catch(SQLException e){
				return false;
			}		
		}
	}
