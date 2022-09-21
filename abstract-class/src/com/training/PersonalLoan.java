package com.training;

public class PersonalLoan extends Loan {

	private String profession;

	public PersonalLoan(String applicantName, double loanAmount, double tenure, String profession) {
		super(applicantName, loanAmount, tenure);
		this.profession = profession;
	}

	@Override
	public double calculateInterest() {
		if(profession.equalsIgnoreCase("teacher")) {
			System.out.println("Personal loan - Special case for teacher");
			return getLoanAmount()*getTenure()*0.10;
		}
		else {
			System.out.println("Personal loan - other profession except teacher");
			return getLoanAmount()*getTenure()*0.12;
		}
	}

}
