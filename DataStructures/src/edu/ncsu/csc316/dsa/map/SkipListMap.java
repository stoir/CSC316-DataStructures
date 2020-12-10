package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Skip list map, this is an ordered map hence it extends comparable and
 * abstract sorted map. Uses a skip list to create the map and maintain the
 * order of the map
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/**
	 * random coin toss variable
	 */
	private Random coinToss;

	/**
	 * start of skip list map
	 */
	private SkipListEntry<K, V> start;

	/**
	 * size of map
	 */
	private int size;

	/**
	 * height of skip list
	 */
	private int height;

	/**
	 * create a new skip list map
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * create a new skip list map with a specified comparator
	 * 
	 * @param compare comparator for skip list objects
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

	/**
	 * Helper method to determine if an entry is one of the sentinel -INFINITY or
	 * +INFINITY nodes (containing a null key)
	 * 
	 * @param entry for the skip list
	 * @return true if the value is sentinel, false if not
	 */
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}

	/**
	 * look up the location of a key
	 * 
	 * @param key to loop up the value for
	 * @return the skipListEntry of the looked up key
	 */
	private SkipListEntry<K, V> lookUp(K key) {
		SkipListEntry<K, V> current = start;
		while (current.getBelow() != null) {
			current = current.below;
			while (!isSentinel(current.next) && compare(key, current.next.getKey()) >= 0) {
				current = current.next;
			}
		}
		return current;
	}

	/**
	 * get the value of the key
	 * 
	 * @param key to get the value from
	 * @return the value at key or null if the key doesn't exist
	 */
	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (this.isSentinel(temp) || !(temp.getKey().equals(key))) {
			return null;
		}
		return temp.getValue();
	}

	/**
	 * get a new skip entry object with pointers to below and previous
	 * 
	 * @param prev  key value pair entry
	 * @param down  key value pair entry
	 * @param key   for new skip list entry
	 * @param value for new skip list entry
	 * @return a new entry with (k,v) and pointers to below and prev entries
	 */
	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
		SkipListEntry<K, V> newEntry = new SkipListEntry<K, V>(key, value);
		newEntry.setBelow(down);
		newEntry.setPrevious(prev);

		if (prev != null) {
			newEntry.setNext(prev.next);
			newEntry.prev.setNext(newEntry);
		}

		if (newEntry.next != null) {
			newEntry.next.setPrevious(newEntry);
		}

		if (down != null) {
			down.setAbove(newEntry);
		}
		return newEntry;
	}

	/**
	 * put a key value entry pair in map
	 * 
	 * @param key   to put into map
	 * @param value to put into map
	 * @return the old value that was put into the map at the same key if the key
	 *         exists, else returns new key value if the key was added and it did
	 *         not already exist in the map
	 */
	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> p = lookUp(key);
		if (p.getKey() == key) {
			var originalVal = p.getValue();
			while (p != null) {
				p.setValue(value);
				p = p.getAbove();
			}
			return originalVal;
		}
		SkipListEntry<K, V> q = null;
		var currentLevel = -1;
		do {
			currentLevel += 1;

			if (currentLevel >= height) {
				height += 1;
				var tail = start.next;
				start = insertAfterAbove(null, start, null, null);
				insertAfterAbove(start, tail, null, null);
			}

			q = this.insertAfterAbove(p, q, key, value);

			while (p.getAbove() == null) {
				p = p.getPrevious();
			}
			p = p.getAbove();
		} while (coinToss.nextInt(1) == 1);

		size += 1;

		return null;
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
		SkipListEntry<K, V> temp = lookUp(key);
		if (this.isSentinel(temp)) {
			return null;
		}
		var val = temp.getValue();
		while (temp != null) {
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			temp = temp.below;
		}
		size -= 1;
		return val;
	}

	/**
	 * get the size of the map
	 * 
	 * @return size of map
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * entrySet iterable object of an entry key value pair
	 * 
	 * @return the entrySet iterable object
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		SkipListEntry<K, V> current = start;
		while (current.below != null) {
			current = current.below;
		}
		current = current.next;
		while (!isSentinel(current)) {
			set.addLast(current);
			current = current.next;
		}
		return set;
	}

	/**
	 * toString to represent a skip list map
	 * 
	 * @return string representation of skip list map
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while (cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while (cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if (!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");

		return sb.toString();
	}

	// This method may be useful for testing or debugging.
	// You may comment-out this method instead of testing it, since
	// the full string will depend on the series of random coin flips
	// and will not have deterministic expected results.
//	public String toFullString() {
//		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
//		SkipListEntry<K, V> cursor = start;
//		SkipListEntry<K, V> firstInList = start;
//		while (cursor != null) {
//			firstInList = cursor;
//			sb.append("-INF -> ");
//			cursor = cursor.next;
//			while (cursor != null && !isSentinel(cursor)) {
//				sb.append(cursor.getKey() + " -> ");
//				cursor = cursor.next;
//			}
//			sb.append("+INF\n");
//			cursor = firstInList.below;
//		}
//		sb.append("]");
//		return sb.toString();
//	}

	/**
	 * skip list entry node
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {

		/**
		 * above node
		 */
		private SkipListEntry<K, V> above;

		/**
		 * below node
		 */
		private SkipListEntry<K, V> below;

		/**
		 * prev node
		 */
		private SkipListEntry<K, V> prev;

		/**
		 * next node
		 */
		private SkipListEntry<K, V> next;

		/**
		 * create a new skip list entry
		 * 
		 * @param key   for new skip list entry
		 * @param value for new skip list entry
		 */
		public SkipListEntry(K key, V value) {
			super(key, value);
			setAbove(null);
			setBelow(null);
			setPrevious(null);
			setNext(null);
		}

		/**
		 * get the below of skip list entry
		 * 
		 * @return below node
		 */
		public SkipListEntry<K, V> getBelow() {
			return below;
		}

		/**
		 * get the next of skip list entry
		 * 
		 * @return next node
		 */
		public SkipListEntry<K, V> getNext() {
			return next;
		}

		/**
		 * get the prev of skip list entry
		 * 
		 * @return previous node
		 */
		public SkipListEntry<K, V> getPrevious() {
			return prev;
		}

		/**
		 * get the above of skip list entry
		 * 
		 * @return above node
		 */
		public SkipListEntry<K, V> getAbove() {
			return above;
		}

		/**
		 * set the below value
		 * 
		 * @param down value to set
		 */
		public void setBelow(SkipListEntry<K, V> down) {
			this.below = down;
		}

		/**
		 * set the next value
		 * 
		 * @param next value to set
		 */
		public void setNext(SkipListEntry<K, V> next) {
			this.next = next;
		}

		/**
		 * set the previous value
		 * 
		 * @param prev value to set
		 */
		public void setPrevious(SkipListEntry<K, V> prev) {
			this.prev = prev;
		}

		/**
		 * set the above value
		 * 
		 * @param up value to set
		 */
		public void setAbove(SkipListEntry<K, V> up) {
			this.above = up;
		}
	}
}