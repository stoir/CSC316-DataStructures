package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
//import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test SkipListMap
 * 
 * @author sanjana cheerla
 *
 */
public class SkipListMapTest {

	/**
	 * map
	 */
	private Map<Integer, String> map;
	/**
	 * student map
	 */
	private Map<Student, Integer> studentMap;

	/**
	 * test setup
	 */
	@Before
	public void setUp() {
		map = new SkipListMap<Integer, String>();
		studentMap = new SkipListMap<Student, Integer>();
	}

	/**
	 * test put
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SkipListMap[3]", map.toString());
		assertEquals(1, map.size());

		assertNull(map.put(2, "string2"));
		assertEquals("SkipListMap[2, 3]", map.toString());
		assertEquals(2, map.size());

		assertNull(map.put(1, "string1"));
		assertEquals("SkipListMap[1, 2, 3]", map.toString());
		assertEquals(3, map.size());

		map.put(1, "string0");
		assertEquals("SkipListMap[1, 2, 3]", map.toString());
		assertEquals(3, map.size());
	}

	/**
	 * test get
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string2", map.get(2));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string3", map.get(3));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string4", map.get(4));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string5", map.get(5));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
	}

	/**
	 * test remove
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		map.remove(1);
		assertEquals(4, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[2, 3, 4, 5]", map.toString());

		map.remove(2);
		assertEquals(3, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[3, 4, 5]", map.toString());

		map.remove(3);
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[4, 5]", map.toString());

		map.remove(4);
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[5]", map.toString());

		map.remove(5);
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertEquals("SkipListMap[]", map.toString());

	}

	/**
	 * test student map
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");

		studentMap.put(s1, 1);
		assertEquals(1, studentMap.size());

		studentMap.put(s2, 2);
		assertEquals(2, studentMap.size());

		studentMap.put(s3, 3);
		assertEquals(3, studentMap.size());

		studentMap.put(s4, 4);
		assertEquals(4, studentMap.size());

		studentMap.put(s5, 5);
		assertEquals(5, studentMap.size());

		studentMap.put(s5, 6);
		assertEquals(5, studentMap.size());

		assertTrue(studentMap.get(s1) == 1);
		assertTrue(studentMap.get(s2) == 2);
		assertTrue(studentMap.get(s3) == 3);
		assertTrue(studentMap.get(s4) == 4);
		assertTrue(studentMap.get(s5) == 6);
	}

	/**
	 * test iterator
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());

		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertFalse(it.hasNext());
	}

	/**
	 * test entry set
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int) (entry.getKey()));
		assertEquals("string1", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(2, (int) (entry.getKey()));
		assertEquals("string2", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(3, (int) (entry.getKey()));
		assertEquals("string3", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(4, (int) (entry.getKey()));
		assertEquals("string4", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(5, (int) (entry.getKey()));
		assertEquals("string5", (String) (entry.getValue()));
	}

	/**
	 * test values
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());

		assertEquals(it.next(), "string1");
		assertEquals(it.next(), "string2");
		assertEquals(it.next(), "string3");
		assertEquals(it.next(), "string4");
		assertEquals(it.next(), "string5");

	}
}