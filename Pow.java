package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Objects;
/**
 * A power instruction. When executed, this should take the top two values on the stack passed in, 
 * compute the second value to the power of the first value, and push the answer back on the stack
 * @author Guan Wang
 * @version 1.0
 */
public class Pow implements Instruction{
	
	//Private
	
	private Pow(){}
	
	private static void checkNotEmpty (Stack<Long> stack, String name) {
		if (stack.size() == 0) {
			throw new IllegalArgumentException(name + " must not be empty");
		}
	}
	
	//Factory
	
	/**
	 * Creates a new power instruction 
	 * @return a new Pow object
	 */
	public static Pow make() {
		return new Pow();
	}
		
	//Commands
		
	@Override
	public void execute(Stack<Long> stack) {
		Objects.requireNonNull(stack);
		checkNotEmpty(stack, "Stack");
		if (stack.size() == 1) {
			return;
		} else {
			long a = stack.top();
			if (a < 0) {
				throw new ArithmeticException("Exponent must be non-negative");
			}
			stack.pop();
			long b = stack.top();
			stack.pop();
			stack.push((long)Math.pow(b, a));
		}
	}
}
