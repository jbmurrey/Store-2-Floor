package com.turner.Store2Floor.model;

public class Item {
	private int ItemID;
	private String Item_Name;
	private int qStore;
	private int qFloor;
	public int getItemID() {
		return ItemID;
	}
	public void setItemID(int itemID) {
		ItemID = itemID;
	}
	public String getItem_Name() {
		return Item_Name;
	}
	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}
	public int getqStore() {
		return qStore;
	}
	public void setqStore(int qStore) {
		this.qStore = qStore;
	}
	public int getqFloor() {
		return qFloor;
	}
	public void setqFloor(int qFloor) {
		this.qFloor = qFloor;
	}
	
	
	
}
