package com.turner.Store2Floor.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.turner.Store2Floor.model.User;
import com.turner.Store2Floor.service.MariaDbUtil;

public class UserDao {
	private static Connection conn = MariaDbUtil.getConnection();
	private static String selectAllUsers = "select * from user";
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Statement statement = null;
		ResultSet result = null;
		try {
		 statement = conn.createStatement();
		 result = statement.executeQuery(selectAllUsers);
		 while(result.next()) {
			 User user = new User();
			 user.setID(result.getInt("ID"));
			 user.setUserName(result.getString("UserName"));
			 user.setFirstName(result.getString("FirstName"));
			 user.setLastName(result.getString("LastName"));
			 user.setEmail(result.getString("Email"));
			 user.setPassword(result.getString("Password"));
			 user.setRole(result.getString("Role"));
			 users.add(user);
		 }
		}
		catch(SQLException e){
		}
		return users;
	}
	
}
