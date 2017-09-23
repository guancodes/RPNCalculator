package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * An array-based circular buffer.
 * @author Guan Wang
 * @version 1.0
 * @param <T> the type of elements stored.
 */
public class Buffer <T> implements Stack<T>{
	
	//Private
	
	private Object[] base;
	private int capacity;
	private int front;
	private int size;
	
	private Buffer(int capacity) {
		this.capacity = capacity;
		front = 0;
		size = 0;
		base = new Object[capacity];
		for (int i=0; i<capacity; i++ ){
			base[i] = Optional.empty();
		}
 	}
	
	@SuppressWarnings("unchecked")
	private Optional<T> safeAt(int index) {
		return (Optional<T>) base[index];
	}
	
	private void newValue(int index, Optional<T> value) {
		base[index] = value;
	}
	
	private static void checkNotZeroOrNegative (int x, String name) {
		if (x <= 0) {
			throw new IllegalArgumentException(name + " must not be zero or negative");
		}
	}
	
	//Factory
	
	/**
	 * Makes a new empty circular buffer
	 * @param capacity The total spots in the circular buffer
	 * @return an empty circular buffer with the given capacity.
	 */
	public static <T> Buffer<T> make(int capacity){ 
		checkNotZeroOrNegative(capacity, "capacity");
		return new Buffer<T>(capacity);
	}
	
	// Queries
	
	/**
	 * Gives us the capacity of the buffer
	 * @return the total number of spots in the buffer
	 */
	public int capacity() {
		return capacity;
	}
	
	@Override
	public T top() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Optional<T> result;
		if (front == 0) {
			result = safeAt(capacity - 1);
		} else {
			result = safeAt(front - 1);
		}
		return result.get();
	}
	

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o instanceof Buffer<?> ) {
			Buffer<?> other = (Buffer<?>)o;
			if(other.size() != this.size() || other.front != this.front) {
				return false;
			}
			else {
					for (int i = 0; i < other.size; i++) {
					if (!safeAt(i).equals(other.safeAt(i))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash_code = 0;
		for (int i=0; i<this.size(); i++) {
			hash_code = Objects.hash(hash_code, safeAt(i), front);
		}
		return hash_code;
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		for (int i=front; i<capacity(); i++) {
			if (this.safeAt(i).isPresent()) {
				sb.append(safeAt(i).get().toString());
				sb.append(" ");
			}
		}
		for (int i=0; i<=front-1; i++) {
			if (this.safeAt(i).isPresent()) {
				sb.append(safeAt(i).get().toString());
				sb.append(" ");
			} 
		}
		return sb.toString();
	}
	
	//Commands
	
	@Override
	public void push(T data) {
		Objects.requireNonNull(data);
		newValue(front, Optional.of(data));
		size++;
		size = Math.min(size, capacity);
		if (front == capacity - 1) {
			front = 0;
		} else {
			front++;
		}
	}

	@Override
	public void pop() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (front == 0) {
			front = capacity - 1;
		} else {
			front--;
		}
		newValue(front, Optional.empty());
		size--;
	}

}
