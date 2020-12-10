package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Search table map, this is an ordered map hence it extends comparable and
 * abstract sorted map. Map is maintained through an array based list of entry
 * key value pair objects and through binary search.
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/**
	 * array based list of entry objects for map
	 */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * create a new search table map
	 */
	public SearchTableMap() {
		this(null);
	}

	/**
	 * create a new search table map
	 * 
	 * @param compare comparator for the map
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * look up the location of a key
	 * 
	 * @param key to loop up the value for
	 * @return -1 if the key does not exist, else return the location of key
	 */
	private int lookUp(K key) {
		return this.binarySearchHelper(0, this.size() - 1, key);
	}

	/**
	 * use binary search to find the location of a key
	 * 
	 * @param min size of where to search
	 * @param max size of where to search
	 * @param key to search for
	 * @return -1 if the key does not exist, else return the location of key
	 */
	private int binarySearchHelper(int min, int max, K key) {
		if (min > max) {
			return -1 * (min + 1);
		}
		int mid = (max + min) / 2;
		if (this.compare(key, list.get(mid).getKey()) == 0) {
			return mid;
		} else if (this.compare(key, list.get(mid).getKey()) > 0) {
			return binarySearchHelper(mid + 1, max, key);
		} else {
			return binarySearchHelper(min, mid - 1, key);
		}
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
	 * get the value of the key
	 * 
	 * @param key to get the value from
	 * @return the value at key or null if the key doesn't exist
	 */
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index < 0) {
			return null;
		}
		return this.list.get(index).getValue();
	}

	/**
	 * entrySet iterable object of an entry key value pair
	 * 
	 * @return the entrySet iterable object
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for (Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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
		if (index < 0) {
			int i = -1 * (index + 1);
			list.add(i, new MapEntry<K, V>(key, value));
			return null;
		}
		var prev = this.list.get(index).getValue();
		this.list.get(index).setValue(value);
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
		if (index < 0) {
			return null;
		}
		var removed = list.get(index).getValue();
		list.remove(index);
		return removed;
	}

	/**
	 * toString to represent a search table map
	 * 
	 * @return string representation of search table map
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