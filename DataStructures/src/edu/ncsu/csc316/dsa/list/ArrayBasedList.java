package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array Based List implementation, extends AbstractList. New functionality
 * includes ensureCapacity, and the nested inner class ElementIterator.
 * 
 * @author Sanjana Cheerla
 *
 * @param <E> generic parameter E
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/**
	 * The default capacity
	 */
	private final static int DEFAULT_CAPACITY = 10;

	/**
	 * Array of E generic elements for data
	 */
	private E[] data;

	/**
	 * size of data array
	 */
	private int size;

	/**
	 * default constructor, creates an empty ArrayBasedList object with the default
	 * capacity
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor that creates an empty ArrayBasedList object with the provided
	 * capacity
	 * 
	 * @param capacity the capacity of the list
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	/**
	 * adds a specific element at the specified index.
	 * 
	 * @param index that the value is being added to
	 * @param value that is being added at the specific index
	 * @throws IllegalArgumentException  if the value is null
	 * @throws IndexOutOfBoundsException saying that the index is invalid.
	 */
	@Override
	public void add(int index, E value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.ensureCapacity(this.size + 1);
		checkIndexForAdd(index);

		for (int i = this.size - 1; i >= index; i--) {
			E temp = this.data[i + 1];
			this.data[i + 1] = this.data[i];
			this.data[i] = temp;
		}

		this.data[index] = value;

		this.size += 1;
	}

	/**
	 * get the specific element at the specified index
	 * 
	 * @param index of the element being get
	 * @return the element at the specific index
	 * @throws IndexOutOfBoundsException saying that the index is invalid.
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		return this.data[index];
	}

	/**
	 * Removes an element at the specified index
	 * 
	 * @param index of the element being removed
	 * @return the removed element
	 * @throws IndexOutOfBoundsException saying that the index is invalid.
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);

		for (int i = index; i < size; i++) {
			E temp = this.data[i + 1];
			this.data[i + 1] = this.data[i];
			this.data[i] = temp;
		}
		E temp = this.data[size];
		this.size -= 1;
		return temp;
	}

	/**
	 * sets a specified element at the specified index
	 * 
	 * @param index of the element being set
	 * @param value of the element being set at the index
	 * @return element before being set at index
	 * @throws IndexOutOfBoundsException saying that the index is invalid.
	 */
	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E temp = this.data[index];
		this.data[index] = value;
		return temp;
	}

	/**
	 * the size of the ArrayBasedList
	 * 
	 * @return the size of the ArrayBasedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * creates an ElementIterator for the data type
	 * 
	 * @return the created ElementIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * checks the capacity of the array, if needed increases the capacity
	 * 
	 * @param minCapacity the capacity being checked
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 2) + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	/**
	 * The element iterator for ArrayBasedList
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class ElementIterator implements Iterator<E> {

		/**
		 * position of the iterator
		 */
		private int position;

		/**
		 * checks to see if remove can occur
		 */
		private boolean removeOK;

		/**
		 * creates an element iterator
		 */
		public ElementIterator() {
			this.position = 0;
		}

		/**
		 * checks to see if there is a next element
		 * 
		 * @return true if there is a next element, false otherwise
		 */
		public boolean hasNext() {
			return this.position < ArrayBasedList.this.size();
		}

		/**
		 * get the next element of the iterator and advance
		 * 
		 * @return current element before advancing
		 * @throws NoSuchElementException if there is no next element
		 */
		public E next() {
			if (this.hasNext()) {
				this.removeOK = true;
				E temp = ArrayBasedList.this.get(this.position);
				this.position += 1;
				return temp;
			} else {
				throw new NoSuchElementException();
			}
		}

		/**
		 * removes the element before current
		 * 
		 * @throws IllegalStateException if remove cannot occur
		 */
		public void remove() {
			if (this.removeOK) {
				position--;
				ArrayBasedList.this.remove(this.position);
				removeOK = false;
				return;
			}
			throw new IllegalStateException();
		}
	}

}
