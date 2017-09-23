package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Objects;
import java.util.Optional;

/**
 * An array-based list.
 * @author Guan Wang
 * @version 1.0
 * @param <T> the type of elements stored.
 */
public class ArrayList<T> implements List<T> {

	// Factories
	
	/**
	 * @return an empty structure.
	 */
	public static <T> ArrayList<T> empty () {
		return new ArrayList<T>();
	}
	
	/**
	 * Conversion factory for arrays.
	 * @param arr an array
	 * @return a structure filled with the same elements as arr in the same order
	 */
	public static <T> ArrayList<T> fromArray (T[] arr) {
		Objects.requireNonNull(arr);
		ArrayList<T> arrayList = new ArrayList<T>();
		for(int i=0; i<arr.length; i++) {
			arrayList.add(arr[i], i);
		}
		return arrayList;
	}
	
	/**
	 * Conversion factory for other lists.
	 * @param l a list
	 * @return a structure filled with the same elements as l in the same order
	 */
	public static <T> ArrayList<T> fromList (List<T> l) {
		Objects.requireNonNull(l);
		ArrayList<T> arrayList = new ArrayList<T>();
		for(int i=0; i<l.size(); i++) {
			arrayList.add(l.unsafeAt(i),i);
		}
		return arrayList;
	}
	
	// Queries
	
	@Override
	public Optional<T> safeAt (int i) {
		checkNotNegative(i, "i");
		if (i < size) {
			return Optional.of(base[i]);
		}
		return Optional.empty();
	}
	
	@Override
	public T unsafeAt (int i) {	return base[i]; }

	@Override
	public int size () { return size; }

	@Override
	public Object[] toArray () {
		Object[] arr = new Object[size];
		for (int i = 0; i < size; i++) {
			arr[i] = unsafeAt(i);
		}
		return arr;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o instanceof ArrayList<?> ) {
			ArrayList<?> al = (ArrayList<?>)o;
			if(al.size()!=this.size()) {
				return false;
			}
			else {
					for (int i = 0; i < al.size; i++) {
					if (!unsafeAt(i).equals(al.unsafeAt(i))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode () {
		int hash = 0;
		for (int i = 0; i < size; i++) {
			hash = Objects.hash(hash, unsafeAt(i));
		}
		return hash;
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("ArrayList:");
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < size; i++) {
			sb.append(unsafeAt(i).toString());
			sb.append(" ");
		}
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
	
	// Commands
	
	@Override
	public void add (T what, int where) {
		Objects.requireNonNull(what);
		checkNotNegative(where, "where");
		checkNotGreater(where, "where");
		ensureCapacity();
		if (where != size) {
			for (int i = size - 1; i >= where; i--) {
				base[i + 1] = base[i];
			}
		}
		base[where] = what;
		size++;
	}

	@Override
	public void remove (int where) {
		checkNotNegative(where, "where");
		checkInBounds(where, "where");
		if (where != (size - 1)) {
			for (int i = where; i < size - 1; i++) {
				base[i] = base[i + 1];
			}
		}
		size--;
	}
	
	// Private
	
	private T[] base;
	private int size, capacity;
	
	@SuppressWarnings("unchecked")
	private ArrayList () {
		base = (T[])new Object[16];
		size = 0;
		capacity = 16;
	}
	
	private static void checkNotNegative (int x, String name) {
		if (x < 0) {
			throw new IllegalArgumentException(name + " must not be negative");
		}
	}
	
	private void checkNotGreater (int x, String name) {
		if (x > size) {
			throw new IllegalArgumentException(name + " must not be greater than size");
		}
	}
	
	private void checkInBounds (int x, String name) {
		if (x >= size) {
			throw new IllegalArgumentException(name + " is out of bounds (size - 1)");
		}
	}
	
	private void ensureCapacity () {
		if(capacity<size+1) {
			
			capacity = capacity*2;
			
			@SuppressWarnings("unchecked")
			T[] newBase = (T[])new Object[capacity];
			for(int i=0; i<base.length;i++) {
				newBase[i] = base[i];
			}
			base=newBase;
		}
	}
}
