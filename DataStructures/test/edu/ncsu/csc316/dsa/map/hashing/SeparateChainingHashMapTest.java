package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * test separate chaining hash map
 * 
 * @author sanjanacheerla
 *
 */
public class SeparateChainingHashMapTest {

	/**
	 * map to test with
	 */
	private Map<Integer, String> map;

	/**
	 * map to test with
	 */
	@SuppressWarnings("unused")
	private Map<Integer, String> map1;

	/**
	 * map to test with
	 */
	@SuppressWarnings("unused")
	private Map<Integer, String> map2;

	/**
	 * map to test with
	 */
	@SuppressWarnings("unused")
	private Map<Integer, String> map3;

	/**
	 * set up
	 */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are testing.
		// Remember that (when testing) alpha = 1, beta = 1, and prime = 7
		// based on our AbstractHashMap constructor.
		// That means you can draw the hash table by hand
		// if you use integer keys, since Integer.hashCode() = the integer value, itself
		// Finally, apply compression. For example:
		// for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
		// for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
		// for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
		// for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
		// for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
		// for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
		// etc.
		// Remember that our secondary map (an AVL tree) is a search
		// tree, which means the entries should be sorted in order within
		// that tree
		map = new SeparateChainingHashMap<Integer, String>(7, true);
		map1 = new SeparateChainingHashMap<Integer, String>();
		map2 = new SeparateChainingHashMap<Integer, String>(true);
		map3 = new SeparateChainingHashMap<Integer, String>(7);
	}

	/**
	 * test put
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));

		// Since our entrySet method returns the entries in the table
		// from left to right, we can use the entrySet to check
		// that our values are in the correct order in the hash table.
		// Alternatively, you could implement a toString() method if you
		// want to check that the exact index/map of each bucket is correct
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4

		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4
		assertEquals(4, (int) it.next().getKey()); // should be in a map in index 5
		assertEquals("string3", map.put(3, "STRING3"));
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
		assertEquals("string1", map.get(1));
		assertEquals("string2", map.get(2));
		assertEquals("string3", map.get(3));
		assertEquals("string4", map.get(4));
		assertEquals("string5", map.get(5));
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
		assertEquals("string1", map.remove(1));
		assertEquals("string2", map.remove(2));
		assertEquals("string3", map.remove(3));
		assertEquals("string4", map.remove(4));
		assertEquals("string5", map.remove(5));
		assertTrue(map.isEmpty());
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
		it.next();
		it.next();
		it.next();
		it.next();
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
		// Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int) it.next().getKey());
		assertEquals(2, (int) it.next().getKey());
		assertEquals(3, (int) it.next().getKey());
		assertEquals(4, (int) it.next().getKey());
		assertEquals(5, (int) it.next().getKey());
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
		assertEquals("string1", it.next());
		assertEquals("string2", it.next());
		assertEquals("string3", it.next());
		assertEquals("string4", it.next());
		assertEquals("string5", it.next());
	}
}