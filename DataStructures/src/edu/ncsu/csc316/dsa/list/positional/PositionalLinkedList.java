package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * the positionallinkedlist, a doubly linked list with front and tail nodes.
 * implements the positional list interface
 * 
 * CODE is from data structures and algorithms in java 6th edition. Book by
 * Michael T. Goodrich and Roberto Tamassia
 * 
 * @author sanjana cheerla
 *
 * @param <E> the generic parameter
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/**
	 * the front of the list
	 */
	private PositionalNode<E> front;
	/**
	 * the end of the list
	 */
	private PositionalNode<E> tail;
	/**
	 * the size of the list
	 */
	private int size;

	/**
	 * create a new positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	/**
	 * create a new ElementIterator
	 * 
	 * @return the created iterator
	 */
	@Override
	public Iterator<E> iterator() {
		// we start at front.getNext() because front is a dummy/sentinel node
		return new ElementIterator(front.getNext());
	}

	/**
	 * add the specified value after the specified position
	 * 
	 * @param p     the position
	 * @param value the value being added after p
	 * @return the added value at the position
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E value) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		return addBetween(value, node, node.getNext());
	}

	/**
	 * add the specified value before the specified position
	 * 
	 * @param p     the position
	 * @param value the value being added before p
	 * @return the added value at the position
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E value) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		return addBetween(value, node.getPrevious(), node);
	}

	/**
	 * add the specified value at the beginning
	 * 
	 * @param value the value being added at the beginning
	 * @return the added value at the position
	 */
	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front, front.getNext());
	}

	/**
	 * add the specified value at the end
	 * 
	 * @param value the value being added at the end
	 * @return the added value at the position
	 */
	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail.getPrevious(), tail);
	}

	/**
	 * get the element after the position
	 * 
	 * @param p the position
	 * @return the node after p
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		return position(node.getNext());
	}

	/**
	 * get the element before the position
	 * 
	 * @param p the position
	 * @return the node before p
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		return position(node.getPrevious());
	}

	/**
	 * get the element at the beginning
	 * 
	 * @return the node at the beginning
	 */
	@Override
	public Position<E> first() {
		return position(front.getNext());
	}

	/**
	 * check if the position is valid or not
	 * 
	 * @param node the node being checked for the position
	 * @return null if the position is not valid, else return the parameter
	 */
	private Position<E> position(PositionalNode<E> node) {
		if (node == front || node == tail)
			return null; // do not expose user to the sentinels
		return node;
	}

	/**
	 * check if the list is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * get the element at the end
	 * 
	 * @return the node at the end
	 */
	@Override
	public Position<E> last() {
		return position(tail.getPrevious());
	}

	/**
	 * create a iterable for position
	 * 
	 * @return the iterable object for positions
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * remove element at position p
	 * 
	 * @param p the position
	 * @return the removed element
	 */
	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> predecessor = node.getPrevious();
		PositionalNode<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrevious(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null); // help with garbage collection
		node.setNext(null); // and convention for defunct node
		node.setPrevious(null);
		return answer;
	}

	/**
	 * remove element at position p
	 * 
	 * @param p the position
	 * @return the removed element
	 */
	@Override
	public E set(Position<E> p, E value) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(value);
		return answer;
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
	 * validate the position
	 * 
	 * @param p the position
	 * @return the position as a node
	 * @throws IllegalArgumentException if position isnt valid
	 */
	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	/**
	 * add between prev and next
	 * 
	 * @param value being added
	 * @param prev  value
	 * @param next  value
	 * @return most recently added value
	 */
	private Position<E> addBetween(E value, PositionalNode<E> prev, PositionalNode<E> next) {
		PositionalNode<E> newest = new PositionalNode<>(value, next, prev); // create and link a new node
		prev.setNext(newest);
		next.setPrevious(newest);
		size++;
		return newest;
	}

	/**
	 * position iterator that moves through postion
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/**
		 * current pos
		 */
		private Position<E> current;
		/**
		 * checks remove
		 */
		private boolean removeOK;

		/**
		 * creates a pos iterator
		 * 
		 * @param start pos
		 */
		public PositionIterator(PositionalNode<E> start) {
			current = start;
			removeOK = false;
		}

		/**
		 * get the next element of the iterator and advance
		 * 
		 * @return true if there is next, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return current != tail;
			//return !(this.current == null || this.current == PositionalLinkedList.this.tail);
		}

		/**
		 * get the next element of the iterator and advance
		 * 
		 * @return current element before advancing
		 * @throws NoSuchElementException if there is no next element
		 */
		@Override
		public Position<E> next() {
			if (this.hasNext()) {
				Position<E> tmp = this.current;
				// current = PositionalLinkedList.this.after(this.current);
				current = validate(current).getNext();
				removeOK = true;
				return tmp;
			}
			throw new NoSuchElementException();
		}

		/**
		 * removes the element before current
		 * 
		 * @throws IllegalStateException if remove cannot occur
		 */
		@Override
		public void remove() {
			if (removeOK) {
				PositionalLinkedList.this.remove(validate(current).getPrevious());
				removeOK = false;
				return;
			}
			throw new IllegalStateException();
		}
	}

	/**
	 * Element iterator for positionallinkedlist
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class ElementIterator implements Iterator<E> {

		/**
		 * create a position iterator
		 */
		private Iterator<Position<E>> it;

		/**
		 * create an element iterator starting at start
		 * 
		 * @param start the iterator from
		 */
		public ElementIterator(PositionalNode<E> start) {
			it = new PositionIterator(start);
		}

		/**
		 * get the next element of the iterator and advance
		 * 
		 * @return true if there is next, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * get the next element
		 * 
		 * @return next element
		 */
		@Override
		public E next() {
			return it.next().getElement();
		}

		/**
		 * removes the element current
		 */
		@Override
		public void remove() {
			it.remove();
		}
	}

	/**
	 * this class creates a new position iterable elemnt
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		/**
		 * create a new position iterator
		 * 
		 * @return new position iterator
		 */
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator(front.getNext());
		}
	}

	/**
	 * creates a positional node
	 * 
	 * @author sanjanacheerla
	 *
	 * @param <E> generic parameter
	 */
	private static class PositionalNode<E> implements Position<E> {

		/**
		 * data element
		 */
		private E element;
		/**
		 * next node
		 */
		private PositionalNode<E> next;
		/**
		 * previous node
		 */
		private PositionalNode<E> previous;

		/**
		 * creates a positional node
		 * 
		 * @param value of the node
		 */
		public PositionalNode(E value) {
			this(value, null);
		}

		/**
		 * creates a positional node
		 * 
		 * @param value of the node
		 * @param next  node
		 */
		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		/**
		 * create a positional node
		 * 
		 * @param value of the node
		 * @param next  node
		 * @param prev  node
		 */
		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		/**
		 * set the previous node
		 * 
		 * @param prev node to be set
		 */
		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		/**
		 * get the prev node
		 * 
		 * @return prev node
		 */
		public PositionalNode<E> getPrevious() {
			return previous;
		}

		/**
		 * set the next node
		 * 
		 * @param next node to be set
		 */
		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		/**
		 * get the next node
		 * 
		 * @return next ndoe
		 */
		public PositionalNode<E> getNext() {
			return next;
		}

		/**
		 * set the element of the node
		 * 
		 * @return the element
		 */
		@Override
		public E getElement() {
			return element;
		}

		/**
		 * set the element
		 * 
		 * @param element to be set
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

}