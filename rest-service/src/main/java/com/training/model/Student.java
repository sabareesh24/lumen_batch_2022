package com.training.model;

public class Student implements Comparable<Student>{

	private int rollNumber;
	private String firstName;
	private double markScored;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int rollNumber, String firstName, double markScored) {
		super();
		this.rollNumber = rollNumber;
		this.firstName = firstName;
		this.markScored = markScored;
	}
	/**
	 * @return the rollNumber
	 */
	public int getRollNumber() {
		return rollNumber;
	}
	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the markScored
	 */
	public double getMarkScored() {
		return markScored;
	}
	/**
	 * @param markScored the markScored to set
	 */
	public void setMarkScored(double markScored) {
		this.markScored = markScored;
	}
	@Override
	public String toString() {
		return rollNumber+","+ firstName+","+ markScored ;
	}
	@Override
	public int compareTo(Student otherObj) {

		if( markScored < otherObj.markScored) return -1;
		if(markScored>otherObj.markScored) return 1;
		return 0;
	}
	
	
}
