package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * a hash map that uses separate chaining, extends abstract hash map
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	/**
	 * map for separate chaining map
	 */
	private Map<K, V>[] table;

	/**
	 * size of map
	 */
	private int size;

	/**
	 * constructor to create a default map
	 */
	public SeparateChainingHashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY, false);
	}

	/**
	 * constructor to create a default map for testing
	 * 
	 * @param isTesting used for testing
	 */
	public SeparateChainingHashMap(boolean isTesting) {
		this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
	}

	/**
	 * create a map based on capacity
	 * 
	 * @param capacity for map
	 */
	public SeparateChainingHashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * create a map with capacity and boolean for testing or not
	 * 
	 * @param capacity  for map
	 * @param isTesting used for testing
	 */
	public SeparateChainingHashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	/**
	 * create an entry set for the separate chaining map
	 * 
	 * @return the created key value entry iterable
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				// Each bucket contains a map, so include
				// all entries in the entrySet for the map
				// at the current bucket
				for (Entry<K, V> entry : table[i].entrySet()) {
					list.addLast(entry);
				}
			}
		}
		return list;
	}

	/**
	 * create table for separate chaining map
	 * 
	 * @param capacity to create table for
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		// Example -- change this to whatever map you'd like
		table = new AVLTreeMap[capacity];
		size = 0;
	}

	/**
	 * get the value bucket
	 * 
	 * @param hash to get value at
	 * @param key  to get value at
	 * @return the value
	 */
	@Override
	public V bucketGet(int hash, K key) {
		// Get the bucket at the specified index in the hash table
		Map<K, V> bucket = table[hash];
		// If there is no map in the bucket, then the entry does not exist
		if (bucket == null) {
			return null;
		}
		// Otherwise, delegate to the existing map's get method to return the value
		return bucket.get(key);
	}

	/**
	 * put a key value entry pair in map
	 * 
	 * @param key   to put into map
	 * @param value to put into map
	 * @return the value that was put into the map
	 */
	@Override
	public V bucketPut(int hash, K key, V value) {
		Map<K, V> bucket = table[hash];
		if (bucket == null) {
			table[hash] = new AVLTreeMap<K, V>();
			bucket = table[hash];
		}
		var prevSize = bucket.size();
		var v = bucket.put(key, value);
		var currSize = bucket.size();
		size += currSize - prevSize;
		return v;
	}

	/**
	 * remove a key
	 * 
	 * @param key to be removed
	 * @return the removed value or null if key is not valid
	 */
	@Override
	public V bucketRemove(int hash, K key) {
		Map<K, V> bucket = table[hash];
		if (bucket == null) {
			return null;
		}
		var prevSize = bucket.size();
		var v = bucket.remove(key);
		var currSize = bucket.size();
		size -= prevSize - currSize;
		return v;
	}

	/**
	 * get size of map
	 * 
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * get the capacity of the hash map
	 * 
	 * @return the capacity
	 */
	@Override
	protected int capacity() {
		return table.length;
	}
}