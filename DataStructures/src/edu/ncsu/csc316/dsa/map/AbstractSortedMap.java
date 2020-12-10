package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * abstract sorted map class extends comparable and abstract map. holds
 * functionality to compare values to maintain order.
 * 
 * @author sanjana cheerla
 *
 * @param <K> key for map
 * @param <V> value for key
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	/**
	 * comparator object
	 */
	private Comparator<K> compare;

	/**
	 * constructor to create an abstract sorted map
	 * 
	 * @param compare comparator that was created
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * compare key 1 with key 2
	 * 
	 * @param key1 being compared
	 * @param key2 being compared
	 * @return 0 if key 1 equals key 2, less than 0 if key 1 is less than key 2,
	 *         greater than 0 if key 1 is greater than key 2
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * natural order inner class to maintain order
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class NaturalOrder implements Comparator<K> {

		/**
		 * compare first with key second
		 * 
		 * @param first  being compared
		 * @param second being compared
		 * @return 0 if first equals second, less than 0 if first is less than second,
		 *         greater than 0 if first is greater than second
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}
