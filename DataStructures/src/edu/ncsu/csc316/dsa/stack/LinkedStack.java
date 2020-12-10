package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * Stack that is implemented using a the singly linked list class, extends
 * abstract stack class for isEmpty() method
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic parameter
 */
public class LinkedStack<E> extends AbstractStack<E> {

	/**
	 * the singly linked list of the stack, holds data.
	 */
	private SinglyLinkedList<E> list;

	/**
	 * construct a new stack which is a based off of a singly linked list
	 */
	public LinkedStack() {
		list = new SinglyLinkedList<E>();
	}

	/**
	 * adds an element to the top of the stack
	 * 
	 * @param value to be added to the top of the stack
	 */
	@Override
	public void push(E value) {
		list.addFirst(value);

	}

	/**
	 * remove an element from the top of the stack
	 * 
	 * @return removed element from the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

	/**
	 * returns the element at the top of the stack
	 * 
	 * @return the value at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E top() {
		if (list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	/**
	 * return the size of the stack
	 * 
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

}
