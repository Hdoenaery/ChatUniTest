package practice;

import java.util.*;


public class Addition2 {
	private static Scanner intext;

	public static void main(String[] args) {
		intext = new Scanner(System.in);

		// get first input
		System.out.println("Enter first integer");
		int number1 = intext.nextInt();

		// get second input
		System.out.println("Enter second integer");
		int number2 = intext.nextInt();

		// display output on console
		System.out.println("The sum is " + (number1+number2));
	}
}
