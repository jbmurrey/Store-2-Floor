package com.turner.Store2Floor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.turner.Store2Floor.model.Item;
import com.turner.Store2Floor.service.MariaDbUtil;

public class ItemDao {
	// Queries
	private static String selectAllItems = "select * from item";
	private static String selectItem = "select * from item " + "WHERE ItemID = ?";
	private static String selectItemsbyName = "select * from item" + " WHERE Item_Name LIKE ?";
	private static String updateItem = "UPDATE item " + " SET Item_Name =  ? " + " , qStore =  ? " + "  , qFloor =  ? " + "  WHERE ItemID = ? ";
	private static String insertItem = "INSERT INTO item (Item_Name,qStore,qFloor) VALUES(" + "? , " + "? , " + "? , " + ")";
	private static String deleteItem = "DELETE FROM item" + " WHERE ItemID = ?";

	public List<Item> getAllItems() {
		Connection conn = MariaDbUtil.getConnection();
		List<Item> items = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllItems);
			items = resultToList(result);
		} catch (SQLException e) {
		}
		return items;
	}

	public List<Item> getItembyID(int id) {
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet result = null;
		List<Item> items = null;
		try {
			ps = conn.prepareStatement(selectItem);
			ps.setString(1, Integer.toString(id));
			result = ps.executeQuery();
			items = resultToList(result);
			ps.close();
			conn.close();
		}

		catch (SQLException e) {

		}
		return items;
	}

	public List<Item> getItemsbyName(String name) {
		Connection conn = MariaDbUtil.getConnection();
		name = "%" + name + "%";
		PreparedStatement ps = null;
		ResultSet result = null;
		List<Item> items = null;
		try {
			ps = conn.prepareStatement(selectItemsbyName);
			ps.setString(1, name);
			result = ps.executeQuery();
			items = resultToList(result);
			ps.close();
			conn.close();
		}

		catch (SQLException e) {
			System.out.print(e);
		}
		return items;
	}

	public Item updateItem(Item updated_item) {
		Connection conn = MariaDbUtil.getConnection();
		List<Item> item = getItembyID(updated_item.getItemID());
		if (item.size() > 0) {
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(updateItem);
				ps.setString(1, updated_item.getItem_Name());
				ps.setInt(2, updated_item.getqStore());
				ps.setInt(3, updated_item.getqFloor());
				ps.setInt(4, updated_item.getItemID());
				ps.executeUpdate();
				ps.close();
				conn.close();
			}

			catch (SQLException e) {

			}
		}
		return updated_item;
	}

	public Item addItem(Item new_item) {
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insertItem);
			ps.setString(1, new_item.getItem_Name());
			ps.setInt(2, new_item.getqStore());
			ps.setInt(2, new_item.getqFloor());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return new_item;

	}

	public boolean deleteItem(int id) {
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(deleteItem);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		}

		catch (SQLException e) {
			return false;
		}
	}

	public List<Item> resultToList(ResultSet result) {
		List<Item> items = new ArrayList<Item>();
		try {
			while (result.next()) {
				Item item = new Item();
				item.setItemID(result.getInt("ItemID"));
				item.setItem_Name(result.getString("Item_Name"));
				item.setqStore(result.getInt("qStore"));
				item.setqFloor(result.getInt("qFloor"));
				items.add(item);
			}
		} catch (SQLException e) {
		}
		return items;
	}

}
