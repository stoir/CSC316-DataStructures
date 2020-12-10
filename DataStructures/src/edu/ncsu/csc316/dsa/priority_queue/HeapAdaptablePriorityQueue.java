package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * adaptable heap priority queue implemented from a heap
 * 
 * CODE is from data structures and algorithms in java 6th edition. Book by
 * Michael T. Goodrich and Roberto Tamassia
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

	// Adaptable PQ Entries must be location-aware so that the
	// performance of replaceKey, replaceValue, and remove are O(log n)

	/**
	 * entry of an adaptable pq entry
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		/**
		 * index of entry
		 */
		private int index;

		/**
		 * create an entry
		 * 
		 * @param key   of entry
		 * @param value of entry
		 * @param index of entry
		 */
		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			setIndex(index);
		}

		/**
		 * get index
		 * 
		 * @return index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * set index
		 * 
		 * @param index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * create a heap adaptable pq
	 * 
	 * @param c comparator
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> c) {
		super(c);
	}

	/**
	 * create a heap adaptable pq without constructor
	 */
	public HeapAdaptablePriorityQueue() {
		this(null);
	}

	// Factory method for creating a new adaptable PQ entry
	/**
	 * insert a key value to the queue
	 * 
	 * @param key   to insert
	 * @param value to insert
	 * @return old entry value or null
	 */
	@Override
	protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
		// A new adaptable PQ Entry added to the heap will be at index size()
		AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
		return temp;
	}

	// Make sure the entry is a valid Adaptable PQ Entry and is located within the
	// heap structure
	/**
	 * validate entry
	 * 
	 * @param entry to validate
	 * @return valid entry
	 * @throws IllegalArgumentException with message "Entry is not a valid adaptable
	 *                                  priority queue entry."
	 */
	private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if (!(entry instanceof AdaptablePQEntry)) {
			throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
		}
		AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>) entry;
		if (temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
			throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
		}
		return temp;
	}

	/**
	 * swap index1 with index2
	 * 
	 * @param index1 to swap
	 * @param index2 to swap
	 */
	@Override
	public void swap(int index1, int index2) {
		// Delegate to the super class swap method
		super.swap(index1, index2);
		// But then update the index of each entry so that they remain location-aware
		((AdaptablePQEntry<K, V>) list.get(index1)).setIndex(index1);
		((AdaptablePQEntry<K, V>) list.get(index2)).setIndex(index2);
	}

	/**
	 * remove an entry from the queue
	 * 
	 * @param entry to remove
	 */
	@Override
	public void remove(Entry<K, V> entry) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		int index = temp.getIndex();
		if (index == list.size() - 1) {
			list.remove(list.size() - 1);
		} else {
			swap(index, list.size() - 1);
			list.remove(list.size() - 1);
			bubble(index);
		}
	}

	/**
	 * bubble to decide to go up heap or down heap
	 * 
	 * @param index of heap
	 */
	private void bubble(int index) {
		if (index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
			upHeap(index);
		} else {
			downHeap(index);
		}
	}

	/**
	 * replace the key of an entry
	 * 
	 * @param entry to replace key of
	 * @param key   to replace entry of
	 */
	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setKey(key);
		bubble(temp.getIndex());
	}

	/**
	 * replace the value of a key
	 * 
	 * @param entry to replace value of
	 * @param value to replace value of
	 */
	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setValue(value);
	}
}