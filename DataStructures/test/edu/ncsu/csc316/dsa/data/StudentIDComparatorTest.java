package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests StudentIDComparator.java
 * 
 * @author Sanjana Cheerla
 */
public class StudentIDComparatorTest {

	/**
	 * Student Test Object
	 */
	private Student sOne;
	/**
	 * Student Test Object
	 */
	private Student sTwo;
	/**
	 * Student Test Object
	 */
	private Student sThree;
	/**
	 * Student Test Object
	 */
	private Student sFour;
	/**
	 * Student Test Object
	 */
	private Student sFive;
	/**
	 * Comparator object
	 */
	private StudentIDComparator comparator;

	/**
	 * Set up tests by initializing valid students
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Test compare method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertTrue(comparator.compare(sOne, sOne) == 0);
		assertTrue(comparator.compare(sOne, sFive) < 0);
		assertTrue(comparator.compare(sFive, sOne) > 0);
		assertTrue(comparator.compare(sFour, sThree) > 0);
	}


}
