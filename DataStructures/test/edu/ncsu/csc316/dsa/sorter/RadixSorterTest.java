package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests RadixSorter
 * 
 * @author sanjana cheerla
 *
 */
public class RadixSorterTest {

	/**
	 * Student object used for testing
	 */
	private Student sOne;
	/**
	 * Student object used for testing
	 */
	private Student sTwo;
	/**
	 * Student object used for testing
	 */
	private Student sThree;
	/**
	 * Student object used for testing
	 */
	private Student sFour;
	/**
	 * Student object used for testing
	 */
	private Student sFive;

	/**
	 * Sorter object
	 */
	private RadixSorter<Student> sorter;

	/**
	 * Set up students before
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		sorter = new RadixSorter<Student>();
	}

	/**
	 * Tests sort student
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
//		for(int i = 0; i < original.length; i++) {
//			System.out.println(original[i].toString());
//		}
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

	/**
	 * Tests with Student objects descending
	 */
	@Test
	public void testSortStudentDescending() {
		Student[] original = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

	/**
	 * Tests with Student objects ascending
	 */
	@Test
	public void testSortStudentAscending() {
		Student[] original = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

}
