package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Hash set extends abstract set
 * 
 * Since our hash map uses linear probing, the entries are not ordered. As a
 * result, we do not restrict our hash set to use Comparable elements. This also
 * gives you an option if you need a set to manage elements that
 * are*NOT*Comparable(versus a TreeSet)
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic param
 */
public class HashSet<E> extends AbstractSet<E> {

	/**
	 * map inner structure
	 */
	private Map<E, E> map;

	// This constructor will use our "production version" of our hash map
	// meaning random values for alpha and beta will be used

	/**
	 * constructor to produce random alpha and beta values
	 */
	public HashSet() {
		this(false);
	}

	/**
	 * constructor to use when testing to produce static alpha, beta and prime
	 * values alpha = 1 beta = 1 prime = 7
	 * 
	 * @param isTesting for if hashset is being used for testing or not
	 */
	public HashSet(boolean isTesting) {
		map = new LinearProbingHashMap<E, E>(isTesting);
	}

	/**
	 * iterator for tree set
	 * 
	 * @return iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return map.iterator();
	}

	/**
	 * add value
	 * 
	 * @param value to add
	 */
	@Override
	public void add(E value) {
		map.put(value, value);
	}

	/**
	 * check to see if value is contained in set
	 * 
	 * @param value to check
	 * @return true if value is in set, false otherwise
	 */
	@Override
	public boolean contains(E value) {
		E temp = map.get(value);
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
		return map.remove(value);
	}

	/**
	 * size of set
	 * 
	 * @return size
	 */
	public int size() {
		return map.size();
	}
}