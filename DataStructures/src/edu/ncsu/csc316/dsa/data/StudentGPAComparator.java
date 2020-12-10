package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * 
 * @author Dr. King
 * @author Sanjana Cheerla
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 * 
	 * @param one student one being compared with
	 * @param two student two being compared with
	 * @return 0 if they have the same gpa number, negative if one's gpa number is
	 *         greater than two's gpa number, positive if one's gpa number is less
	 *         than two's gpa number
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() > two.getGpa()) {
			return -1;
		} else if (one.getGpa() < two.getGpa()) {
			return 1;
		}
		return 0;
	}

}
