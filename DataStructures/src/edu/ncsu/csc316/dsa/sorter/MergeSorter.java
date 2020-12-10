package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implement mergesort according to the psuedo code provided in lab 4 github
 * page
 * 
 * @author Sanjana Cheerla
 *
 * @param <E> generic parameter E
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Merge Sort constructor using the comparator variable
	 * 
	 * @param comparator for the merge sort
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Generic/Default Constructor
	 */
	public MergeSorter() {
		this(null);
	}

	/**
	 * Sort the data using merge sort
	 * 
	 * @param data array to be sorted
	 */
	@Override
	public void sort(E[] data) {
		if (data.length < 2) {
			return;
		}
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);

		sort(left);
		sort(right);
		merge(left, right, data);
	}

	/**
	 * Merges left and right array into total array
	 * 
	 * @param left       sorted array
	 * @param right      sorted array
	 * @param totalArray combined array for output merging both left and right
	 */
	private void merge(E[] left, E[] right, E[] totalArray) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < totalArray.length) {
			if ((rightIndex == right.length)
					|| (leftIndex < left.length && this.compare(left[leftIndex], right[rightIndex]) < 0)) {
				totalArray[leftIndex + rightIndex] = left[leftIndex];
				leftIndex += 1;
			} else {
				totalArray[leftIndex + rightIndex] = right[rightIndex];
				rightIndex += 1;
			}
		}
	}

}