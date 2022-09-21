<%@ page language="java" import="java.util.*,com.example.model.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Show Restaurant</title>
<style type="text/css">

table,tr,td,th{

border: 2px solid blue;

margin-left: auto;
margin-right: auto;
border-collapse: collapse;
}
</style>
</head>
<body>
<h1 style="text-align: center;">Restaurant List</h1>
<table>
	<thead>
		<tr>
			<th>Restaurant ID</th>
			<th>Restaurant Name</th>
			<th>Pincode</th>
			<th>Cuisine</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${restaurantsList}" var="eachRestaurant">
		<tr>
			<td>${eachRestaurant.id}</td>
			<td>${eachRestaurant.restaurantName}</td>
			<td>${eachRestaurant.pinCode}</td>
			<td>${eachRestaurant.cuisine}</td>
		</tr>
	</c:forEach>	
	</tbody>
</table>
</body>
</html>