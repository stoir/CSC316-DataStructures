package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * the positional list interface that hholds the methods for positionalList and
 * extends the iterable interface
 * 
 * @author sanjana cheerla
 *
 * @param <E> the generic parameter
 */
public interface PositionalList<E> extends Iterable<E> {

	/**
	 * add the specified value after the specified position
	 * 
	 * @param p     the position
	 * @param value the value being added after p
	 * @return the added value at the position
	 */
	Position<E> addAfter(Position<E> p, E value);

	/**
	 * add the specified value before the specified position
	 * 
	 * @param p     the position
	 * @param value the value being added before p
	 * @return the added value at the position
	 */
	Position<E> addBefore(Position<E> p, E value);

	/**
	 * add the specified value at the beginning
	 * 
	 * @param value the value being added at the beginning
	 * @return the added value at the position
	 */
	Position<E> addFirst(E value);

	/**
	 * add the specified value at the end
	 * 
	 * @param value the value being added at the end
	 * @return the added value at the position
	 */
	Position<E> addLast(E value);

	/**
	 * get the element after the position
	 * 
	 * @param p the position
	 * @return the node after p
	 */
	Position<E> after(Position<E> p);

	/**
	 * get the element before the position
	 * 
	 * @param p the position
	 * @return the node before p
	 */
	Position<E> before(Position<E> p);

	/**
	 * get the element at the beginning
	 * 
	 * @return the node at the beginning
	 */
	Position<E> first();

	/**
	 * check if the list is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * get the element at the end
	 * 
	 * @return the node at the end
	 */
	Position<E> last();

	/**
	 * create a iterable for position
	 * 
	 * @return the iterable object for positions
	 */
	Iterable<Position<E>> positions();

	/**
	 * remove element at position p
	 * 
	 * @param p the position
	 * @return the removed element
	 */
	E remove(Position<E> p);

	/**
	 * remove element at position p
	 * 
	 * @param p     the position
	 * @param value being set
	 * @return the removed element
	 */
	E set(Position<E> p, E value);

	/**
	 * get the size of the list
	 * 
	 * @return the size
	 */
	int size();
}