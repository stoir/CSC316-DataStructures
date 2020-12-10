package edu.ncsu.csc316.dsa.sorter;


import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the QuickSorter class
 * 
 * @author sanjana cheerla
 *
 */
public class QuickSorterTest {

	/**
	 * Student object used for testing
	 */
	private Student sOne = new Student("AOneFirst", "AOneLast", 1, 1, 1.0, "AoneUnityID");
	/**
	 * Student object used for testing
	 */
	private Student sTwo = new Student("BTwoFirst", "BTwoLast", 2, 2, 2.0, "BtwoUnityID");
	/**
	 * Student object used for testing
	 */
	private Student sThree = new Student("CThreeFirst", "CThreeLast", 3, 3, 3.0, "CthreeUnityID");
	/**
	 * Student object used for testing
	 */
	private Student sFour = new Student("DFourFirst", "DFourLast", 4, 4, 4.0, "DfourUnityID");
	/**
	 * Student object used for testing
	 */
	private Student sFive = new Student("EFiveFirst", "EFiveLast", 5, 5, 5.0, "EfiveUnityID");

	/**
	 * Ascending data of Integers
	 */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/**
	 * descending data of Integers
	 */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/**
	 * Random data of Integers
	 */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/**
	 * ascending data of Integers
	 */
	private Student[] studentAscending = { sOne, sTwo, sThree, sFour, sFive };
	/**
	 * descending data of Students
	 */
	private Student[] studentDescending = { sFive, sFour, sThree, sTwo, sOne };
	/**
	 * Random data of Students
	 */
	private Student[] studentRandom = { sFour, sOne, sFive, sThree, sTwo };
	/**
	 * integer sorter
	 */
	private QuickSorter<Integer> integerSorter;
	/**
	 * student sorter
	 */
	private QuickSorter<Student> studentSorter;
	/**
	 * student sorter
	 */
	private QuickSorter<Student> studentSorterFirst;
	/**
	 * student sorter
	 */
	private QuickSorter<Student> studentSorterLast;
	/**
	 * student sorter
	 */
	private QuickSorter<Student> studentSorterMid;
	/**
	 * student sorter
	 */
	private QuickSorter<Student> studentSorterRand;
	/**
	 * student sorter
	 */
	private QuickSorter<Student> studentSorterComp;

	/**
	 * Sets up testing instances
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<Integer>();
		studentSorter = new QuickSorter<Student>();

		studentSorterFirst = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		studentSorterLast = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		studentSorterMid = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		studentSorterRand = new QuickSorter<Student>(QuickSorter.RANDOM_ELEMENT_SELECTOR);
		Comparator<Student> comp = Comparator.naturalOrder();
		studentSorterComp = new QuickSorter<Student>(comp);
	}

	/**
	 * Tests the sort() method with integers
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		integerSorter.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		integerSorter.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
	}

	/**
	 * Tests sort() with Student objects
	 */
	@Test
	public void testSortStudent() {
		studentSorter.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		studentSorter.sort(studentDescending);
		assertEquals(0, studentDescending[0].compareTo(sOne));
		assertEquals(0, studentDescending[1].compareTo(sTwo));
		assertEquals(0, studentDescending[2].compareTo(sThree));
		assertEquals(0, studentDescending[3].compareTo(sFour));
		assertEquals(0, studentDescending[4].compareTo(sFive));

		studentSorter.sort(studentRandom);
		assertEquals(0, studentRandom[0].compareTo(sOne));
		assertEquals(0, studentRandom[1].compareTo(sTwo));
		assertEquals(0, studentRandom[2].compareTo(sThree));
		assertEquals(0, studentRandom[3].compareTo(sFour));
		assertEquals(0, studentRandom[4].compareTo(sFive));

		studentSorterFirst.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		studentSorterLast.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		studentSorterRand.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		studentSorterMid.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		studentSorterComp.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));
	}
	
	
}