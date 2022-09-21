<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Add Restaurant</title>
</head>
<body>
<form action="restaurant" method="post" name="addRestaurant">
<div class="form-group">
	<label for="restaurantId">Restaurant ID</label>
	<input type="text" name="restaurantId" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="restaurantName">Restaurant Name</label>
	<input type="text" name="restaurantName" class="form-control required="required""/>
</div>
<div class="form-group">
	<label for="pincode">Pincode</label>
	<input type="text" name="pincode" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="cuisine">Cuisine</label>
	<input type="text" name="cuisine" class="form-control"/>
</div>
<div class="form-group">
	<input type="hidden" name="decision" value="Add">
	<input type="submit" class="btn btn-primary" value="Add"/>
</div>
</form>
</body>
</html>