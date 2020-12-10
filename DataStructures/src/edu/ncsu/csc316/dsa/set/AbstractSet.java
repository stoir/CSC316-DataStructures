package edu.ncsu.csc316.dsa.set;

/**
 * abstract set, implements set
 * 
 * @author sanjana cheerla
 * 
 * @param <E> generic param
 */
public abstract class AbstractSet<E> implements Set<E> {

	/**
	 * add all elements from set
	 * 
	 * @param other elements to add
	 */
	@Override
	public void addAll(Set<E> other) {
		for (E element : other) {
			add(element);
		}
	}

	/**
	 * retain all elements from set
	 * 
	 * @param other elements to retain
	 */
	@Override
	public void retainAll(Set<E> other) {
		for (E element : this) {
			if (!other.contains(element)) {
				remove(element);
			}
		}
	}

	/**
	 * remove all elements from set
	 * 
	 * @param other elements to remove
	 */
	@Override
	public void removeAll(Set<E> other) {
		for (E element : other) {
			remove(element);
		}
	}

	/**
	 * check to see if set is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}