package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Objects;

/**
 * A plus instruction. When executed, this should take the top two values on the stack passed in, 
 * add them, and push the answer back on the stack
 * @author Guan Wang
 * @version 1.0
 */
public class Plus implements Instruction{

	//Private
	
	private Plus(){}
	
	private static void checkNotEmpty (Stack<Long> stack, String name) {
		if (stack.size() == 0) {
			throw new IllegalArgumentException(name + " must not be empty");
		}
	}
	
	//Factory
	
	/**
	 * Creates a new plus instruction 
	 * @return a new Plus object
	 */
	public static Plus make() {
		return new Plus();
	}
	
	//Commands
	
	@Override
	public void execute(Stack<Long> stack) {
		Objects.requireNonNull(stack);
		checkNotEmpty(stack, "stack");
		if (stack.size() == 1) {
			return;
		} else {
			long a = stack.top();
			stack.pop();
			long b = stack.top();
			stack.pop();
			stack.push(a + b);
		}
	}

}
