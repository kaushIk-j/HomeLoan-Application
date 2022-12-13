package com.cg.homeloan.util;

public class EMICalculator {

	private double loanAmount;
	private double rateOfInterest;
	private int tenure;
	
	public double getEMIAmount() {
		//System.out.println(loanAmount+" "+rateOfInterest+" "+tenure);
		 return (loanAmount*rateOfInterest*Math.pow(1+rateOfInterest, tenure))/(Math.pow(1+rateOfInterest, tenure)-1);
		//return (loanAmount+(loanAmount*rateOfInterest))/(tenure);
	}

	public EMICalculator() {
		super();
		
	}

	public EMICalculator(double loanAmount, double rateOfInterest, int tenure) {
		super();
		this.loanAmount = loanAmount;
		this.rateOfInterest = rateOfInterest/(12*100);
		this.tenure = tenure*12;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest/(12*100);
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure*12;
	}

	@Override
	public String toString() {
		return "EmiCalculator [loanAmount=" + loanAmount + ", rateOfInterest=" + rateOfInterest + ", tenure=" + tenure
				+ "]";
	}
	
}
