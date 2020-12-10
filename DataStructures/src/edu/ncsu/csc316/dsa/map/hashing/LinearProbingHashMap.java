package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * linear probing hash map, extends abstract hash map
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/**
	 * array of table entries
	 */
	private TableEntry<K, V>[] table;

	/**
	 * size of linear probing hash map
	 */
	private int size;

	/**
	 * construct a default map
	 */
	public LinearProbingHashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY, false);
	}

	/**
	 * constructor to create a default map for testing
	 * 
	 * @param isTesting used for testing
	 */
	public LinearProbingHashMap(boolean isTesting) {
		this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
	}

	/**
	 * create a map based on capacity
	 * 
	 * @param capacity for map
	 */
	public LinearProbingHashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * create a map with capacity and boolean for testing or not
	 * 
	 * @param capacity  for map
	 * @param isTesting used for testing
	 */
	public LinearProbingHashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	/**
	 * create an entry set for the linear probing hash map
	 * 
	 * @return the created key value entry iterable
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
		for (int i = 0; i < table.length; i++) {
			if (!isAvailable(i)) {
				list.addLast(table[i]);
			}
		}
		return list;
	}

	/**
	 * create table for linear probing hash map
	 * 
	 * @param capacity to create table for
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		table = (TableEntry<K, V>[]) new TableEntry[capacity];
		size = 0;
	}

	/**
	 * determine whether a bucket has an entry or not
	 * 
	 * @param index to check if the bucket has an entry or not
	 * @return true if there is an entry, false otherwise
	 */
	private boolean isAvailable(int index) {
		return (table[index] == null || table[index].isDeleted());
	}

	/**
	 * Helper method to find the bucket for an entry; If the entry *is* in the map,
	 * returns the index of the bucket. If the entry is *not* in the map, returns
	 * -(a + 1) to indicate that the entry should be added at index a
	 * 
	 * @param index to find bucket for
	 * @param key   to find bucket for
	 * @return the index of the bucket, negative value(-(a + 1) if the entry should
	 *         be added at index a
	 */
	private int findBucket(int index, K key) {
		int avail = -1;
		int j = index;
		do {
			if (isAvailable(j)) {
				if (avail == -1)
					avail = j;
				if (table[j] == null)
					break;
			} else if (table[j].getKey().equals(key))
				return j;
			j = (j + 1) % capacity();
		} while (j != index);
		return -(avail + 1);
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
		int index = findBucket(hash, key);
		if (index < 0)
			return null;
		return table[index].getValue();
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
		int index = findBucket(hash, key);
		if (index >= 0)
			return table[index].setValue(value);
		int tableIdx = -(index + 1);
		table[tableIdx] = new TableEntry<>(key, value);
		size++;
		return null;
	}

	/**
	 * remove a key
	 * 
	 * @param key to be removed
	 * @return the removed value or null if key is not valid
	 */
	@Override
	public V bucketRemove(int hash, K key) {
		int index = findBucket(hash, key);
		if (index < 0)
			return null;
		V removed = table[index].getValue();
		table[index].setDeleted(true);
		size--;
		return removed;
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

	/**
	 * table entry for linear probing hash hmap
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private static class TableEntry<K, V> extends MapEntry<K, V> {

		/**
		 * if the table entry is deleted
		 */
		private boolean isDeleted;

		/**
		 * create a new table entry
		 * 
		 * @param key   for entry
		 * @param value for entry
		 */
		public TableEntry(K key, V value) {
			super(key, value);
			setDeleted(false);
		}

		/**
		 * see if entry is deleted
		 * 
		 * @return true if deleted, false otherwise
		 */
		public boolean isDeleted() {
			return isDeleted;
		}

		/**
		 * set if table entry is deleted or not
		 * 
		 * @param deleted to set table entry to is deleted or not
		 */
		public void setDeleted(boolean deleted) {
			isDeleted = deleted;
		}
	}
}