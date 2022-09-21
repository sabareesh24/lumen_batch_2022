package com.employee.services;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.*;


import com.employee.entity.Employee;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.ifaces.EmployeeCrudRepository;
import com.employee.repos.EmployeeRepository;

public class EmployeeServices {

	private EmployeeCrudRepository repository;
	private Connection connection;
	private static final Logger logger=LogManager.getRootLogger();
	
	public EmployeeServices(Connection connection) {
		super();
		this.connection=connection;
		this.repository=new EmployeeRepository(this.connection);
	}
	
	public void addEmployee(Employee employee){
		boolean isAdded=false;
		isAdded = this.repository.save(employee);
		if(isAdded) {
			logger.info("Employee Added : "+isAdded);
		}
		else {
			logger.error("Employee Added : "+isAdded);
		}
	}
	
	public void findByName(String firstName) throws EmployeeNotFoundException{
		List<Employee> employeesList=new ArrayList<>();
		employeesList=this.repository.getByName(firstName);
		employeesList.forEach(e->logger.info(e));
	}
	
	public void displayNameAndPhoneNumber() throws EmployeeNotFoundException{
		Map<String,Long> employeesNameAndPhoneNumber=this.repository.getNameAndPhoneNumber();
		Set<Map.Entry<String,Long>> items=employeesNameAndPhoneNumber.entrySet();
		items.forEach(e->logger.info("Employee Name : "+e.getKey()+" Employee Phone Number : "+e.getValue()));
	}
	
	public void updateEmailAndPhoneNumber(String initialEmail,String changedEmail,long phoneNumber) throws EmployeeNotFoundException{
		boolean isUpdated=this.repository.updateEmailAndPhoneNumber(initialEmail, changedEmail, phoneNumber);
		if(isUpdated) {
			logger.info("Employee Updated : "+isUpdated);
		}
		else {
			logger.error("Employee Updated : "+isUpdated);
		}
	}
	
	public void deleteEmployeeByFirstName(String firstName) throws  EmployeeNotFoundException{
		boolean isDeleted=this.repository.deleteByName(firstName);
		if(isDeleted) {
			logger.info("Employee deleted : "+isDeleted);
		}
		else {
			logger.error("Employee deleted : "+isDeleted);
		}
	}
	
	public void displayNameAndMail(LocalDate date) throws EmployeeNotFoundException {
		Map<String,String> nameAndMail=this.repository.getNameAndEmail(date);
		Set<Map.Entry<String,String>> items=nameAndMail.entrySet();
		items.forEach(e->logger.info("Employee Name : "+e.getKey()+" Employee Mail : "+e.getValue()));
	}
	
	public void displayNameAndPhoneNumber(LocalDate date) throws EmployeeNotFoundException {
		Map<String,Long> nameAndPhoneNumber=this.repository.getNameAndPhoneNumber(date);
		Set<Map.Entry<String,Long>> items=nameAndPhoneNumber.entrySet();
		items.forEach(e->logger.info("Employee Name : "+e.getKey()+" Employee Phone Number : "+e.getValue()));
	}
	
}
