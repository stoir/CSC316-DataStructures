package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Sanjana Cheerla
 * @param <E> generic parameter E
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Construct an InserstionSorter object
	 */
	public InsertionSorter() {
		this(null);
	}

	/**
	 * Constructs an Insertion Sorter object using a specific comparator
	 * 
	 * @param comparator specific comparator
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * The sort method, modifies the original data and sorts from smallest to
	 * biggest value using the insertion sort algorithm.
	 * 
	 * @param data the data being sorted
	 */
	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && super.compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}

	}

}
