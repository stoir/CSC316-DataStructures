package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test UnorderedArraydMap
 * 
 * @author sanjana cheerla
 *
 */
public class UnorderedArrayMapTest {

	/**
	 * map
	 */
	private Map<Integer, String> map;

	/**
	 * setup
	 */
	@Before
	public void setUp() {
		map = new UnorderedArrayMap<Integer, String>();
	}

	/**
	 * test put
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedArrayMap[3]", map.toString());
		assertEquals(1, map.size());

		assertNull(map.put(2, "string2"));
		assertEquals("UnorderedArrayMap[2, 3]", map.toString());
		assertEquals(2, map.size());

		assertNull(map.put(1, "string1"));
		assertEquals("UnorderedArrayMap[1, 2, 3]", map.toString());
		assertEquals(3, map.size());

		map.put(1, "string0");
		assertEquals("UnorderedArrayMap[1, 2, 3]", map.toString());
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
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string2", map.get(2));
		assertEquals("UnorderedArrayMap[1, 2, 4, 5, 3]", map.toString());

		assertEquals("string3", map.get(3));
		assertEquals("UnorderedArrayMap[1, 2, 4, 3, 5]", map.toString());

		assertEquals("string4", map.get(4));
		assertEquals("UnorderedArrayMap[1, 4, 2, 3, 5]", map.toString());

		assertEquals("string5", map.get(5));
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
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
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());

		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string1", map.remove(1));
		assertEquals(4, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[4, 2, 5, 3]", map.toString());

	}

	/**
	 * test iterator
	 */
	@Test
	public void testIterator() {
		Iterator<Integer> it = map.iterator();
		assertFalse(it.hasNext());

		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

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
		assertEquals(4, (int) (entry.getKey()));
		assertEquals("string4", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(2, (int) (entry.getKey()));
		assertEquals("string2", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(5, (int) (entry.getKey()));
		assertEquals("string5", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(3, (int) (entry.getKey()));
		assertEquals("string3", (String) (entry.getValue()));
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

		assertEquals(it.next(), "string1");
		assertEquals(it.next(), "string4");
		assertEquals(it.next(), "string2");
		assertEquals(it.next(), "string5");
		assertEquals(it.next(), "string3");

	}
}