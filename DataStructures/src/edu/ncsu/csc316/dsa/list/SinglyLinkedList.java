package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * singly linked list that extends abstract list that keeps a head and tail
 * pointer. Contains an inner class of a linked node.
 * 
 * @author sanjana cheerla
 *
 * @param <E> the generic parameter
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/**
	 * front node of the list
	 */
	private LinkedListNode<E> front;

	/**
	 * end (Tail) node of the list
	 */
	private LinkedListNode<E> tail;

	/**
	 * size of the list
	 */
	private int size;

	/**
	 * create a new singly linked list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	/**
	 * Inner class that creates a linked list node used in the linekd list
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> the generic parameter
	 */
	private static class LinkedListNode<E> {

		/**
		 * the data of the linked list node
		 */
		private E data;

		/**
		 * next node of the created node
		 */
		private LinkedListNode<E> next;

		/**
		 * create a new linked list node with just the data
		 * 
		 * @param data of the linked node
		 */
		public LinkedListNode(E data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * creates a linked list node with the data and next node
		 * 
		 * @param data of the linked node
		 * @param next node
		 */
		public LinkedListNode(E data, LinkedListNode<E> next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * get the data element of the linked list node
		 * 
		 * @return the data element
		 */
		public E getElement() {
			return data;
		}

		/**
		 * get the next linked list node
		 * 
		 * @return next node
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}

		/**
		 * set the current data element
		 * 
		 * @param element being set
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/**
		 * set the next element of the linked list node
		 * 
		 * @param next node
		 */
		public void setNext(LinkedListNode<E> next) {
			this.next = next;
		}

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
		super.checkIndexForAdd(index);
		LinkedListNode<E> currNode = this.front.next;
		LinkedListNode<E> prevNode = null;

		for (int i = 0; i < index; i++) {
			prevNode = currNode;
			currNode = currNode.next;
		}

		LinkedListNode<E> tmpNode = new LinkedListNode<E>(value, currNode);

		if (index == 0) {
			this.front.next = tmpNode;
			if (size == 0) {
				this.tail = front.next;
			}
		} else if (index == size) {
			this.addLast(value);
			return;
		} else {
			prevNode.next = tmpNode;
		}
		this.size++;
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
		super.checkIndex(index);
		LinkedListNode<E> currNode = front.next;
		int i = 0;

		while (currNode != null) {
			if (i == index) {
				return currNode.getElement();
			}
			currNode = currNode.getNext();
			i++;
		}

		return null;
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

		super.checkIndex(index);

		if (index == 0) {
			LinkedListNode<E> temp = front.next;
			front.next = front.getNext().getNext();
			size--;
			return temp.getElement();
		}

		LinkedListNode<E> currNode = front.next;
		LinkedListNode<E> temp = null;

		for (int i = 0; i < index - 1; i++) {
			currNode = currNode.getNext();
		}
		temp = currNode;
		E data = temp.getNext().getElement();
		currNode.next = currNode.getNext().getNext();
		if (index == size - 1) {
			this.tail = currNode;
		}
		size--;

		return data;
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
		LinkedListNode<E> currNode = front.next;

		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}

		E data = currNode.getElement();
		currNode.setElement(value);

		return data;
	}

	/**
	 * get the size of the list
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * get the last, tail element of the list
	 * 
	 * @return the last, tail element
	 */
	public E last() {
		return tail.getElement();
	}

	/**
	 * Add to the end
	 * 
	 * @param data value being added to the end
	 */
	public void addLast(E data) {
		LinkedListNode<E> newNode = new LinkedListNode<E>(data);
		if (this.front == null || size == 0) {
			this.addFirst(data);
			return;
		}
		this.tail.setNext(newNode);
		this.tail = newNode;
		size++;
	}

	/**
	 * creates an ElementIterator for the data type
	 * 
	 * @return the created ElementIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(this.front.next);
	}

	/**
	 * The element iterator for ArrayBasedList
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/**
		 * the current node
		 */
		LinkedListNode<E> current;
		/**
		 * the previous node
		 */
		LinkedListNode<E> prev;
		/**
		 * the previous previous node
		 */
		LinkedListNode<E> prevPrev;
		/**
		 * the boolean to check to see if you can remove
		 */
		boolean removeOK;

		/**
		 * create an element iterator for the singly linked list
		 * 
		 * @param node that the iterator is being created for
		 */
		public ElementIterator(LinkedListNode<E> node) {
			current = node;
			prev = node;
			prevPrev = node;
			removeOK = false;
		}

		/**
		 * checks to see if there is a next element
		 * 
		 * @return true if there is a next element, false otherwise
		 */
		public boolean hasNext() {
			if (current == null) {
				return false;
			}
			return true;
		}

		/**
		 * get the next element of the iterator and advance
		 * 
		 * @return current element before advancing
		 * @throws NoSuchElementException if there is no next element
		 */
		public E next() {
			if (this.hasNext()) {
				this.prevPrev = prev;
				this.prev = current;
				// this.setPrevious(current);
				E currElem = current.getElement();
				current = current.getNext();
				this.removeOK = true;
				return currElem;
			}
			throw new NoSuchElementException();
		}

		/**
		 * removes the element before current
		 * 
		 * @throws IllegalStateException if remove cannot occur
		 */
		public void remove() {
			if (this.isRemoveOK()) {
				this.setPrevious(this.getCurrent());
				this.setRemoveOK(false);
				size--;
				return;
			}
			throw new IllegalStateException();
		}

		/**
		 * get the current node
		 * 
		 * @return current node
		 */
		public LinkedListNode<E> getCurrent() {
			return this.current;
		}

		/**
		 * set the previous node
		 * 
		 * @param newNode that is being set
		 */
		public void setPrevious(LinkedListNode<E> newNode) {
			LinkedListNode<E> temp = this.prev;
			this.prev = newNode;
			this.prevPrev = temp;
			this.getPreviousPrevious().next = prev;
		}

		/**
		 * get the previous previous node
		 * 
		 * @return the previous previous node
		 */
		public LinkedListNode<E> getPreviousPrevious() {
			return this.prevPrev;
		}

		/**
		 * set the removeOK boolean value
		 * 
		 * @param removeOK to set the value
		 */
		public void setRemoveOK(boolean removeOK) {
			this.removeOK = removeOK;
		}

		/**
		 * get what removeOK is equal to
		 * 
		 * @return true if removeOK is true, false if removeOK is false
		 */
		public boolean isRemoveOK() {
			return removeOK;
		}
	}
}
