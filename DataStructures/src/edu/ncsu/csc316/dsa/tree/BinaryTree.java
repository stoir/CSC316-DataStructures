package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * interface to hold commonality for binary trees
 * 
 * @author sanjana cheerla
 * 
 * @param <E> general param
 */
public interface BinaryTree<E> extends Tree<E> {

	/**
	 * get the left position if it exists
	 * 
	 * @param p to get the left position for
	 * @return the left node of p
	 */
	Position<E> left(Position<E> p);

	/**
	 * get the right position if it exists
	 * 
	 * @param p to get the right position for
	 * @return the right node of p
	 */
	Position<E> right(Position<E> p);

	/**
	 * get the sibling of p
	 * 
	 * @param p the position to get the sibling of if it exists
	 * @return the position of the sibling of p if it exists
	 */
	Position<E> sibling(Position<E> p);

	/**
	 * return an iterator that orders the tree elements with left, root, right
	 * 
	 * @return an iterator that orders the tree elements with left, root, right
	 */
	Iterable<Position<E>> inOrder();
}
