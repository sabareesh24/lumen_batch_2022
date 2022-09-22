package com.employee;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

import com.employee.entity.Employee;
import com.employee.services.EmployeeServices;
import com.employee.utils.ConnectionFactory;

public class App {
	private EmployeeServices services;
	private Connection connection;
	public App() {
		super();
		this.connection=ConnectionFactory.getMySqlConnection();
		this.services=new EmployeeServices(this.connection);
	}
	
	public long getPhoneNumber(Scanner scanner) {
		try {
			System.out.println("Enter the phone number : ");
			long phoneNumber=Long.parseLong(scanner.nextLine());
			return phoneNumber;
			
		}
		catch(NumberFormatException e) {
			System.err.println("Phone Number should not contain letters");
			return getPhoneNumber(scanner);
		}
	}
	
	public LocalDate getDateOfBirth(Scanner scanner) {
		try {
			System.out.println("Enter the Date of Birth in (YYYY-MM-DD) Format : ");
			LocalDate dateOfBirth=LocalDate.parse(scanner.nextLine());
			return dateOfBirth;
		}
		catch(Exception e) {
			System.err.println("Please enter the date properly in [YYYY-MM-DD] Format like [2000-12-24]");
			return getDateOfBirth(scanner);
		}
	}
	
	public LocalDate getWeddingDate(Scanner scanner) {
		try {
			System.out.println("Enter the Wedding Date in (YYYY-MM-DD) Format : ");
			LocalDate weddingDate=LocalDate.parse(scanner.nextLine());
			return weddingDate;
		}
		catch(Exception e) {
			System.err.println("Please enter the date properly in [YYYY-MM-DD] Format like [2000-12-24]");
			return getWeddingDate(scanner);
		}
	}
	
	public String getMail(Scanner scanner) {
		System.out.println("Enter the mail id : ");
		String emailAddress=scanner.nextLine();
		if(emailAddress.isEmpty()) {
			System.err.println("Email can't be empty");
			return getMail(scanner);
		}
		return emailAddress;
	}
	
	public LocalDate marriedOrUnmarried(Scanner scanner,LocalDate weddingDate) {
		System.out.println("Married/UnMarried - Y/N : ");
		
		char isMarried=scanner.nextLine().charAt(0);
		switch (isMarried) {
			case 'Y':
			case 'y':
				weddingDate = getWeddingDate(scanner);
				break;
			case 'N':
			case 'n':
				break;
			default:
				System.out.println("Invalid choice");
				weddingDate=marriedOrUnmarried(scanner, weddingDate);
				break;
		}
		return weddingDate;
	}

	public void getInputAndOutput(Scanner scanner) {
        while(true) {
        	System.out.println("------------------>Employee Application<-------------------\n");
        	System.out.println("1-->Add Employee Details\n2-->Display List of Employees by the Firstname\n3-->Display List of Employees with Firstname and Phone number\n4-->Update the email and phone number of a particular employee\n5-->Delete details of a particular employee by Firstname\n6-->List of employees with firstname and email address whose birthday falls on the given date\n7-->Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date\n");
        	int choice=0;
        	System.out.println("Enter any choice from 1 to 7");
        	choice=Integer.parseInt(scanner.nextLine());
        	switch (choice) {
				case 1:{
					System.out.println("----------------->1)Add Employee Details<-----------------");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					System.out.println("Enter the last name : ");
					String lastName=scanner.nextLine();
					System.out.println("Enter the address : ");
					String address=scanner.nextLine();
					String emailAddress=getMail(scanner);
					long phoneNumber=getPhoneNumber(scanner);
					LocalDate dateOfBirth=getDateOfBirth(scanner);
					LocalDate weddingDate=null;
					weddingDate=marriedOrUnmarried(scanner,weddingDate);
					this.services.addEmployee(new Employee(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate));
					break;
				}	
				case 2:{
					System.out.println("----------------->2)List of employees by their firstName<-----------------");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					this.services.findByName(firstName);
					break;
				}
				case 3:{
					System.out.println("----------------->3)List of employees with FirstName and Phone Number<-----------------");
					this.services.displayNameAndPhoneNumber();
					break;
				}	
				case 4:{
					System.out.println("----------------->4)Update the email and phoneNumber of a particular employee<-----------------");
					System.out.println("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					System.out.println("Enter the new mail id : ");
					String newEmailAddress=scanner.nextLine();
					System.out.println("Enter the new phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					this.services.updateEmailAndPhoneNumber(emailAddress,newEmailAddress,phoneNumber);
					break;
				}
				case 5:{
					System.out.println("----------------->5)Delete Details of a Particular employee by firstName<-----------------");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					System.out.println("Enter the email : ");
					String emailAddress=scanner.nextLine();
					this.services.deleteEmployeeByFirstName(firstName,emailAddress);
					break;
				}
				case 6:{
					System.out.println("----------------->6)list of employees with their firstName and emailAddress  whose Birthday falls on the given date<-----------------");
					LocalDate date=getDateOfBirth(scanner);
					this.services.displayNameAndMail(date);
					break;
				}
				case 7:{
					System.out.println("----------------->7)list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date<-----------------");
					LocalDate date=getWeddingDate(scanner);
					this.services.displayNameAndPhoneNumber(date);
					break;
				}
				default:{
					System.err.println("Give a valid choice");
					break;
				}
			}
        	System.out.println("Do you want to continue 'Y' ---> Continue / 'N' ---> Exit :");
        	char notExitChoice=scanner.nextLine().charAt(0);
        	if(notExitChoice=='N' || notExitChoice=='n') {
        		System.out.println("You have successfully exited");
        		return;
        	}
        	else if(notExitChoice=='Y'|| notExitChoice=='y') {
        		getInputAndOutput(scanner);
        		return;
        	}
        }	
        
	}
	
    public static void main( String[] args ){
    	App object=new App();
        Scanner scanner=new Scanner(System.in);
        object.getInputAndOutput(scanner);
        scanner.close();
        
    }
}
