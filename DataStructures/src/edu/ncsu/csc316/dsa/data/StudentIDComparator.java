package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * 
 * @author Dr. King
 * @author Sanjana Cheerla
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * 
	 * @param one student one being compared with
	 * @param two student two being compared with
	 * @return 0 if they have the same ID number, negative if one's id number is
	 *         less than two's id number, positive if one's id number is greater
	 *         than two's id number
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getId() < two.getId()) {
			return -1;
		} else if (one.getId() > two.getId()) {
			return 1;
		}
		return 0;
	}

}
