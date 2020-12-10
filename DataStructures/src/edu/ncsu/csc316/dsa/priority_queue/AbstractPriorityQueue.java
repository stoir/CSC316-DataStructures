package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * abstract priority queue to hold commonality, implements priority queue
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	/**
	 * comparator
	 */
	private Comparator<K> comparator;

	/**
	 * constructor to create a pq
	 * 
	 * @param c comparator for queue
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}

	/**
	 * set comparator
	 * 
	 * @param c comparator to set
	 */
	private void setComparator(Comparator<K> c) {
		if (c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * natural order class for comparator
	 * 
	 * @author sanjana cheerla
	 *
	 */
	public class NaturalOrder implements Comparator<K> {
		/**
		 * compare first and second key
		 * 
		 * @param first  key to compare
		 * @param second key to compare
		 * @return which key is bigger
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * compare key one and key two
	 * 
	 * @param data1 to compare
	 * @param data2 to compare
	 * @return the bigger data key
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}

	/**
	 * check to see if queue is empty
	 * 
	 * @return true if empty false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	// Make sure you import PriorityQueue.Entry and NOT Map.Entry!
	/**
	 * priority queue entry, implements entry
	 * 
	 * @author sanjanacheerla
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	public static class PQEntry<K, V> implements Entry<K, V> {

		/**
		 * key for entry
		 */
		private K key;
		/**
		 * value for entry
		 */
		private V value;

		/**
		 * pq entry object
		 * 
		 * @param key   to enter
		 * @param value to enter
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * set key
		 * 
		 * @param key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * set value
		 * 
		 * @param value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * get the key
		 * 
		 * @return key
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * get the value
		 * 
		 * @return value
		 */
		@Override
		public V getValue() {
			return value;
		}
	}

	/**
	 * factory method for constructing a new priority queue entry object
	 * 
	 * @param key   for new entry
	 * @param value for new entry
	 * @return created entry
	 */
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}