package com.example.demo.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.exceptions.ElementNotFoundException;
import com.example.ifaces.CrudRepository;
import com.example.model.Restaurant;

public class RestaurantRespository implements CrudRepository<Restaurant> {

	private Connection connection;
	
	public RestaurantRespository(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean save(Restaurant restaurant) {
		String query="INSERT INTO restaurant_table values(?,?,?,?)";
		int rowAdded=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, restaurant.getId());
			statement.setString(2, restaurant.getRestaurantName());
			statement.setLong(3, restaurant.getPinCode());
			statement.setString(4, restaurant.getCuisine());
			rowAdded=statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(rowAdded==1) return true;
		return false;
	}

	@Override
	public Collection<Restaurant> findAll() {
		List<Restaurant> restaurantsList=new ArrayList<>();
		String query="SELECT * FROM restaurant_table";
		try(PreparedStatement statement=connection.prepareStatement(query)){
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				Restaurant restaurant=mapRowToObject(resultSet);
				restaurantsList.add(restaurant);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return restaurantsList;
	}

	@Override
	public boolean delete(Restaurant restaurant) throws ElementNotFoundException {
		
		return deleteById(restaurant.getId());
	}

	@Override
	public boolean deleteById(int key) throws ElementNotFoundException {
		String query="DELETE FROM restaurant_table WHERE restauratId=?";
		int rowDeleted=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, key);
			rowDeleted=statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(rowDeleted==1) return true;
		return false;
	}

	@Override
	public Restaurant findById(int key) throws ElementNotFoundException {
		Restaurant restaurant=null;
		String query="SELECT * FROM restaurant_table WHERE restaurantId=?";
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, key);
			ResultSet resultSet=statement.executeQuery();
			if(resultSet.next()) {
				restaurant=mapRowToObject(resultSet);
			}
			else {
				throw new ElementNotFoundException("ERR-102","Restaurant with Given Id "+ key+"is Not Found");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}
	
	@Override
	public Collection<Restaurant> findByName(String name) throws ElementNotFoundException {
		List<Restaurant> restaurantsList=new ArrayList<>();
		Restaurant restaurant=null;
		String query="SELECT * FROM restaurant_table WHERE restaurantName=?";
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();
			if(resultSet.next()) {
				restaurant=mapRowToObject(resultSet);
				restaurantsList.add(restaurant);
				while(resultSet.next()) {
					restaurant=mapRowToObject(resultSet);
					restaurantsList.add(restaurant);
				}
			}
			else {
				throw new ElementNotFoundException("ERR-102","Restaurant with Given name "+ name+"is Not Found");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantsList;
	}

	@Override
	public int update(Restaurant obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Restaurant mapRowToObject(ResultSet resultSet) throws SQLException{
		int id=resultSet.getInt("restaurantId");
		String restaurantName=resultSet.getString("restaurantName");
		long pinCode=resultSet.getLong("pincode");
		String cuisine=resultSet.getString("cuisine");
		return new Restaurant(id, restaurantName, pinCode, cuisine);
	}

	
}
