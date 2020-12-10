package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * 
 * @author Dr. King
 * @author sanjana cheerla
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * Construct an SelectionSorter object
	 */
	public SelectionSorter() {
		this(null);
	}

	/**
	 * constructs a selection sorter object based on the comparator
	 * 
	 * @param comparator being set with selection sorter
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * The sort method, modifies the original data and sorts from smallest to
	 * biggest value using the selection sort algorithm.
	 * 
	 * @param data the data being sorted
	 */
	@Override
	public void sort(E[] data) {
		for (int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if (super.compare(data[j], data[min]) < 0) {
					min = j; 
				}
			}
			if (!(i == min)) {
				E x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}
	}
}
