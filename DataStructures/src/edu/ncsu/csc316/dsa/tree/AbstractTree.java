package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;

/**
 * abstract tree that implements common behavior for trees
 * 
 * @author sanjana cheerla
 *
 * @param <E> general
 */
public abstract class AbstractTree<E> implements Tree<E> {

	/**
	 * check to see if the node is an internal node in the tree
	 * 
	 * @param p node to check if it is internal
	 * @return true if node is internal, false otherwise
	 */
	@Override
	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}

	/**
	 * check to see if the node is a leaf
	 * 
	 * @param p node to check if it is a leaf
	 * @return true if p is a leaf, false otherwise
	 */
	@Override
	public boolean isLeaf(Position<E> p) {
		return numChildren(p) == 0;
	}

	/**
	 * check to see if the node is a root
	 * 
	 * @param p node to check if it is a root
	 * @return true if p is a root, false otherwise
	 */
	@Override
	public boolean isRoot(Position<E> p) {
		return p == root();
	}

	/**
	 * check to see if the tree is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Node for the tree nodes.
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> general param
	 */
	protected abstract static class AbstractNode<E> implements Position<E> {

		/**
		 * element for the tree node
		 */
		private E element;

		/**
		 * constructor to create a tree node
		 * 
		 * @param element value for node
		 */
		public AbstractNode(E element) {
			setElement(element);
		}

		/**
		 * get the element of the node
		 * 
		 * @return the element of the node
		 */
		@Override
		public E getElement() {
			return element;
		}

		/**
		 * set the element of the node
		 * 
		 * @param element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * Element iterator for the elements in the tree
	 * 
	 * @author sanjana cheerla
	 *
	 */
	protected class ElementIterator implements Iterator<E> {
		/**
		 * iterator for the positions
		 */
		private Iterator<Position<E>> it;

		/**
		 * constructor to create an element iterator
		 * 
		 * @param iterator for the element iterator
		 */
		public ElementIterator(Iterator<Position<E>> iterator) {
			it = iterator;
		}

		/**
		 * check to see if the next element in the iterator exists
		 * 
		 * @return true if the the next element in the iterator exists, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * get the next element in the iterator
		 * 
		 * @return the next element in the iterator
		 */
		@Override
		public E next() {
			return it.next().getElement();
		}

		/**
		 * remove an element, currently un-supported
		 * 
		 * @throws UnsupportedOperationException with the message "The remove operation
		 *                                       is not supported yet."
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * toString for the class
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		toStringHelper(sb, "", root());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * string helper for toString
	 * 
	 * @param sb     string builder
	 * @param indent to indent children
	 * @param root   position
	 */
	private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
		if (root == null) {
			return;
		}
		sb.append(indent).append(root.getElement()).append("\n");
		for (Position<E> child : children(root)) {
			toStringHelper(sb, indent + " ", child);
		}
	}
}