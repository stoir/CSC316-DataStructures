package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * abstract hash map extends abstract map. A map enabled for hashing features.
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

	/**
	 * An initial capacity for the hash table
	 */
	protected static final int DEFAULT_CAPACITY = 17;

	// From our discussion in class, the expected number of probes
	// for separate chaining remains relatively the same no matter
	// what the load factor may be. However, for linear probing, to
	// reduce the chance of having large clusters, we will resize
	// when the load factor reaches 0.5

	/**
	 * load factor to resize at
	 */
	private static final double MAX_LOAD_FACTOR = 0.5;

	/**
	 * default prime value
	 */
	protected static final int DEFAULT_PRIME = 109345121;

	/**
	 * Alpha value for MAD compression This implementation uses a variation of the
	 * MAD method where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
	 */
	private long alpha;

	/**
	 * Beta value for MAD compression This implementation uses a variation of the
	 * MAD method where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
	 */
	private long beta;

	/**
	 * The prime number to use for compression strategy
	 */
	private int prime;

	/**
	 * abstract hash map constructor
	 * 
	 * @param capacity  for map
	 * @param isTesting set true for testing with out random numbers
	 */
	public AbstractHashMap(int capacity, boolean isTesting) {
		if (isTesting) {
			alpha = 1;
			beta = 1;
			prime = 7;
		} else {
			Random rand = new Random();
			alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
			beta = rand.nextInt(DEFAULT_PRIME);
			prime = DEFAULT_PRIME;
		}
		createTable(capacity);
	}

	/**
	 * compress the keyy value
	 * 
	 * @param key to compress
	 * @return compressed value
	 */
	private int compress(K key) {
		return (int) ((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
	}

	/**
	 * put a key value entry pair in map
	 * 
	 * @param key   to put into map
	 * @param value to put into map
	 * @return the value that was put into the map
	 */
	@Override
	public V put(K key, V value) {
		V ret = bucketPut(compress(key), key, value);
		if ((double) size() / capacity() > MAX_LOAD_FACTOR) {
			resize(2 * capacity() + 1);
		}
		return ret;
	}

	/**
	 * get the value of the key
	 * 
	 * @param key to get the value from
	 * @return the value at key or null if the key doesn't exist
	 */
	@Override
	public V get(K key) {
		return bucketGet(compress(key), key);
	}

	/**
	 * remove a key
	 * 
	 * @param key to be removed
	 * @return the removed value or null if key is not valid
	 */
	@Override
	public V remove(K key) {
		return bucketRemove(compress(key), key);
	}

	/**
	 * resize the hash map
	 * 
	 * @param newCapacity to resize to
	 */
	private void resize(int newCapacity) {
		List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
		for (Entry<K, V> entry : entrySet()) {
			list.addLast(entry);
		}
		createTable(newCapacity);
		for (Entry<K, V> entry : list) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * get the capacity of the hash map
	 * 
	 * @return the capacity
	 */
	protected abstract int capacity();

	/**
	 * create a table
	 * 
	 * @param capacity for table
	 */
	protected abstract void createTable(int capacity);

	/**
	 * get the value bucket
	 * 
	 * @param hash to get value at
	 * @param key  to get value at
	 * @return the value
	 */
	protected abstract V bucketGet(int hash, K key);

	/**
	 * put the bucket
	 * 
	 * @param hash  to put bucket at
	 * @param key   to put bucket at
	 * @param value of bucket
	 * @return old bucket value
	 */
	protected abstract V bucketPut(int hash, K key, V value);

	/**
	 * remove bucket
	 * 
	 * @param hash to remove at
	 * @param key  to remove at
	 * @return the removed value
	 */
	protected abstract V bucketRemove(int hash, K key);
}
