package edu.ncsu.csc316.dsa.stack;

/**
 * Holds the is empty method for the linked stack class
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic parameter
 */
public abstract class AbstractStack<E> implements Stack<E> {

	/**
	 * Check to see if the stack is empty
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
