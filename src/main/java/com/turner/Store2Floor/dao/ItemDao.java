package com.turner.Store2Floor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.turner.Store2Floor.model.Item;
import com.turner.Store2Floor.model.Item.locations;
import com.turner.Store2Floor.service.MariaDbUtil;

public class ItemDao {
	//Connection to MariaDB
	private static Connection conn = MariaDbUtil.getConnection();
	//Queries
	private static String selectAllItems = "select * from item";
	private static String selectItemsbyLocation = "select * from item Where Location = ?";
	private static String selectItem = "select * from item " + "WHERE ItemID = ?";
	private static String updateItem = "UPDATE item " + " SET Item_Name =  ? " + " , Quantity =  ? " + "  , Location =  ? " + " , Section =  ? " +  "  WHERE ItemID = ? ";
	private static String insertItem = "INSERT INTO item (Item_Name,Quantity,Location,Section) VALUES(" + "? , " +  "? , " + "? , " + " ? " + ")";
	private static String deleteItem = "DELETE FROM item" +  " WHERE ItemID = ?";
	
	public List<Item> getAllItems() {
		Statement statement = null;
		ResultSet result = null;
		try {
		 statement = conn.createStatement();
		 result = statement.executeQuery(selectAllItems);
		}
		catch(SQLException e){
		}
		return resultToList(result);
	}
	
	public List<Item> getItembyID(int id) {
		PreparedStatement ps = null;
		ResultSet result = null;
		List<Item> items = null;
		try{
			ps = conn.prepareStatement(selectItem);
			ps.setString(1,Integer.toString(id));
			result = ps.executeQuery();
			items = resultToList(result);
			ps.close();
		}
		
		catch(SQLException e){
		
		}
		return items;
	}
	
	public List<Item> getItembyLocation(int locationID) {
		
		String location = Item.locations[locationID];
		PreparedStatement ps = null;
		ResultSet result = null;
		List<Item> items = null;
		try{
			ps = conn.prepareStatement(selectItemsbyLocation);
			ps.setString(1,location);
			System.out.println(ps);
			result = ps.executeQuery();
			items = resultToList(result);
			ps.close();
		}
		
		catch(SQLException e){
		
		}
		return items;
	}
	
	public Item updateItem(Item updated_item ) {
		List<Item> item  = getItembyID(updated_item.getItemID());
		if(item.size()>0) {
		PreparedStatement ps = null;
		try{
			
			ps = conn.prepareStatement(updateItem);
			ps.setString(1, updated_item.getItem_Name());
			ps.setInt(2,updated_item.getQuantity());
			ps.setString(3, updated_item.getLocation());
			ps.setString(4,updated_item.getSection());
			ps.setInt(5,updated_item.getItemID());
			ps.executeUpdate();
			ps.close();
		}
		
		catch(SQLException e){
		
		}
		}
		return updated_item;
		
	}
	
	public Item addItem(Item new_item ) {
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(insertItem);
			ps.setString(1, new_item.getItem_Name());
			ps.setInt(2,new_item.getQuantity());
			ps.setString(3, new_item.getLocation());
			ps.setString(4,new_item.getSection());
			ps.executeUpdate();
			System.out.println(ps);
			ps.close();
		}
		
		catch(SQLException e){
		System.out.println(e);
		}
		
		return new_item;
		
	}
	
	public List<Item> resultToList(ResultSet result){
		List<Item> items = new ArrayList<Item>();
		try {
		while(result.next()) {
			 Item item = new Item();
			 item.setItemID(result.getInt("ItemID"));
			 item.setItem_Name(result.getString("Item_Name"));
			 item.setQuantity(result.getInt("Quantity"));
			 item.setLocation(result.getString("Location"));
			 item.setSection(result.getString("Section"));
			 items.add(item);
		 }
		}
		catch(SQLException e) {
			
		}
		return items;
		}
	
	public boolean deleteItem(int id){
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(deleteItem);
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


