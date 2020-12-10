package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * linked binary tree, a binary tree with a linked list in the background
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/**
	 * root node of tree
	 */
	private Node<E> root;
	/**
	 * size of tree
	 */
	private int size;

	/**
	 * create a linked binary tree
	 */
	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	/**
	 * validate the position to see if it is an abstract node
	 * 
	 * @param p the position being validated
	 * @return the position casted to abstract node
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  linked binary tree node", if the position is
	 *                                  not a valid binary tree node
	 */
	protected Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Position is not a valid linked binary node");
		}
		return (Node<E>) p;
	}

	/**
	 * tree nodes for linked binary trees
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> genral param
	 */
	public static class Node<E> extends AbstractNode<E> {
		/**
		 * parent node
		 */
		private Node<E> parent;
		/**
		 * left node
		 */
		private Node<E> left;
		/**
		 * right node
		 */
		private Node<E> right;

		/**
		 * create a node with an element
		 * 
		 * @param element value for the node
		 */
		public Node(E element) {
			this(element, null);
		}

		/**
		 * create a node with a parent
		 * 
		 * @param element value for the node
		 * @param parent  of the node
		 */
		public Node(E element, Node<E> parent) {
			super(element);
			setParent(parent);
		}

		/**
		 * get the left node of the node
		 * 
		 * @return the left node
		 */
		public Node<E> getLeft() {
			return left;
		}

		/**
		 * get the right node of the node
		 * 
		 * @return the right node
		 */
		public Node<E> getRight() {
			return right;
		}

		/**
		 * set the left node of the tree
		 * 
		 * @param left node to set
		 */
		public void setLeft(Node<E> left) {
			this.left = left;
		}

		/**
		 * set the right node of the tree
		 * 
		 * @param right node to set
		 */
		public void setRight(Node<E> right) {
			this.right = right;
		}

		/**
		 * get the parent of the node
		 * 
		 * @return parent of the node
		 */
		public Node<E> getParent() {
			return parent;
		}

		/**
		 * set the parent of the node
		 * 
		 * @param parent to set
		 */
		public void setParent(Node<E> parent) {
			this.parent = parent;
		}
	}

	/**
	 * get the left node of the node
	 * 
	 * @param p node to find left node for
	 * @return left node of p
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  linked binary tree node", if the position is
	 *                                  not a valid binary tree node
	 */
	@Override
	public Position<E> left(Position<E> p) {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	/**
	 * get the right node of the node
	 * 
	 * @param p node to find right node for
	 * @return right node of p
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  linked binary tree node", if the position is
	 *                                  not a valid binary tree node
	 */
	@Override
	public Position<E> right(Position<E> p) {
		Node<E> node = validate(p);
		return node.getRight();
	}

	/**
	 * add a node to the left
	 * 
	 * @param p     to add the left node to
	 * @param value of the left node
	 * @return the added left node
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  linked binary tree node", if the position is
	 *                                  not a valid binary tree node
	 */
	@Override
	public Position<E> addLeft(Position<E> p, E value) {
		Node<E> node = validate(p);
		if (left(node) != null) {
			throw new IllegalArgumentException("Node already has a left child.");
		}
		Node<E> child = this.createNode(value, node, null, null);
		node.setLeft(child);
		size++;
		return child;
	}

	/**
	 * add a node to the right
	 * 
	 * @param p     to add the right node to
	 * @param value of the right node
	 * @return the added right node
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  linked binary tree node", if the position is
	 *                                  not a valid binary tree node
	 */
	@Override
	public Position<E> addRight(Position<E> p, E value) {
		Node<E> node = validate(p);
		if (right(node) != null) {
			throw new IllegalArgumentException("Node already has a right child.");
		}
		Node<E> child = this.createNode(value, node, null, null);
		node.setRight(child);
		size++;
		return child;
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
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  linked binary tree node", if the position is
	 *                                  not a valid binary tree node
	 */
	@Override
	public Position<E> parent(Position<E> p) {
		Node<E> node = validate(p);
		return node.getParent();
	}

	/**
	 * add the root for a tree
	 * 
	 * @param value for the root node
	 * @return the position of the root node
	 * @throws IllegalArgumentException with the message "The tree already has a
	 *                                  root." if the tree already has a root
	 */
	@Override
	public Position<E> addRoot(E value) {
		if (root() != null) {
			throw new IllegalArgumentException("The tree already has a root.");
		}
		this.root = createNode(value, null, null, null);
		size++;
		return root;
	}

	/**
	 * remove a position p
	 * 
	 * @param p the node to remove
	 * @return the value of the removed node
	 * @throws IllegalArgumentException with the message "The node has two children"
	 *                                  if the node already has two children
	 */
	@Override
	public E remove(Position<E> p) {
		if (numChildren(p) == 2) {
			throw new IllegalArgumentException("The node has two children");
		}
		Node<E> node = validate(p);
		Node<E> child;

		if (node.getRight() != null) {
			child = node.getRight();
		} else {
			child = node.getLeft();
		}

		if (child != null)
			child.setParent(node.getParent());

		if (node == root)
			root = child;

		else {
			var parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		node.setParent(node);
		node.setRight(null);
		node.setLeft(null);

		size--;

		return node.getElement();
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
	 * create a node
	 * 
	 * @param e      value for node
	 * @param parent of node
	 * @param left   of node
	 * @param right  of node
	 * @return created node
	 */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		Node<E> newNode = new Node<E>(e);
		newNode.setParent(parent);
		newNode.setLeft(left);
		newNode.setRight(right);
		return newNode;
	}

	// setRoot is needed for a later lab...
	// ...but THIS DESIGN IS BAD! If a client arbitrarily changes
	// the root by using the method, the size may no longer be correct/valid.
	// Instead, the precondition for this method is that
	// it should *ONLY* be used when rotating nodes in
	// balanced binary search trees. We could instead change
	// our rotation code to not need this setRoot method, but that
	// makes the rotation code messier. For the purpose of this lab,
	// we will sacrifice a stronger design for cleaner/less code.

	/**
	 * set root, only to be used when balancing
	 * 
	 * @param p to set the root to
	 * @return the new root
	 */
	protected Position<E> setRoot(Position<E> p) {
		root = validate(p);
		return root;
	}

	/**
	 * return an iterator that orders the tree elements with root, left, right
	 * 
	 * @return an iterator that orders the tree elements with root, left, right
	 */
	@Override
	public Iterable<Position<E>> preOrder() {
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

}