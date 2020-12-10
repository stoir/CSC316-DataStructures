package edu.ncsu.csc316.dsa.queue;

/**
 * Holds the is empty method for the array based queue class
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic parameter
 */
public abstract class AbstractQueue<E> implements Queue<E> {

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
