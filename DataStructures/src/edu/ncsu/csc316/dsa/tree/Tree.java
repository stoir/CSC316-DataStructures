package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tree interface holding all methods for a tree data structures
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public interface Tree<E> {

	/**
	 * get the root of the tree
	 * 
	 * @return root of tree
	 */
	Position<E> root();

	/**
	 * get the parent of the position
	 * 
	 * @param p position to find parent for
	 * @return the parent of position if it exists
	 */
	Position<E> parent(Position<E> p);

	/**
	 * get the children of the position
	 * 
	 * @param p the node to find the child for
	 * @return an iterator for children of p if they exists
	 */
	Iterable<Position<E>> children(Position<E> p);

	/**
	 * get the number of children of a position
	 * 
	 * @param p position to find number of children for
	 * @return the number of children
	 */
	int numChildren(Position<E> p);

	/**
	 * check to see if the node is an internal node in the tree
	 * 
	 * @param p node to check if it is internal
	 * @return true if node is internal, false otherwise
	 */
	boolean isInternal(Position<E> p);

	/**
	 * check to see if the node is a leaf
	 * 
	 * @param p node to check if it is a leaf
	 * @return true if p is a leaf, false otherwise
	 */
	boolean isLeaf(Position<E> p);

	/**
	 * check to see if the node is a root
	 * 
	 * @param p node to check if it is a root
	 * @return true if p is a root, false otherwise
	 */
	boolean isRoot(Position<E> p);

	/**
	 * get size of tree
	 * 
	 * @return size of tree
	 */
	int size();

	/**
	 * check to see if the tree is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * return an iterator that orders the tree elements with root, left, right
	 * 
	 * @return an iterator that orders the tree elements with root, left, right
	 */
	Iterable<Position<E>> preOrder();

	/**
	 * return an iterator that orders the tree elements with left, right, root
	 * 
	 * @return an iterator that orders the tree elements with left, right, root
	 */
	Iterable<Position<E>> postOrder();

	/**
	 * return an iterator that orders the tree elements ordered by levels
	 * 
	 * @return an iterator that orders the tree elements ordered by levels
	 */
	Iterable<Position<E>> levelOrder();
}
