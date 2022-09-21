package com.training;

public class HousingLoan extends Loan {
	
	private int propertyAge;
	
	
	@Override
	public double calculateInterest() {
		if(this.propertyAge<5) {
			System.out.println("Personal loan - Special case if property age < 5");
			return getLoanAmount()*getTenure()*0.10;
		}
		else {
			System.out.println("Personal loan - if property age >= 5");
			return getLoanAmount()*getTenure()*0.12;
		}
	}


	public HousingLoan(String applicantName, double loanAmount, double tenure, int propertyAge) {
		super(applicantName, loanAmount, tenure);
		this.propertyAge = propertyAge;
	}


	


	


	

}
