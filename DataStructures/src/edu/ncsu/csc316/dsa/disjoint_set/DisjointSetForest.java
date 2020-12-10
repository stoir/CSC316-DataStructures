package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * disjoint set interface
 * 
 * @author sanjana cheerla
 *
 * @param <E> general param
 */
public interface DisjointSetForest<E> {

	/**
	 * Creates a disjoint set that contains the element e, then returns the position
	 * of the set
	 * 
	 * @param value to make set from
	 * @return position of the set
	 */
	Position<E> makeSet(E value);

	/**
	 * find the value in the set
	 * 
	 * @param value to find
	 * @return position of value in set
	 */
	Position<E> find(E value);

	/**
	 * Merges the disjoint sets that contain positions s and t
	 * 
	 * @param s set s to union
	 * @param t set t to union
	 */
	void union(Position<E> s, Position<E> t);

}