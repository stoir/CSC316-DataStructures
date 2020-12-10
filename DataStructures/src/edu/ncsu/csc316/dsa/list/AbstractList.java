package edu.ncsu.csc316.dsa.list;

/**
 * Abstract interface for the Abstract list type which implements list. Holds
 * all common functionality such as addFirst, addLast, checkIndex,
 * checkIndexForAdd, first, last, isEmpty, removeFrist, and removeLast.
 * 
 * @author Sanjana Cheerla
 *
 * @param <E> generic data type
 */
public abstract class AbstractList<E> implements List<E> {

	/**
	 * Add to the beginning
	 * 
	 * @param value being added to the beginning
	 */
	@Override
	public void addFirst(E value) {
		add(0, value);
	}

	/**
	 * Add to the end
	 * 
	 * @param value being added to the end
	 */
	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Check the index to see if the index is valid
	 * 
	 * @param index being checked
	 * @throws IndexOutOfBoundsException saying that the index is invalid.
	 */
	protected void checkIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	/**
	 * Check the index to see if the index is valid to add
	 * 
	 * @param index being checked being added
	 * @throws IndexOutOfBoundsException saying that the index is invalid.
	 */
	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	/**
	 * return the first element
	 * 
	 * @return first element
	 */
	@Override
	public E first() {
		return get(0);
	}

	/**
	 * check to see if the data type is empty
	 * 
	 * @return true if empty, false if not empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * return the last element
	 * 
	 * @return last element
	 */
	@Override
	public E last() {
		return get(size() - 1);
	}

	/**
	 * remove the first element and return it
	 * 
	 * @return the removed first element
	 */
	@Override
	public E removeFirst() {
		return remove(0);
	}

	/**
	 * remove the last element and return it
	 * 
	 * @return the removed last element
	 */
	@Override
	public E removeLast() {
		return remove(size() - 1);
	}

}
