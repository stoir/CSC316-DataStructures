package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * Implement quicksort according to the psuedo code provided in lab 4 github
 * page
 * 
 * @author Sanjana Cheerla
 *
 * @param <E> generic parameter E
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * pivot selector for first element being the pivot
	 */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	/**
	 * pivot selector for last element being the pivot
	 */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
	/**
	 * pivot selector for middle element being the pivot
	 */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
	/**
	 * pivot selector for random element being the pivot
	 */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	/**
	 * pivot for the sort
	 */
	private PivotSelector selector;

	/**
	 * Create a Quick sorter object with a comparator and a selector
	 * 
	 * @param comparator for the quick sort
	 * @param selector   for the quick sort
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Create a Quick sorter object with a comparator
	 * 
	 * @param comparator for the quick sort
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Create a Quick sorter object with a selector
	 * 
	 * @param selector for the quick sort
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Generic/Default Constructor
	 */
	public QuickSorter() {
		this(null, null);
	}

	/**
	 * Set the selector for the quick sort, if the selector is null, a new random
	 * element selector is used
	 * 
	 * @param selector
	 */
	private void setSelector(PivotSelector selector) {
		if (selector == null) {
			selector = new RandomElementSelector();
		}
		this.selector = selector;
	}

	/**
	 * Sort the data using quick sort
	 * 
	 * @param data array to be sorted
	 */
	@Override
	public void sort(E[] data) {
		int low = 0;
		int high = data.length - 1;
		quickSort(data, low, high);
	}

	/**
	 * quick sort the data
	 * 
	 * @param data array to sort
	 * @param low  point of the data
	 * @param high point of the data
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}

	/**
	 * return the index of the pivot element, after moving values less than pivot to
	 * be before the pivot index and values greater than the pivot to be after the
	 * pivot index
	 * 
	 * 
	 * @param data array to sort
	 * @param low  point of the data
	 * @param high point of the data
	 * @return the index of the pivot element
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}

	/**
	 * swap pivotIndex with high from data array
	 * 
	 * @param data       array
	 * @param pivotIndex to swap with high
	 * @param high       to swap with pivot index
	 */
	private void swap(E[] data, int pivotIndex, int high) {
		E x = data[pivotIndex];
		E y = data[high];
		data[pivotIndex] = y;
		data[high] = x;
	}

	/**
	 * return the index of the pivot element, after moving values less than pivot to
	 * be before the pivot index and values greater than the pivot to be after the
	 * pivot index. Helper method for partition() method
	 * 
	 * 
	 * @param data array to sort
	 * @param low  point of the data
	 * @param high point of the data
	 * @return the index of the pivot element
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int idx = low;
		for (int j = low; j < high; j++) {
			if (this.compare(data[j], pivot) < 0) {
				swap(data, idx, j);
				idx += 1;
			}
		}
		swap(data, idx, high);

		return idx;
	}

	/**
	 * Inner interface to select the pivot for quick sort
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}

	/**
	 * Select the first element for the pivot for quick sort, implements pivot
	 * selector
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private static class FirstElementSelector implements PivotSelector {

		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		@Override
		public int selectPivot(int low, int high) {
			return low;
		}

	}

	/**
	 * Select the last element for the pivot for quick sort, implements pivot
	 * selector
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private static class LastElementSelector implements PivotSelector {

		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
	}

	/**
	 * Select the middle element for the pivot for quick sort, implements pivot
	 * selector
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private static class MiddleElementSelector implements PivotSelector {

		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		@Override
		public int selectPivot(int low, int high) {
			return (high - low) / 2 + low;
		}
	}

	/**
	 * Select the random element for the pivot for quick sort, implements pivot
	 * selector
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private static class RandomElementSelector implements PivotSelector {

		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		@Override
		public int selectPivot(int low, int high) {
			Random random = new Random();
			return random.nextInt(high - low) + low;
		}
	}

}