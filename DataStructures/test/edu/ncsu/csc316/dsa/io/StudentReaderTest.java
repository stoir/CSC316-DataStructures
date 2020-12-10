package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests student Reader
 * 
 * @author sanjana cheerla
 */
public class StudentReaderTest {

	/**
	 * Tests to see first names are valid
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}

	/**
	 * Tests to see if last names are valid
	 */
	@Test
	public void testReadFile2() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Michael", contents[0].getLast());
		assertEquals("Marsh", contents[1].getLast());
		assertEquals("Mott", contents[2].getLast());
		assertEquals("Pease", contents[3].getLast());
		assertEquals("Seibert", contents[4].getLast());
		assertEquals("Matheson", contents[5].getLast());
		assertEquals("Terrell", contents[6].getLast());
		assertEquals("Runyon", contents[7].getLast());
		assertEquals("Woodbury", contents[8].getLast());
		assertEquals("Carrion", contents[9].getLast());
		assertEquals("Worth", contents[10].getLast());
		assertEquals("Mclendon", contents[11].getLast());
		assertEquals("Dick", contents[12].getLast());
		assertEquals("Greco", contents[13].getLast());
		assertEquals("Bauman", contents[14].getLast());
		assertEquals("Falcon", contents[15].getLast());
	}

	/**
	 * Tests to see if unityId names are valid
	 */
	@Test
	public void testReadFile3() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("michaea", contents[0].getUnityID());
		assertEquals("marsha", contents[1].getUnityID());
		assertEquals("mottl", contents[2].getUnityID());
		assertEquals("peasei", contents[3].getUnityID());
		assertEquals("seibere", contents[4].getUnityID());
		assertEquals("mathesl", contents[5].getUnityID());
		assertEquals("terrela", contents[6].getUnityID());
		assertEquals("runyont", contents[7].getUnityID());
		assertEquals("woodbul", contents[8].getUnityID());
		assertEquals("carrior", contents[9].getUnityID());
		assertEquals("worthn", contents[10].getUnityID());
		assertEquals("mclendc", contents[11].getUnityID());
		assertEquals("dicks", contents[12].getUnityID());
		assertEquals("grecoc", contents[13].getUnityID());
		assertEquals("baumant", contents[14].getUnityID());
		assertEquals("falcond", contents[15].getUnityID());
	}

	/**
	 * Tests to see if student id's are valid
	 */
	@Test
	public void testReadFile4() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals(1, contents[0].getId());
		assertEquals(3, contents[1].getId());
		assertEquals(4, contents[2].getId());
		assertEquals(5, contents[3].getId());
		assertEquals(8, contents[4].getId());
		assertEquals(9, contents[5].getId());
		assertEquals(10, contents[6].getId());
		assertEquals(14, contents[7].getId());
		assertEquals(17, contents[8].getId());
		assertEquals(19, contents[9].getId());
		assertEquals(23, contents[10].getId());
		assertEquals(24, contents[11].getId());
		assertEquals(26, contents[12].getId());
		assertEquals(28, contents[13].getId());
		assertEquals(30, contents[14].getId());
		assertEquals(31, contents[15].getId());
	}

	/**
	 * Tests to see if student gpa's are valid
	 */
	@Test
	public void testReadFile5() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals(1.10, contents[0].getGpa(), 0);
		assertEquals(2.25, contents[1].getGpa(), 0);
		assertEquals(2.94, contents[2].getGpa(), 0);
		assertEquals(2.72, contents[3].getGpa(), 0);
		assertEquals(0.60, contents[4].getGpa(), 0);
		assertEquals(0.40, contents[5].getGpa(), 0);
		assertEquals(3.49, contents[6].getGpa(), 0);
		assertEquals(0.62, contents[7].getGpa(), 0);
		assertEquals(1.57, contents[8].getGpa(), 0);
		assertEquals(0.90, contents[9].getGpa(), 0);
		assertEquals(3.63, contents[10].getGpa(), 0);
		assertEquals(3.34, contents[11].getGpa(), 0);
		assertEquals(0.56, contents[12].getGpa(), 0);
		assertEquals(3.11, contents[13].getGpa(), 0);
		assertEquals(1.23, contents[14].getGpa(), 0);
		assertEquals(2.95, contents[15].getGpa(), 0);
	}

	/**
	 * Tests to see if student credit hours are valid
	 */
	@Test
	public void testReadFile6() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals(10, contents[0].getCreditHours());
		assertEquals(11, contents[1].getCreditHours());
		assertEquals(18, contents[2].getCreditHours());
		assertEquals(9, contents[3].getCreditHours());
		assertEquals(13, contents[4].getCreditHours());
		assertEquals(9, contents[5].getCreditHours());
		assertEquals(10, contents[6].getCreditHours());
		assertEquals(10, contents[7].getCreditHours());
		assertEquals(13, contents[8].getCreditHours());
		assertEquals(13, contents[9].getCreditHours());
		assertEquals(11, contents[10].getCreditHours());
		assertEquals(14, contents[11].getCreditHours());
		assertEquals(17, contents[12].getCreditHours());
		assertEquals(11, contents[13].getCreditHours());
		assertEquals(16, contents[14].getCreditHours());
		assertEquals(16, contents[15].getCreditHours());
	}

}
