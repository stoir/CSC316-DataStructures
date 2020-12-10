package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * abstract binary tree to hold functionality for binary trees
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

	/**
	 * return an iterator that orders the tree elements with left, root, right
	 * 
	 * @return an iterator that orders the tree elements with left, root, right
	 */
	@Override
	public Iterable<Position<E>> inOrder() {
		List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();

		if (!isEmpty()) {
			inOrderHelper(root(), traversal);
		}

		return traversal;
	}

	/**
	 * helper to traverse in order(left, root, right)
	 * 
	 * @param node      to traverse from
	 * @param traversal list
	 */
	private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
		if (node == null) {
			return;
		}
		inOrderHelper(left(node), traversal);
		if (node != null && node.getElement() != null)
			traversal.addLast(node);
		inOrderHelper(right(node), traversal);
	}

	/**
	 * get the sibling of p
	 * 
	 * @param p the position to get the sibling of if it exists
	 * @return the position of the sibling of p if it exists
	 */
	@Override
	public Position<E> sibling(Position<E> p) {
		var parent = this.parent(p);
		if (parent == null || this.isRoot(p) || this.numChildren(parent) != 2)
			return null;
		else if (p == left(parent)) {
			return right(parent);
		} else {
			return left(parent);
		}

	}

	/**
	 * validate the position to see if it is an abstract node
	 * 
	 * @param p the position being validated
	 * @return the position casted to abstract node
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  binary tree node", if the position is not a
	 *                                  valid binary tree node
	 */
	private AbstractNode<E> validate(Position<E> p) {
		if (!(p instanceof AbstractNode)) {
			throw new IllegalArgumentException("Position is not a valid binary tree node");
		}
		return (AbstractNode<E>) (p);
	}

	/**
	 * get the number of children of a position
	 * 
	 * @param p position to find number of children for
	 * @return the number of children
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  binary tree node", if the position is not a
	 *                                  valid binary tree node
	 */
	@Override
	public int numChildren(Position<E> p) {
		int count = 0;
		AbstractNode<E> node = validate(p);

		if (this.left(node) != null) {
			count += 1;
		}
		if (this.right(node) != null) {
			count += 1;
		}

		return count;
	}

	/**
	 * set the position p to the value E
	 * 
	 * @param p     to change the value of
	 * @param value to set p for
	 * @return the previous value
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  binary tree node", if the position is not a
	 *                                  valid binary tree node
	 */
	@Override
	public E set(Position<E> p, E value) {
		AbstractNode<E> node = validate(p);
		E original = node.getElement();
		node.setElement(value);
		return original;
	}

	/**
	 * create an iterator with in order traversal
	 * 
	 * @return the created in order iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(inOrder().iterator());
	}

	/**
	 * get the children of the position
	 * 
	 * @param p the node to find the child for
	 * @return an iterator for children of p if they exists
	 * @throws IllegalArgumentException with the message "Position is not a valid
	 *                                  binary tree node", if the position is not a
	 *                                  valid binary tree node
	 */
	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		AbstractNode<E> node = validate(p);
		List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
		if (left(node) != null) {
			ret.addLast(left(node));
		}
		if (right(node) != null) {
			ret.addLast(right(node));
		}
		return ret;
	}
}