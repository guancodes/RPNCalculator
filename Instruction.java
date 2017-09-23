package nz.ac.aut.wbz8656.dsa2017.assignment02;

/**
 * Interface for an instruction to execute on a stack.
 * @author Guan Wang
 * @version 1.0
 * @param stack The given stack to be executed on.
 */
public interface Instruction {
	
	//Command
	
	/**
	 * Executes on a stack according to the corresponding instruction.
	 * @param stack The stack to be executed on
	 */
	public abstract void execute (Stack<Long> stack);
}
