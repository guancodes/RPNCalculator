package nz.ac.aut.wbz8656.dsa2017.assignment02;
/**
 * Compiles a string into a program, and allows a user to run it.
 * @author Guan Wang
 * @version 1.0
 */
public class Program {
	
	//Private
	
	private ArrayList<Instruction> instructions;
	
	private Program(ArrayList<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	//Factory
	
	/**
	 * Takes the string src, and a printable. After checking that it represents a valid program,
	 * it returns a Program which can be executed.
	 * @param src The string that represents a program
	 * @param printer A printable
	 * @return a Program that is compiled and is prepared to be run
	 * @throws IllegalArgumentException
	 */
	public static Program compile (String src, Printable printer) throws IllegalArgumentException {
		ArrayList<Instruction> instructions = ArrayList.empty();
		String[] parts = src.split("#")[0].split(" ");
		int index = 0;
		int count = 0;
		for (String p : parts) {
			if (p.length() > 0) {
				boolean isNumber = false;
				boolean isOperator = false;
				boolean isDot = false;
				try {
					Long value = Long.valueOf(p);
					instructions.add(Literal.of(value), index);
					isNumber = true;
				} catch (NumberFormatException e) {
					if (p.equals("+")) {
						instructions.add(Plus.make(), index);
						isOperator = true;
					} else if (p.equals("-")) {
						instructions.add(Minus.make(), index);
						isOperator = true;
					} else if (p.equals("*")) {
						instructions.add(Times.make(), index);
						isOperator = true;
					} else if (p.equals("/")) {
						instructions.add(Divide.make(), index);
						isOperator = true;
					} else if (p.equals("^")) {
						instructions.add(Pow.make(), index);
						isOperator = true;
					} else if (p.equals(".")) {
						instructions.add(Dot.make(printer), index);
						isDot = true;
					} else {
						throw new IllegalArgumentException("Invalid token: " + p);
					}
				}
				if (count == 0) {
					if (!isNumber) {
						throw new IllegalArgumentException("Expected number at positon " + count + " instead got: " + p);
					}
				} else if (count == 1) {
					if (!isNumber && !isDot) {
						throw new IllegalArgumentException("Expected number or dot at position " + count + " instead got: " + p);
					} else if (isDot) {
						count++;
					}
				} else if (isDot) {
					count++;
				} else {
					if (count % 2 == 0) { // even
						if (!isOperator) {
							throw new IllegalArgumentException("Expected operator at position " + count + " instead got: " + p);
						}
					} else { // odd
						if (!isNumber) {
							throw new IllegalArgumentException("Expected number at position " + count + " instead got: " + p);
						}
					}
				}
				count++;
				index++;
			}
		}
		return new Program(instructions);
	}
	
	//Command
	
	/**
	 * Runs the program
	 * @param stack Given stack that instructions are to be executed on
	 */
	public void run (Stack<Long> stack) {
		for (int i=0; i<instructions.size(); i++) {
			instructions.unsafeAt(i).execute(stack);
		}
	}
}
