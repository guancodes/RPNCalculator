package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Optional;

/**
 * Interface for a simple linear structure.
 * @author Guan Wang
 * @version 1.0
 * @param <T> the type of element stored.
 */
public interface List<T> {
	
	// Queries
	
	/**
	 * Attempts to retrieve the element at index i.
	 * @param i the index to try (cannot be negative).
	 * @return the element at the index i, or Optional.empty if this index is past the end
	 */
	public abstract Optional<T> safeAt (int i);
	
	/**
	 * Retrieves the element at index i, or throws an exception.
	 * @param i the index to try (must be in bounds).
	 * @return the element at index i.
	 */
	public abstract T unsafeAt (int i);
	
	/**
	 * @return the number of elements in the structure.
	 */
	public abstract int size ();
	
	/**
	 * Conversion method.
	 * @return an array with the same contents as this structure, in the same order.
	 */
	public abstract Object[] toArray ();
	
	// Commands

	/**
	 * Attempts to add an element at the given position, 'sliding over' whatever is already there
	 * @param what the thing to add (cannot be null).
	 * @param where the index to add at (must be between 0 and the number of elements in this structure)
	 */
	public abstract void add (T what, int where);
	
	/**
	 * Attempts to remove the element at the given position, 'sliding back' whatever is in front of it to replace it
	 * @param where the index to remove at (must be between 0 and the number of elements in this structure - 1)
	 */
	public abstract void remove (int where);
}
