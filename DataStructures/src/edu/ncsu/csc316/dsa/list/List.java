package edu.ncsu.csc316.dsa.list;

/**
 * List interface that extends iterable, contains add, addFirst, addLast, first,
 * get, isEmpty, last, removeFist, removeLast, set, and size
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic parameter
 */
public interface List<E> extends Iterable<E> {

	/**
	 * add specified value at the specified index
	 * 
	 * @param index that that value is being added
	 * @param value that is being added at the index
	 */
	void add(int index, E value);

	/**
	 * add to the beginning of the list
	 * 
	 * @param value being added to the beginning
	 */
	void addFirst(E value);

	/**
	 * add to the end of the list
	 * 
	 * @param value being added to the end of the list
	 */
	void addLast(E value);

	/**
	 * return the first element of the list
	 * 
	 * @return first element
	 */
	E first();

	/**
	 * get the element at the specified index
	 * 
	 * @param index that the element is being get from
	 * @return the element at the specific index
	 */
	E get(int index);

	/**
	 * check to see if the list is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * get the last element of the list
	 * 
	 * @return the last element of the list
	 */
	E last();

	/**
	 * remove the element at the specified index
	 * 
	 * @param index that the element is being removed from
	 * @return the element at the index
	 */
	E remove(int index);

	/**
	 * remove the first element in the list
	 * 
	 * @return the first element of the list that is removed
	 */
	E removeFirst();

	/**
	 * remove the last element in the list
	 * 
	 * @return the last element of the list that is removed
	 */
	E removeLast();

	/**
	 * sets a specified element at the specified index
	 * 
	 * @param index of the element being set
	 * @param value of the element being set at the index
	 * @return element before being set at index
	 */
	E set(int index, E value);

	/**
	 * get the size of the list
	 * 
	 * @return size of the list
	 */
	int size();
}