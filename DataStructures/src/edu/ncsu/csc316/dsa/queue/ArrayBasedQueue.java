package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * Queue implemented using an array and a circular buffer
 * 
 * some code is from data structures and algorithms in java 6th edition. Book by
 * Michael T. Goodrich and Roberto Tamassia
 * 
 * @author sanjana cheerla 
 *
 * @param <E> the generic parameter
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/**
	 * hold all data for the queue
	 */
	private E[] data;

	/**
	 * front of the queue circular buffer
	 */
	private int front;

	/**
	 * rear of the queue circular buffer
	 */
	private int rear;

	/**
	 * size of the queue
	 */
	private int size;

	/**
	 * default capacity of the queue
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * create a new queue of an initialized array with the provided capacity
	 * 
	 * @param initialCapacity create a queue of the provided capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity) {
		data = (E[]) (new Object[initialCapacity]);
		size = 0;
		front = 0;
		rear = -1;
	}

	/**
	 * create a new queue of an initialized array with the default capacity
	 */
	public ArrayBasedQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * add an element to the end of the queue
	 * 
	 * @param value to remove
	 */
	@Override
	public void enqueue(E value) {
		if (rear + 1 == data.length) {
			rear = 0;
		} else {
			rear += 1;
		}
		data[rear] = value;
		size += 1;
	}

	/**
	 * remove an element from the beginning of the queue
	 * 
	 * @return the removed element
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		E frontVal = this.front();
		size -= 1;
		if (front + 1 == data.length) {
			front = 0;
		} else {
			front += 1;
		}
		return frontVal;
	}

	/**
	 * returns an element from the beginning of the queue
	 * 
	 * @return the element at the beginning of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E front() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	/**
	 * returns the size of the queue
	 * 
	 * @return size of queue
	 */
	@Override
	public int size() {
		return size;
	}

}