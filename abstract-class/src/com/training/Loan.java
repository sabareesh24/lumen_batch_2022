package com.training;

public abstract class Loan {
	
	private String applicantName;
	private double loanAmount;
	private double tenure;
	
	public abstract double calculateInterest();
	
	public Loan(String applicantName, double loanAmount, double tenure) {
		super();
		this.applicantName = applicantName;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getTenure() {
		return tenure;
	}

	public void setTenure(double tenure) {
		this.tenure = tenure;
	}

	
	
	
}
