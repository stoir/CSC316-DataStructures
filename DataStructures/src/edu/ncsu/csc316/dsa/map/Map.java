package edu.ncsu.csc316.dsa.map;

/**
 * Map interface that extends iterable. Holds all methods needed for map data
 * structure.
 * 
 * @author sanjana cheerla
 *
 * @param <K> key for map
 * @param <V> value for key in map
 */
public interface Map<K, V> extends Iterable<K> {

	/**
	 * entrySet iterable object of an entry key value pair
	 * 
	 * @return the entrySet iterable object
	 */
	Iterable<Entry<K, V>> entrySet();

	/**
	 * get the value of the key
	 * 
	 * @param key to get the value from
	 * @return the value at key or null if the key doesn't exist
	 */
	V get(K key);

	/**
	 * check to see if the map is empty
	 * 
	 * @return true if the map is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * put a key value entry pair in map
	 * 
	 * @param key   to put into map
	 * @param value to put into map
	 * @return the value that was put into the map
	 */
	V put(K key, V value);

	/**
	 * remove a key
	 * 
	 * @param key to be removed
	 * @return the removed value or null if key is not valid
	 */
	V remove(K key);

	/**
	 * get the size of the map
	 * 
	 * @return size of map
	 */
	int size();

	/**
	 * iterable to iterate though values
	 * 
	 * @return the created iterable
	 */
	Iterable<V> values();

	/**
	 * Map Key Value pair interface. K is the Key for a map, and V is the value for
	 * a Key
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key for map
	 * @param <V> value for key
	 */
	interface Entry<K, V> {
		/**
		 * get the Key in a map entry
		 * 
		 * @return the key value
		 */
		K getKey();

		/**
		 * get the value for key in a map entry
		 * 
		 * @return the value for key
		 */
		V getValue();

		/**
		 * Set the key value
		 * 
		 * @param key the key value to set
		 * @return the key value that was set
		 */
		K setKey(K key);

		/**
		 * Set the value for a key
		 * 
		 * @param value to set for a key
		 * @return the previous value that key held
		 */
		V setValue(V value);
	}
}
