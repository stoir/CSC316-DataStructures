package edu.ncsu.csc316.dsa.priority_queue;

/**
 * adaptable priority queue
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * remove an entry from the queue
	 * 
	 * @param entry to remove
	 */
	void remove(Entry<K, V> entry);

	/**
	 * replace the key of an entry
	 * 
	 * @param entry to replace key of
	 * @param key   to replace entry of
	 */
	void replaceKey(Entry<K, V> entry, K key);

	/**
	 * replace the value of a key
	 * 
	 * @param entry to replace value of
	 * @param value to replace value of
	 */
	void replaceValue(Entry<K, V> entry, V value);
}
