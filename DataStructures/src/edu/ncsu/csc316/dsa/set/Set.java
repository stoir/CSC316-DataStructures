package edu.ncsu.csc316.dsa.set;

/**
 * set interface iterable
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic param
 */
public interface Set<E> extends Iterable<E> {
	/**
	 * add value
	 * 
	 * @param value to add
	 */
	void add(E value);

	/**
	 * check to see if value is contained in set
	 * 
	 * @param value to check
	 * @return true if value is in set, false otherwise
	 */
	boolean contains(E value);

	/**
	 * remove value
	 * 
	 * @param value from set
	 * @return removed value
	 */
	E remove(E value);

	/**
	 * check to see if set is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * size of set
	 * 
	 * @return size
	 */
	int size();

	/**
	 * add all elements from set
	 * 
	 * @param other elements to add
	 */
	void addAll(Set<E> other);

	/**
	 * retain all elements from set
	 * 
	 * @param other elements to retain
	 */
	void retainAll(Set<E> other);

	/**
	 * remove all elements from set
	 * 
	 * @param other elements to remove
	 */
	void removeAll(Set<E> other);
}
