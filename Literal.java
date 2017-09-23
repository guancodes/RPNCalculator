package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Objects;

/**
 * A literal instruction. When executed, will push a value provided to the stack.
 * @author Guan Wang
 * @version 1.0
 */
public class Literal implements Instruction{
	
	//Private
	
	private long val;
	private Literal(long val) {
		this.val = val;
	}
	
	//Factory
	
	/**
	 * Creates a new literal instruction which, when executed, will push val to a given stack
	 * @param val The value of type long that's going to be pushed into a given stack when executed
	 * @return a new Literal object
	 */
	public static Literal of(long val) {
		return new Literal(val);
	}
	
	//Queries
	
	/**
	 * Gives us the value that's going to be pushed into the stack
	 * @return the value being pushed
	 */
	public long val() {
		return val;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Literal ) {
			Literal other = (Literal) o;
			return this.val == other.val;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(val);
	}
	
	@Override
	public String toString() {
		return String.valueOf(val);
	}
	
	//Command
	
	@Override
	public void execute(Stack<Long> stack) {
		Objects.requireNonNull(stack);
		stack.push(val);		
	}

}
