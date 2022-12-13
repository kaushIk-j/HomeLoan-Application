package com.cg.homeloan.util;

public class HomeLoanBorrowingAmountCalculator {

	private double loanAppliedAmount;
	private double rateOfInterest;
	private int tenure;
	private double totalAnnualIncome;
	private double monthlyExpenses;
	private double otherMonthlyExpenses;

	public int getHomeLoanBorrowingAmount() {
		double left = ((totalAnnualIncome / 12) - (monthlyExpenses + otherMonthlyExpenses));
	    double emical = new EMICalculator(loanAppliedAmount, rateOfInterest, tenure).getEMIAmount();
	    if (left/2 > emical) {
	    	return 1;
	    }
		return 0;
	}

	public HomeLoanBorrowingAmountCalculator() {
		super();
	}

	

	public double getLoanAppliedAmount() {
		return loanAppliedAmount;
	}

	public void setLoanAppliedAmount(double loanAppliedAmount) {
		this.loanAppliedAmount = loanAppliedAmount;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public double getTotalAnnualIncome() {
		return totalAnnualIncome;
	}

	public void setTotalAnnualIncome(double totalAnnualIncome) {
		this.totalAnnualIncome = totalAnnualIncome;
	}

	public double getMonthlyExpenses() {
		return monthlyExpenses;
	}

	public void setMonthlyExpenses(double monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}

	public double getOtherMonthlyExpenses() {
		return otherMonthlyExpenses;
	}

	public void setOtherMonthlyExpenses(double otherMonthlyExpenses) {
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}

	@Override
	public String toString() {
		return "HomeLoanBorrowingAmountCalculator [ rateOfInterest=" + rateOfInterest
				+ ", tenure=" + tenure + ", totalAnnualIncome=" + totalAnnualIncome + ", monthlyExpenses="
				+ monthlyExpenses + ", otherMonthlyExpenses=" + otherMonthlyExpenses + "]";
	}

}
// Author : 