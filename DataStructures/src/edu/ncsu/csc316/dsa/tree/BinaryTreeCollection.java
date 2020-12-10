package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * binary tree collection for binary trees
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	/**
	 * add the root for a tree
	 * 
	 * @param value for the root node
	 * @return the position of the root node
	 */
	Position<E> addRoot(E value);

	/**
	 * add a node to the left
	 * 
	 * @param p     to add the left node to
	 * @param value of the left node
	 * @return the added left node
	 */
	Position<E> addLeft(Position<E> p, E value);

	/**
	 * add a node to the right
	 * 
	 * @param p     to add the right node to
	 * @param value of the right node
	 * @return the added right node
	 */
	Position<E> addRight(Position<E> p, E value);

	/**
	 * remove a position p
	 * 
	 * @param p the node to remove
	 * @return the value of the removed node
	 */
	E remove(Position<E> p);

	/**
	 * set the position p to the value value
	 * 
	 * @param p     to set the value of
	 * @param value to set p
	 * @return the previous value of p
	 */
	E set(Position<E> p, E value);
}
