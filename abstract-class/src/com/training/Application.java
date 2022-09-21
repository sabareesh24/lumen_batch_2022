package com.training;

public class Application {
    public static void main(String[] args) {
        Loan applicant1=new HousingLoan("RamKumar",200000,20,1);
        Loan applicant2=new HousingLoan("Sabareesh",300000, 22, 6);
        Loan applicant3=new HousingLoan("Sachin",400000, 22, 5);
        Loan applicant4=new HousingLoan("Tendulkar",500000, 22, 2);
        Loan applicant5=new PersonalLoan("Chandru",200000,20,"teacher");
        Loan applicant6=new PersonalLoan("Kavin",300000, 22, "student");
        Loan applicant7=new PersonalLoan("Mohan",400000, 22, "teacher");
        Loan applicant8=new PersonalLoan("Rakesh",500000, 22, "developer");
        LoanService service=new LoanService();
        System.out.println(service.addLoan(applicant1));
        System.out.println(service.addLoan(applicant2));
        System.out.println(service.addLoan(applicant3));
        System.out.println(service.addLoan(applicant4));
        System.out.println(service.addLoan(applicant5));
        System.out.println(service.addLoan(applicant6));
        System.out.println(service.addLoan(applicant7));
        System.out.println(service.addLoan(applicant8));
        Loan[] loans=service.getAll();
        for(Loan eachLoan: loans) {
            System.out.println(eachLoan.calculateInterest());
        }
    }
}
