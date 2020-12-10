package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * priority queue implemented from a heap
 * 
 * CODE is from data structures and algorithms in java 6th edition. Book by
 * Michael T. Goodrich and Roberto Tamassia
 * 
 * @author sanjana cheerla
 *
 * @param <K> key
 * @param <V> value
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	// Remember that heaps can be easily implemented using an internal array
	// representation
	// versus a linked representation.

	/**
	 * internal representation of heap
	 */
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * constructor to create a heap pq
	 * 
	 * @param comparator for queue
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * create a heap pq without comparator
	 */
	public HeapPriorityQueue() {
		this(null);
	}

	//////////////////////////////////////////
	// ADT Operations
	//////////////////////////////////////////

	/**
	 * insert a key value to the queue
	 * 
	 * @param key   to insert
	 * @param value to insert
	 * @return old entry value or null
	 */
	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size() - 1);
		return temp;
	}

	/**
	 * get the min value
	 * 
	 * @return min value
	 */
	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * delete the min value
	 * 
	 * @return deleted value
	 */
	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> tmp = list.get(0);
		swap(0, list.size() - 1);
		list.remove(list.size() - 1);
		downHeap(0);
		return tmp;
	}

	/**
	 * get the size of the priority queue
	 * 
	 * @return size
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * go up the heap
	 * 
	 * @param index to start at
	 */
	protected void upHeap(int index) {
		while (index > 0) {
			int parent = parent(index);
			if (compare(list.get(index).getKey(), list.get(parent).getKey()) >= 0) {
				break;
			}
			swap(index, parent);
			index = parent;
		}
	}

	/**
	 * swap index1 with index2
	 * 
	 * @param index1 to swap
	 * @param index2 to swap
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * go down the heap
	 * 
	 * @param index to start at
	 */
	protected void downHeap(int index) {
		while (hasLeft(index)) {
			int left = left(index);
			int small = left;
			if (hasRight(index)) {
				int right = right(index);
				if (compare(list.get(left).getKey(), list.get(right).getKey()) > 0) {
					small = right;
				}
			}
			if (compare(list.get(small).getKey(), list.get(index).getKey()) >= 0) {
				break;
			}
			swap(index, small);
			index = small;
		}
	}

	//////////////////////////////////////////////////
	// Convenience methods to help abstract the math
	// involved in jumping to parent or children
	//////////////////////////////////////////////////

	/**
	 * parent of index
	 * 
	 * @param index to find parent of
	 * @return parent
	 */
	protected int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * left of index
	 * 
	 * @param index to find left of
	 * @return left
	 */
	protected int left(int index) {
		return 2 * index + 1;
	}

	/**
	 * right of index
	 * 
	 * @param index to find right of
	 * @return right
	 */
	protected int right(int index) {
		return 2 * index + 2;
	}

	/**
	 * check if index has left
	 * 
	 * @param index to check
	 * @return true if left exists, false otherwise
	 */
	protected boolean hasLeft(int index) {
		return left(index) < list.size();
	}

	/**
	 * check if index has right
	 * 
	 * @param index to check
	 * @return true if right exists, false otherwise
	 */
	protected boolean hasRight(int index) {
		return right(index) < list.size();
	}
}
