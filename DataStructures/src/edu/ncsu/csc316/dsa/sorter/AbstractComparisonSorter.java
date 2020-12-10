package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract class that maintains comparator for bubble, insertion and selction
 * sort
 * 
 * @author Dr. Jason King
 * @author Sanjana Cheerla
 *
 * @param <E> generic parameter
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/**
	 * comparator object field that maintains the type of comparator being used for
	 * the object
	 */
	private Comparator<E> comparator;

	/**
	 * sets the comparator value to be compared with
	 * 
	 * @param comparator being set
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	/**
	 * Sets the comparator object, if the comparator is null, a new NaturalOrder
	 * comparator is created
	 * 
	 * @param comparator comparator being set
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			comparator = new NaturalOrder();
		}
		this.comparator = comparator;
	}

	/**
	 * Inner class that compares using the natural order of the object E
	 * 
	 * @author Dr.King, taken from lab 1 requirements
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * compares two value, return negative if 1 is smaller than 2 and return
	 * positive if 1 is bigger than 2, returns 0 if both are equal
	 * 
	 * @param data1 data being compared
	 * @param data2 data being compared
	 * @return negative if 1 is smaller than 2 and return positive if 1 is bigger
	 *         than 2, returns 0 if both are equal
	 */
	public int compare(E data1, E data2) {
		return comparator.compare(data1, data2);
	}
}