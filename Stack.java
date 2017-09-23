package nz.ac.aut.wbz8656.dsa2017.assignment02;

/**
 * Interface for a stack.
 * @author Guan Wang
 * @version 1.0
 * @param <T> the type of element stored.
 */
public interface Stack <T>{
	
	//Commands
	
	/**
	 * Pushes a value onto the top of this stack.
	 * @param data the data to be pushed onto this stack.
	 */
	public abstract void push(T data);
	
	/**
	 * Removes the object at the top of this stack
	 */
	public abstract void pop();
	
	//Queries
	
	/**
	 * Gives us the object at the top of this stack
	 * @return The object at the top of this stack
	 */
	public abstract T top();
	
	/**
	 * Gives us the size of this stack
	 * @return The size of this stack
	 */
	public abstract int size();
}
