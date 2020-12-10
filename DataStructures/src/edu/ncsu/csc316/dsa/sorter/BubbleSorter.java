package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the Bubble sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Sanjana Cheerla
 * @param <E> generic parameter E
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Construct a BubbleSorter object
	 */
	public BubbleSorter() {
		this(null);
	}

	/**
	 * Constructs a bubble Sorter object using a specific comparator
	 * 
	 * @param comparator specific comparator
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * The sort method, modifies the original data and sorts from smallest to
	 * biggest value using the bubble sort algorithm.
	 * 
	 * @param data the data being sorted
	 */
	@Override
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (super.compare(data[i], data[i - 1]) < 1) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}

}