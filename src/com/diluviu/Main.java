package com.diluviu;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int principal;
		float annIntRate;
		byte period;
		
		principal = (int)  readNumber ("Principal ($1K - $1M): ", 1000, 1_000_000);
		annIntRate = (float) readNumber ("Annual Interest Rate: ", 1, 30);
		period = (byte)  readNumber ("Period (Years): ", 1, 30);
		
		float mountIntRate = calculateMountInterestRate(annIntRate);
		short totalNumberOfPayments = calculateTotalNumberOfPayments(period);
		
		double mortgage = calculateMortgage (principal, mountIntRate, totalNumberOfPayments); 
		
		System.out.println("\nMORTAGE\n--------\nMonthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage)+"\n");
		System.out.println("PAYMENT SCHEDULE\n----------------");
		for (short i = 1; i <= totalNumberOfPayments; i++) {
			double remainingBalance = calculateRemainingBalance(principal, mountIntRate,totalNumberOfPayments, i);
			System.out.println(NumberFormat.getCurrencyInstance().format(remainingBalance));
		}
	}
	
	public static double calculateMortgage (int principal, 
			float mountIntRate, 
			short totalNumberOfPayments) {

		double mortgage = principal * (mountIntRate * (Math.pow(1 + mountIntRate, totalNumberOfPayments))
				/ (Math.pow(1 + mountIntRate, totalNumberOfPayments) - 1));
		
		return mortgage; 
	}
	
	public static double readNumber(String prompt, double min, double max) { 
		Scanner scanner = new Scanner(System.in);
		NumberFormat nf = NumberFormat.getInstance();
		double value;
		
		while (true) {
			System.out.print(prompt);
			value = scanner.nextDouble();
			if (value >= min && value <= max)
				break;
			System.out.println("Enter a number between " + nf.format(min) + " and "+ nf.format(max));
		}
		return value;
	}
	
	public static double calculateRemainingBalance(int principal, float mountIntRate, short totalNumberOfPayments, short numbOfMadePayments) {
		double remainingBalance = principal * (Math.pow(1 + mountIntRate, totalNumberOfPayments) - (Math.pow(1 + mountIntRate, numbOfMadePayments))) 
				/ (Math.pow(1 + mountIntRate, totalNumberOfPayments) - 1);
		
		return remainingBalance;
	} 
	
	public static float calculateMountInterestRate(float annIntRate) {
		final byte MONTHS_IN_YEAR = 12;
		final byte PERCENTS = 100;
		float mountIntRate = (float) (annIntRate / MONTHS_IN_YEAR / PERCENTS);
		return mountIntRate;
	}
	public static short calculateTotalNumberOfPayments(short years) {
		final byte MONTHS_IN_YEAR = 12;
		short totalNumberOfPayments = (short) (years * MONTHS_IN_YEAR);
		return totalNumberOfPayments; 
	}
	
}
