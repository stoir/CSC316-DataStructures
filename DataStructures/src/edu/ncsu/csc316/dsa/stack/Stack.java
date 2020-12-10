package edu.ncsu.csc316.dsa.stack;

/**
 * Stack interface to hold all methods implemented in stack
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic parameter
 */
public interface Stack<E> {

	/**
	 * adds an element to the top of the stack
	 * 
	 * @param value to be added to the top of the stack
	 */
	void push(E value);

	/**
	 * remove an element from the top of the stack
	 * 
	 * @return removed element from the top of the stack
	 */
	E pop();

	/**
	 * returns the element at the top of the stack
	 * 
	 * @return the value at the top of the stack
	 */
	E top();

	/**
	 * return the size of the stack
	 * 
	 * @return the size of the stack
	 */
	int size();

	/**
	 * check if the stack is empty
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	boolean isEmpty();
}