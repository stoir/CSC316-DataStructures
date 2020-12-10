package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * 
 * @author Dr. King
 * @author Sanjana Cheerla
 * @param <E> generic parameter E
 */
public interface Sorter<E> {

	/**
	 * The sort method, modifies the original data and sorts from smallest to
	 * biggest value.
	 * @param data the array being sorted
	 */
	public void sort(E[] data);
}
