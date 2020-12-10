package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * Abstract map to hold common functionality of maps, implements the map
 * interface Holds map entry, key iterator, value iterator inner classes along
 * with common methods used in classes that inherit this class
 * 
 * @author sanjana cheerla
 *
 * @param <K> key in maps
 * @param <V> value of key
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * protected class to create a map entry consisting of key and value pairs
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key in maps
	 * @param <V> value of key
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		/**
		 * key value of map
		 */
		private K key;
		/**
		 * value of key
		 */
		private V value;

		/**
		 * create a map entry
		 * 
		 * @param key   value
		 * @param value for key
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * get the value of the key
		 * 
		 * @return the value at key
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * get the value for key in a map entry
		 * 
		 * @return the value for key
		 */
		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Set the key value
		 * 
		 * @param key the key value to set
		 * @return the key value that was set
		 */
		@Override
		public K setKey(K key) {
			this.key = key;
			return this.key;
		}

		/**
		 * Set the value for a key
		 * 
		 * @param value to set for a key
		 * @return the previous value that key held
		 */
		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * Key iterator to iterate through key values in map implements iterator
	 * 
	 * @author sanjana cheerla
	 */
	protected class KeyIterator implements Iterator<K> {

		/**
		 * iterator object for Entry values in map
		 */
		private Iterator<Entry<K, V>> it;

		/**
		 * constructor to create a key iterator object
		 * 
		 * @param iterator object
		 */
		public KeyIterator(Iterator<Entry<K, V>> iterator) {
			it = iterator;
		}

		/**
		 * check to see if there are more keys in map
		 * 
		 * @return true if there is a next value, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * get the next value in map and iterate forward
		 * 
		 * @return the key of the next element
		 */
		@Override
		public K next() {
			return it.next().getKey();
		}

		/**
		 * remove operation for iterator
		 * 
		 * @throws UnsupportedOperationException with the message "The remove operation
		 *                                       is not supported yet."
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * value iterator to iterate through values in map implements iterator
	 * 
	 * @author sanjana cheerla
	 */
	protected class ValueIterator implements Iterator<V> {

		/**
		 * iterator object for Entry values in map
		 */
		private Iterator<Entry<K, V>> it;

		/**
		 * constructor to create a value iterator object
		 * 
		 * @param iterator object
		 */
		public ValueIterator(Iterator<Entry<K, V>> iterator) {
			it = iterator;
		}

		/**
		 * check to see if there are more values in map
		 * 
		 * @return true if there is a next value, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * get the next value in map and iterate forward
		 * 
		 * @return the key of the next element
		 */
		@Override
		public V next() {
			return it.next().getValue();
		}

		/**
		 * remove operation for iterator
		 * 
		 * @throws UnsupportedOperationException with the message "The remove operation
		 *                                       is not supported yet."
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * checks too see if map is empty
	 * 
	 * @return true if map is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * creates a new key iterator object
	 * 
	 * @return created object
	 */
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}

	/**
	 * iterable to iterate though values
	 * 
	 * @return the created iterable
	 */
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

	/**
	 * value iterable class that implements iterable to create a new value iterator
	 * object
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class ValueIterable implements Iterable<V> {

		/**
		 * creates a new value iterator object
		 * 
		 * @return created object
		 */
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator(entrySet().iterator());
		}

	}

}