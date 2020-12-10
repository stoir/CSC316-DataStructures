package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * unordered array map based off a positional linked list
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/**
	 * a positional linked list to implement the map data structure
	 */
	private PositionalList<Entry<K, V>> list;

	/**
	 * create a new unordered linked map
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}

	/**
	 * look up the location of a key
	 * 
	 * @param key to loop up the value for
	 * @return -1 if the key does not exist, else return the location of key
	 */
	private Position<Entry<K, V>> lookUp(K key) {
		for (var i : list.positions())
			if (i.getElement().getKey().equals(key))
				return i;
		return null;
	}

	/**
	 * get the value of the key
	 * 
	 * @param key to get the value from
	 * @return the value at key or null if the key doesn't exist
	 */
	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			return null;
		}
		var tmp = p.getElement().getValue();
		this.moveToFront(p);
		return tmp;
	}

	/**
	 * move position to the front
	 * 
	 * @param position to move to the front
	 */
	private void moveToFront(Position<Entry<K, V>> position) {
		final var element = position.getElement();
		this.list.remove(position);
		this.list.addFirst(element);
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
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			list.addFirst(new MapEntry<K, V>(key, value));
			return null;
		}
		var prev = p.getElement().getValue();
		p.getElement().setValue(value);
		this.moveToFront(p);
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
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null)
			return null;
		var removed = p.getElement().getValue();
		list.remove(p);
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
	 * entrySet iterable object of an entry key value pair
	 * 
	 * @return the entrySet iterable object
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for (Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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