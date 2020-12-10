package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;
//import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

/**
 * TTree set, extends comparable and abstract set
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic param
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

	/**
	 * tree mapp inner structure
	 */
	private Map<E, E> tree;

	/**
	 * tree set constructor
	 */
	public TreeSet() {
		tree = new BinarySearchTreeMap<E, E>();
	}

	/**
	 * iterator for tree set
	 * 
	 * @return iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return tree.iterator();
	}

	/**
	 * add value
	 * 
	 * @param value to add
	 */
	@Override
	public void add(E value) {
		tree.put(value, value);
	}

	/**
	 * check to see if value is contained in set
	 * 
	 * @param value to check
	 * @return true if value is in set, false otherwise
	 */
	@Override
	public boolean contains(E value) {
		E temp = tree.get(value);
		if (temp == null) {
			return false;
		}
		return true;
	}

	/**
	 * remove value
	 * 
	 * @param value from set
	 * @return removed value
	 */
	@Override
	public E remove(E value) {
		return tree.remove(value);
	}

	/**
	 * size of set
	 * 
	 * @return size
	 */
	@Override
	public int size() {
		return tree.size();
	}
}