package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * General tree implementation
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public class GeneralTree<E> extends AbstractTree<E> implements GeneralTreeCollection<E> {

	/**
	 * root of tree
	 */
	private Node<E> root;

	/**
	 * size of tree
	 */
	private int size;

	/**
	 * create a new general tree
	 */
	public GeneralTree() {
		root = null;
		size = 0;
	}

	/**
	 * get the root of the tree
	 * 
	 * @return root of tree
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/**
	 * get the parent of the position
	 * 
	 * @param p position to find parent for
	 * @return the parent of position if it exists
	 * @throws IllegalArgumentException with the message "Position is not a legal
	 *                                  general tree node", if the position is not a
	 *                                  legal general tree node
	 */
	@Override
	public Position<E> parent(Position<E> p) {
		return validate(p).getParent();
	}

	/**
	 * get the children of the position
	 * 
	 * @param p the node to find the child for
	 * @return an iterator for children of p if they exists
	 * @throws IllegalArgumentException with the message "Position is not a legal
	 *                                  general tree node", if the position is not a
	 *                                  legal general tree node
	 */
	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		Node<E> node = validate(p);
		List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
		for (Position<Node<E>> n : node.getChildren().positions()) {
			ret.addLast(n.getElement());
		}
		return ret;
	}

	/**
	 * get the number of children of a position
	 * 
	 * @param p position to find number of children for
	 * @return the number of children
	 * @throws IllegalArgumentException with the message "Position is not a legal
	 *                                  general tree node", if the position is not a
	 *                                  legal general tree node
	 */
	@Override
	public int numChildren(Position<E> p) {
		Node<E> node = validate(p);
		return node.getChildren().size();
	}

	/**
	 * add the root of the tree
	 * 
	 * @param value of the root
	 * @return the root
	 * @throws IllegalArgumentException with the message "Tree already has a root"
	 *                                  if the tree already has a root node
	 */
	@Override
	public Position<E> addRoot(E value) {
		if (root != null) {
			throw new IllegalArgumentException("Tree already has a root");
		}
		this.root = createNode(value);
		size = 1;
		return root;
	}

	/**
	 * add a child to the tree
	 * 
	 * @param p     of the parent
	 * @param value of the child
	 * @return the added child
	 * @throws IllegalArgumentException with the message "Position is not a legal
	 *                                  general tree node", if the position is not a
	 *                                  legal general tree node
	 */
	@Override
	public Position<E> addChild(Position<E> p, E value) {
		Node<E> node = validate(p);
		Node<E> newNode = createNode(value);
		node.getChildren().addLast(newNode);
		newNode.setParent(node);
		size++;
		return newNode;
	}

	/**
	 * set a position to a value
	 * 
	 * @param p     to set
	 * @param value to set p's value
	 * @return the previous value of p
	 * @throws IllegalArgumentException with the message "Position is not a legal
	 *                                  general tree node", if the position is not a
	 *                                  legal general tree node
	 */
	@Override
	public E set(Position<E> p, E value) {
		Node<E> node = validate(p);
		E original = node.getElement();
		node.setElement(value);
		return original;
	}

	/**
	 * create a node for the tree
	 * 
	 * @param element value to create node for
	 * @return the created node
	 */
	public Node<E> createNode(E element) {
		return new Node<E>(element);
	}

	/**
	 * get size of tree
	 * 
	 * @return size of tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * return an iterator that orders the tree elements with root, left, right
	 * 
	 * @return an iterator that orders the tree elements with root, left, right
	 */
	@Override
	public Iterable<Position<E>> preOrder() {
		// You can use any list data structure here that supports
		// O(1) addLast
		List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			preOrderHelper(root(), traversal);
		}
		return traversal;
	}

	/**
	 * pre order traversal helper
	 * 
	 * @param p         position for traversal
	 * @param traversal of the tree
	 */
	private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
		traversal.addLast(p);
		for (Position<E> c : children(p)) {
			if (c != null) {
				preOrderHelper(c, traversal);
			}
		}
	}

	/**
	 * return an iterator that orders the tree elements with left, right, root
	 * 
	 * @return an iterator that orders the tree elements with left, right, root
	 */
	@Override
	public Iterable<Position<E>> postOrder() {
		// You can use any list data structure here that supports
		// O(1) addLast
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			postOrderHelper(root(), list);
		}
		return list;
	}

	/**
	 * post order traversal helper
	 * 
	 * @param p         position for traversal
	 * @param traversal of the tree
	 */
	private void postOrderHelper(Position<E> p, List<Position<E>> list) {
		for (Position<E> c : children(p)) {
			if (c != null) {
				postOrderHelper(c, list);
			}
		}
		list.addLast(p);
	}

	/**
	 * return an iterator that orders the tree elements ordered by levels
	 * 
	 * @return an iterator that orders the tree elements ordered by levels
	 */
	@Override
	public Iterable<Position<E>> levelOrder() {
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		var q = new ArrayBasedQueue<Position<E>>();

		if (!this.isEmpty())
			q.enqueue(root());

		while (!q.isEmpty()) {
			var p = q.dequeue();

			list.addLast(p);
			for (Position<E> c : children(p))
				if (c != null) {
					q.enqueue(c);
				}
		}
		return list;
	}

	/**
	 * create an element iterator in preOrder
	 * 
	 * @return created element iterator in preOrder
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(preOrder().iterator());
	}

	/**
	 * remove node p
	 * 
	 * @param p to remove
	 * @return the removed value
	 */
	@Override
	public E remove(Position<E> p) {
		// We will only support removal of a node that only has 1 child
		if (numChildren(p) > 1) {
			throw new IllegalArgumentException("The node has more than 1 child.");
		}
		// Handle special case if the node being removed is the root
		if (isRoot(p)) {
			E original = p.getElement();
			if (numChildren(p) == 0) {
				this.root = null;
			} else {
				Node<E> replacement = validate(p).getChildren().first().getElement();
				replacement.setParent(null);
				this.root = replacement;
			}
			size--;
			return original;
		}
		// Handle the case where the node being removed is NOT the root
		Node<E> node = validate(p);
		Node<E> parent = validate(parent(p));
		// Create an iterator over the parent node's children positions
		Iterator<Position<Node<E>>> it = parent.getChildren().positions().iterator();
		while (it.hasNext()) {
			// Find the position of the node to be removed
			Position<Node<E>> current = it.next();
			if (current.getElement() == node) {
				if (numChildren(p) == 1) {
					// If the node being removed has 1 child, replace it
					// in the parent's list of children
					Node<E> replacement = node.getChildren().first().getElement();
					replacement.setParent(parent);
					parent.getChildren().set(current, replacement);
				} else {
					// If the node being removed has 0 children, remove it
					parent.getChildren().remove(current);
				}
			}
		}
		size--;
		return node.getElement();
	}

	/**
	 * validate the node at position p
	 * 
	 * @param p to validate
	 * @return validated node
	 * @throws IllegalArgumentException with the message "Position is not a legal
	 *                                  general tree node", if the position is not a
	 *                                  legal general tree node
	 */
	private Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Position is not a legal general tree node");
		}
		return (Node<E>) p;
	}

	/**
	 * Node for general tree
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> general param
	 */
	private static class Node<E> extends AbstractNode<E> {

		/**
		 * parent node
		 */
		private Node<E> parent;

		/**
		 * A general tree node needs to maintain a list of children
		 */
		private PositionalList<Node<E>> children;

		/**
		 * create a new node
		 * 
		 * @param element value to create node
		 */
		public Node(E element) {
			this(element, null);
		}

		/**
		 * create a node with a parent
		 * 
		 * @param element value for new node
		 * @param parent  of new node
		 */
		public Node(E element, Node<E> parent) {
			super(element);
			setParent(parent);
			children = new PositionalLinkedList<Node<E>>();
		}

		/**
		 * set the parent
		 * 
		 * @param p to set parent
		 */
		public void setParent(Node<E> p) {
			parent = p;
		}

		/**
		 * get the parent of a node
		 * 
		 * @return the parent of that node
		 */
		public Node<E> getParent() {
			return parent;
		}

		/**
		 * get the children of a node
		 * 
		 * @return a list of the children for that node
		 */
		public PositionalList<Node<E>> getChildren() {
			return children;
		}
	}

}