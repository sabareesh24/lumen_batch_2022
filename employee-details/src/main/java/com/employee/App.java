package com.employee;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.*;

import com.employee.entity.Employee;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.services.EmployeeServices;
import com.employee.utils.ConnectionFactory;

public class App {
	private EmployeeServices services;
	private Connection connection;
	private static final Logger logger=LogManager.getLogger();
	public App() {
		super();
		this.connection=ConnectionFactory.getMySqlConnection();
		this.services=new EmployeeServices(this.connection);
	}
	
	public LocalDate marriedOrUnmarried(Scanner scanner,LocalDate weddingDate) {
		logger.info("Married/UnMarried - Y/N : ");
		
		char isMarried=scanner.nextLine().charAt(0);
		switch (isMarried) {
			case 'Y':
			case 'y':
				logger.info("Enter the Wedding Date in (YYYY-MM-DD) Format : ");
				weddingDate = LocalDate.parse(scanner.nextLine());
				break;
			case 'N':
			case 'n':
				break;
			default:
				logger.info("Invalid choice");
				weddingDate=marriedOrUnmarried(scanner, weddingDate);
				break;
		}
		return weddingDate;
	}

	public void getInputAndOutput(Scanner scanner) {
        while(true) {
        	logger.info("------------------>Employee Application<-------------------\n");
        	logger.info("1-->Add Employee Details\n2-->Display List of Employees by the Firstname\n3-->Display List of Employees with Firstname and Phone number\n4-->Update the email and phone number of a particular employee\n5-->Delete details of a particular employee by Firstname\n6-->List of employees with firstname and email address whose birthday falls on the given date\n7-->Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date\n");
        	int choice=0;
        	logger.info("Enter any choice from 1 to 7");
        	choice=Integer.parseInt(scanner.nextLine());
        	switch (choice) {
				case 1:{
					logger.info("----------------->1)Add Employee Details<-----------------");
					logger.info("Enter the first name : ");
					String firstName=scanner.nextLine();
					logger.info("Enter the last name : ");
					String lastName=scanner.nextLine();
					logger.info("Enter the address : ");
					String address=scanner.nextLine();
					logger.info("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					logger.info("Enter the phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					logger.info("Enter the Date of Birth in (YYYY-MM-DD) Format : ");
					LocalDate dateOfBirth=LocalDate.parse(scanner.nextLine());
					LocalDate weddingDate=null;
					weddingDate=marriedOrUnmarried(scanner,weddingDate);
					this.services.addEmployee(new Employee(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate));
					break;
				}	
				case 2:{
					logger.info("----------------->2)List of employees by their firstName<-----------------");
					String firstName=scanner.nextLine();
					try {
						this.services.findByName(firstName);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{
					logger.info("----------------->3)List of employees with FirstName and Phone Number<-----------------");
					try {
						this.services.displayNameAndPhoneNumber();
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}	
				case 4:{
					logger.info("----------------->4)Update the email and phoneNumber of a particular employee<-----------------");
					logger.info("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					logger.info("Enter the new mail id : ");
					String newEmailAddress=scanner.nextLine();
					logger.info("Enter the new phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					try {
						this.services.updateEmailAndPhoneNumber(emailAddress,newEmailAddress,phoneNumber);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 5:{
					logger.info("----------------->5)Delete Details of a Particular employee by firstName<-----------------");
					logger.info("Enter the first name : ");
					String firstName=scanner.nextLine();
					try {
						this.services.deleteEmployeeByFirstName(firstName);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 6:{
					logger.info("----------------->6)list of employees with their firstName and emailAddress  whose Birthday falls on the given date<-----------------");
					logger.info("Enter the date (YYYY-MM-DD) Format : ");
					LocalDate date=LocalDate.parse(scanner.nextLine());
					try {
						this.services.displayNameAndMail(date);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 7:{
					logger.info("----------------->7)list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date<-----------------");
					logger.info("Enter the date (YYYY-MM-DD) Format : ");
					LocalDate date=LocalDate.parse(scanner.nextLine());
					try {
						this.services.displayNameAndPhoneNumber(date);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				default:{
					logger.error("Give a valid choice");
					break;
				}
			}
        	logger.info("Do you want to continue 'Y' ---> Continue / 'N' ---> Exit :");
        	char notExitChoice=scanner.nextLine().charAt(0);
        	if(notExitChoice=='N' || notExitChoice=='n') {
        		logger.info("You have successfully exited");
                scanner.close();
        		return;
        	}
        	else if(notExitChoice=='Y'|| notExitChoice=='Y') {
        		getInputAndOutput(scanner);
        	}
        }	
        
	}
	
    public static void main( String[] args ){
    	App object=new App();
        Scanner scanner=new Scanner(System.in);
        object.getInputAndOutput(scanner);
        
    }
}
