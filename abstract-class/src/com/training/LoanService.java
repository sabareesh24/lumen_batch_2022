package com.training;

public class LoanService {
	private static int count=0;
	private Loan loanList[];
	public LoanService() {
		this.loanList=new Loan[4];
		// TODO Auto-generated constructor stub
	}
	public boolean addLoan(Loan loan) {
		if(count<this.loanList.length) {
            loanList[count]=loan;
            count++;
            return true;
        }
		else if(count==this.loanList.length) {
            System.out.println("Array size increased");
            increaseArraySize();
            loanList[count]=loan;
            count++;
            return true;
        }
        return false;
	} 
	public Loan[] getAll() {
		return this.loanList;
	}
	public void increaseArraySize() {
		Loan tempArray[]=new Loan[this.loanList.length*2];
		int index=0;
		for(Loan iterator: this.loanList) {
			tempArray[index]=iterator;
			index++;
		}
		this.loanList=new Loan[tempArray.length];
		this.loanList=tempArray;
	}
	public void printLoanDetails() {
		
	}
}
