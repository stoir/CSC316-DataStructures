package edu.ncsu.csc316.dsa.priority_queue;

/**
 * priority queue interface
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public interface PriorityQueue<K, V> {

	/**
	 * Entry interface for priority queues
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	interface Entry<K, V> {
		/**
		 * get the key
		 * 
		 * @return key
		 */
		K getKey();

		/**
		 * get the value
		 * 
		 * @return value
		 */
		V getValue();
	}

	/**
	 * insert a key value to the queue
	 * 
	 * @param key   to insert
	 * @param value to insert
	 * @return old entry value or null
	 */
	Entry<K, V> insert(K key, V value);

	/**
	 * get the min value
	 * 
	 * @return min value
	 */
	Entry<K, V> min();

	/**
	 * delete the min value
	 * 
	 * @return deleted value
	 */
	Entry<K, V> deleteMin();

	/**
	 * get the size of the priority queue
	 * 
	 * @return size
	 */
	int size();

	/**
	 * check to see if queue is empty
	 * 
	 * @return true if empty false otherwise
	 */
	boolean isEmpty();
}