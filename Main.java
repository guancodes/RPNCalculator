package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Scanner;

/**
 * This is an RPN calculator. A CUI of the RPN calculator with welcoming messages and
 * a simple instruction is provided as an interactive environment for the user. It will
 * operate on whole numbers, and implement a range of basic maths operations including plus,
 * minus, times, divide, exponential, and dot which displays the result of the calculation.
 * And the signs of the operators are "+", "-", "*", "/", "^", and "." respectively. It expects
 * its input in postfix notation. e.g. "1 + 2 * 3 =" should be input as "2 3 * 1 + ." It should
 * always start with two whole numbers followed by an operator, then repeat the pattern "number
 * operator", and end with a display operator ".". "." operator is crucial for the display of
 * the result, and can be used anywhere in the input except for at the beginning. If extra spaces
 * were inserted accidentally, the calculator will ignore them automatically for you. If wrong input
 * pattern were given, error messages are provided to fix it. e.g. "2 3 * /" will invoke the error
 * message "Expected number at position 3 instead got: /". When doing exponentiation calculation,
 * it should be noted that only non-negative exponent is accepted. e.g. "2 -1 ^" will invoke the
 * error message "Exponent must be non-negative" Comments can also be added at the end of the
 * program following the # key and will be ignored when run. e.g. "2 3 * 1 +. #answer should be 7"
 * will run perfectly fine.
 * @author Guan Wang
 * @version 1.0
 */
public class Main {
	
	public static void main(String[] args) {
		System.out.println("Welcome to RPN calculator (type q to quit)");
		System.out.println("Reminder: it expects its input in postfix notation");
		System.out.println("For example: \n1 + 2 * 3 = should be input as " + 
				"2 3 * 1 + . \n\".\" is crucial for the display of the result");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print(">>> ");
			String input = scanner.nextLine();
			if (input.equals("q")) {
				System.out.println("Exiting...");
				break;
			}
			try {
				Program program = Program.compile(input, new ConsolePrinter());
				Buffer<Long> buffer = Buffer.make(10);
				program.run(buffer);
			} catch (IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage());
			} catch (ArithmeticException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		scanner.close();
	}

}
