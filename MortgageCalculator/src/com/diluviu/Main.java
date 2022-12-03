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
		
		double mortgage =calculateMortgage (principal, annIntRate, period); 
		
		System.out.println("Mortgage:" + NumberFormat.getCurrencyInstance().format(mortgage));
		// 16:47:10
	}
	
	public static double calculateMortgage (int principal, 
			float annIntRate, 
			byte years) {

		final byte MONTHS_IN_YEAR = 12;
		final byte PERCENTS = 100;
		
		float mountIntRate = annIntRate / MONTHS_IN_YEAR / PERCENTS;
		short numOfPayments = (short) (years * MONTHS_IN_YEAR);

		double mortgage = principal * (mountIntRate * (Math.pow(1 + mountIntRate, numOfPayments))
				/ (Math.pow(1 + mountIntRate, numOfPayments) - 1));
		
		return mortgage; 
	}
	
	public static double readNumber(String prompt, double min, double max) { 
		Scanner scanner = new Scanner(System.in);
		NumberFormat nf = NumberFormat.getInstance();
		double value;
		
		while (true) {
			System.out.print(prompt);
			value = scanner.nextInt();
			if (value >= min && value <= max)
				break;
			System.out.println("Enter a number between " + nf.format(min) + " and "+ nf.format(max));
		}
		return value;
	}
}
