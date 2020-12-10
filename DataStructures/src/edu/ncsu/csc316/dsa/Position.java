package edu.ncsu.csc316.dsa;

/**
 * Position interface to get the element at a specific position
 * 
 * @author Sanjana Cheerla
 *
 * @param <E> generic parameter
 */
public interface Position<E> {

	/**
	 * Get the element at a specific position
	 * 
	 * @return element at position
	 */
	E getElement();
}
