package com.example.demo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.repos.RestaurantRespository;
import com.example.demo.utils.ConnectionFactory;
import com.example.exceptions.ElementNotFoundException;
import com.example.ifaces.CrudRepository;
import com.example.model.Restaurant;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection; 
    private CrudRepository<Restaurant> repository;
    
    public RestaurantServlet() {
        super();
        
    }

	
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void init() throws ServletException {
		   connection=ConnectionFactory.getMySqlConnection();
	       repository=new RestaurantRespository(connection);
	     
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String decision=request.getParameter("decision");
		if(decision.equals("Add")) {
			int restaurantId=Integer.parseInt(request.getParameter("restaurantId"));
			String restaurantName=request.getParameter("restaurantName");
			long pincode=Long.parseLong(request.getParameter("pincode"));
			String cuisine=request.getParameter("cuisine");
			Restaurant restaurant=new Restaurant(restaurantId, restaurantName, pincode, cuisine);
			if(repository.save(restaurant)) {
				request.setAttribute("result", "Restaurant Added Successfully");
			}
			else {
				request.setAttribute("result", "Couldn't able to Add the restaurant");
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher("result.jsp");
			dispatcher.forward(request, response);
		}
		else if(decision.equals("Display")){
			String restaurantName=request.getParameter("restaurantName");
			try {
				List<Restaurant> restaurantsList=(List<Restaurant>) repository.findByName(restaurantName);
				request.setAttribute("restaurantsList", restaurantsList);
				RequestDispatcher dispatcher=request.getRequestDispatcher("showRestaurants.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ElementNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
