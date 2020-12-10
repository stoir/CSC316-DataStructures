package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * unordered array map based off an array based list
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	/**
	 * Use the adapter pattern to delegate to our existing array-based list
	 * implementation
	 * 
	 * an array based list to implement the map data structure
	 */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * create a new unordered map
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * look up the location of a key
	 * 
	 * @param key to loop up the value for
	 * @return -1 if the key does not exist, else return the location of key
	 */
	private int lookUp(K key) {
		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i).getKey() == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * get the value of the key
	 * 
	 * @param key to get the value from
	 * @return the value at key or null if the key doesn't exist
	 */
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		return this.transpose(index);
	}

	/**
	 * put a key value entry pair in map
	 * 
	 * @param key   to put into map
	 * @param value to put into map
	 * @return the old value that was put into the map at the same key if the key
	 *         exists, else returns null if the key was added and it did not already
	 *         exist in the map
	 */
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			list.addFirst(new MapEntry<K, V>(key, value));
			return null;
		}
		var prev = this.list.get(index).getValue();
		this.list.get(index).setValue(value);
		this.transpose(index);

		return prev;
	}

	/**
	 * remove a key from the map
	 * 
	 * @param key to be removed
	 * @return null if the key doesn't exist, else the value of the key that was
	 *         removed
	 */
	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		var removed = list.get(index).getValue();
		list.remove(index);
		return removed;
	}

	/**
	 * get the size of the map
	 * 
	 * @return size of map
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * transpose to reduce run time
	 * 
	 * @param index to transpose
	 * @return the first value in list
	 */
	private V transpose(int index) {
		if (index > 0) {
			this.swap(index, index - 1);
			return this.list.get(index - 1).getValue();
		}
		return this.list.first().getValue();
	}

	/**
	 * swap i values with j
	 * 
	 * @param i to swap with j
	 * @param j to swap with i
	 */
	private void swap(int i, int j) {
		var a = this.list.get(i);
		var b = this.list.get(j);
		this.list.set(i, b);
		this.list.set(j, a);
	}

	/**
	 * entrySet iterable object of an entry key value pair
	 * 
	 * @return the entrySet iterable object
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return list;
	}

	/**
	 * toString to represent a unordered array map
	 * 
	 * @return string representation of unordered array map
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while (it.hasNext()) {
			sb.append(it.next().getKey());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}