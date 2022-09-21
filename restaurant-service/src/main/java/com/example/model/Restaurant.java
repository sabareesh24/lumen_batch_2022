package com.example.model;

public class Restaurant {
	private int id;
	private String restaurantName;
	private long pinCode;
	private String cuisine;
	
	public Restaurant() {
		super();
	}

	public Restaurant(int id, String restaurantName, long pinCode, String cuisine) {
		super();
		this.id = id;
		this.restaurantName = restaurantName;
		this.pinCode = pinCode;
		this.cuisine = cuisine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", pinCode=" + pinCode + ", cuisine="
				+ cuisine + "]";
	}
	
	
}
