package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests Student.java Class
 * 
 * @author Sanjana Cheerla
 */
public class StudentTest {

	/**
	 * Student used for testing
	 */
	private Student sOne;
	/**
	 * Student used for testing
	 */
	private Student sTwo;
	/** d
	 * Student used for testing
	 */
	private Student sFive;
	/**
	 * Student used for testing
	 */
	private Student sOneVTwo;
	/**
	 * Student used for testing
	 */
	private Student sOneVFour;

	/**
	 * Set up method, initialize all students
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sOneVTwo = new Student("OneFirst", "OneLast", 2, 1, 1.0, "oneUnityID");
		sOneVFour = new Student("OneFirstVFour", "OneLast", 1, 1, 1.0, "oneUnityID");
	}

	/**
	 * Tries to make Illegal student objects
	 */
	@Test
	public void illegalStudentTest() {
		Student sSix = null;
		try {
			sSix = new Student("", "sixLast", 6, 6, 6.0, "sixUnityID");
		} catch (IllegalArgumentException e) {
			assertTrue(sSix == null);
		}

		try {
			sSix = new Student("sixFirst", "", 6, 6, 6.0, "sixUnityID");
		} catch (IllegalArgumentException e) {
			assertTrue(sSix == null);
		}

		try {
			sSix = new Student("sixFirst", "sixLast", -6, 6, 6.0, "sixUnityID");
		} catch (IllegalArgumentException e) {
			assertTrue(sSix == null);
		}

		try {
			sSix = new Student("sixFirst", "sixLast", 6, -6, 6.0, "sixUnityID");
		} catch (IllegalArgumentException e) {
			assertTrue(sSix == null);
		}

		try {
			sSix = new Student("sixFirst", "sixLast", 6, 6, -6.0, "sixUnityID");
		} catch (IllegalArgumentException e) {
			assertTrue(sSix == null);
		}

		try {
			sSix = new Student("sixFirst", "sixLast", 6, 6, 6.0, "");
		} catch (IllegalArgumentException e) {
			assertTrue(sSix == null);
		}
	}
	
	/**
	 * Test setFirst() and setFirst() method
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Test setLast() and getLast() method
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Test setId() and getId() method
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Test setGpa() and getGpa() method
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}

	/**
	 * Test setUnityID() and getUnityID() method
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Test setCreditHours() and getCreditHours() method
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(100);
		assertEquals(100, sOne.getCreditHours());
	}
	
	/**
	 * Test compareTo() method
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sOne.compareTo(sOneVTwo) < 0);
		assertFalse(sOne.equals(sOneVFour));
	}

	/**
	 * Test equals() method
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sOne));
		assertFalse(sOne.equals(sFive));
		assertFalse(sOne.equals(sOneVTwo));
	}

	/**
	 * Test toString() method
	 */
	@Test
	public void testToString() {
		assertEquals("OneFirst OneLast, id=1, creditHours=1, gpa=1.0, unityId=oneUnityID", sOne.toString());
	}
	
	/**
	 * Test hashcode() method
	 */
	@Test
	public void testHashCode() {
		assertFalse(sOne.hashCode() == sTwo.hashCode());
	}
}
