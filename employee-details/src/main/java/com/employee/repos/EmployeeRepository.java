package com.employee.repos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.employee.entity.Employee;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.ifaces.EmployeeCrudRepository;


public class EmployeeRepository implements EmployeeCrudRepository {
	
	private Connection connection;
	private List<Employee> employeeList;

	public EmployeeRepository(Connection connection) {
		super();
		this.employeeList=new ArrayList<>();
		this.connection = connection;
		try {
			this.employeeList=findAll();
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean save(Employee employee){
		String query="INSERT INTO employee_table values(?,?,?,?,?,?,?)";
		int rowAdded=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1,employee.getFirstName());
			statement.setString(2,employee.getLastName());
			statement.setString(3,employee.getAddress());
			statement.setString(4,employee.getEmailAddress());
			statement.setLong(5, employee.getPhoneNumber());
			statement.setDate(6, Date.valueOf(employee.getDateOfBirth()));
			statement.setDate(7,Date.valueOf(employee.getWeddingDate()));
			rowAdded=statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(rowAdded==1) { 
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> getByName(String name) throws EmployeeNotFoundException {
		List<Employee> list=this.employeeList.stream().filter(e->e.getFirstName().equals(name)).collect(Collectors.toList());
		if(list.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-102", "Employee Not Found with the given name : "+name);
		}
		else {
			return list;
		}
	}

	@Override
	public Map<String,Long> getNameAndPhoneNumber() throws EmployeeNotFoundException {
		Map<String,Long> nameAndPhoneNumber=this.employeeList.stream().collect(Collectors.toMap(Employee :: getFirstName, Employee :: getPhoneNumber));;
		if(nameAndPhoneNumber.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-103", "No Employee is Found");
		}
		else {
			return nameAndPhoneNumber;
		}
	}

	@Override
	public boolean updateEmailAndPhoneNumber(String initialMail, String changedMail, long phoneNumber) throws EmployeeNotFoundException {
		String query="UPDATE employee_table SET email_address=?,phone_number=? WHERE email_address=?";
		int rowUpdated=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1,changedMail);
			statement.setLong(2,phoneNumber);
			statement.setString(3,initialMail);
			rowUpdated=statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(rowUpdated==1) return true;
		return false;
	}

	@Override
	public boolean deleteByName(String name) throws EmployeeNotFoundException {
		String query="DELETE FROM employee_table WHERE first_name=?";
		int rowDeleted=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1,name);
			rowDeleted=statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		boolean result=(rowDeleted==1) ? true : false;
		return result;
	}

	@Override
	public Map<String,String> getNameAndEmail(LocalDate date) throws EmployeeNotFoundException {
		Map<String,String> nameAndEmail=this.employeeList.stream().filter(e->e.getDateOfBirth().getMonth().equals(date.getMonth()) && e.getDateOfBirth().getDayOfMonth()==date.getDayOfMonth()).collect(Collectors.toMap(Employee :: getFirstName, Employee :: getEmailAddress));
		if(nameAndEmail.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-104", "No Employee is Found with the given Birthday Date");
		}
		else {
			return nameAndEmail;
		}
	}

	@Override
	public Map<String,Long> getNameAndPhoneNumber(LocalDate date) throws EmployeeNotFoundException {
		Map<String,Long> nameAndPhoneNumber=this.employeeList.stream().filter(e->e.getWeddingDate().getMonth().equals(date.getMonth()) && e.getWeddingDate().getDayOfMonth()==date.getDayOfMonth()).collect(Collectors.toMap(Employee :: getFirstName, Employee :: getPhoneNumber));
		if(nameAndPhoneNumber.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-105", "No Employee is Found with the given wedding date");
		}
		else {
			return nameAndPhoneNumber;
		}
	}
	
	private Employee mapRowToObject(ResultSet resultSet) throws SQLException{
		String firstName=resultSet.getString("first_name");
		String lastName=resultSet.getString("last_name");
		String address=resultSet.getString("address");
		String emailAddress=resultSet.getString("email_address");
		long phoneNumber=resultSet.getLong("phone_number");
		LocalDate dateOfBirth=resultSet.getDate("date_of_birth").toLocalDate();
		LocalDate weddingDate=null;
		if(resultSet.getDate("wedding_date")!=null) {
			weddingDate=resultSet.getDate("wedding_date").toLocalDate();
		}
		return new Employee(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate);
						
	}
	
	public List<Employee> findAll() throws EmployeeNotFoundException{
		String query="SELECT * FROM employee_table";
		Employee employee=null;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			ResultSet resultSet=statement.executeQuery();
			if(resultSet.next()) {
				employee=mapRowToObject(resultSet);
				this.employeeList.add(employee);
				while(resultSet.next()) {
					employee=mapRowToObject(resultSet);
					this.employeeList.add(employee);
				}
			}
			else {
				throw new EmployeeNotFoundException("ERR-103", "No Employee is found");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return this.employeeList;
	}

}
