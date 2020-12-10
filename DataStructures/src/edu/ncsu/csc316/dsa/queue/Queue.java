package edu.ncsu.csc316.dsa.queue;

/**
 * Queue interface to hold all methods implemented in queue
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic parameter
 */
public interface Queue<E> {

	/**
	 * add an element to the end of the queue
	 * 
	 * @param value to remove
	 */
	void enqueue(E value);

	/**
	 * remove an element from the beginning of the queue
	 * 
	 * @return the removed element
	 */
	E dequeue();

	/**
	 * returns an element from the beginning of the queue
	 * 
	 * @return the element at the beginning of the queue
	 */
	E front();

	/**
	 * returns the size of the queue
	 * 
	 * @return size of queue
	 */
	int size();

	/**
	 * checks to see if a queue is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();
}
