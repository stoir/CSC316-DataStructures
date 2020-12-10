package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * General tree interface to hold methods that are implemented in GeneralTree
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {

	/**
	 * add the root of the tree
	 * 
	 * @param value of the root
	 * @return the root
	 */
	Position<E> addRoot(E value);

	/**
	 * add a child to the tree
	 * 
	 * @param p     of the parent
	 * @param value of the child
	 * @return the added child
	 */
	Position<E> addChild(Position<E> p, E value);

	/**
	 * remove node p
	 * 
	 * @param p to remove
	 * @return the removed value
	 */
	E remove(Position<E> p);

	/**
	 * set a position to a value
	 * 
	 * @param p     to set
	 * @param value to set p's value
	 * @return the previous value of p
	 */
	E set(Position<E> p, E value);
}
