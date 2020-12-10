package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * test singly linked list
 * 
 * @author sanjana cheerla
 *
 */
public class SinglyLinkedListTest {

	/**
	 * the list
	 */
	private List<String> list;

	/**
	 * before
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<String>();
	}

	/**
	 * test add index
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));

		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));

		list.add(3, "four");
		assertEquals(4, list.size());
		assertEquals("four", list.get(3));

		list.add(4, "five");
		assertEquals(5, list.size());
		assertEquals("five", list.get(4));

		list.add(5, "six");
		assertEquals(6, list.size());
		assertEquals("six", list.get(5));

		list.add(6, "seven");
		assertEquals(7, list.size());
		assertEquals("seven", list.get(6));

		list.add(7, "eight");
		assertEquals(8, list.size());
		assertEquals("eight", list.get(7));

		list.add(8, "nine");
		assertEquals(9, list.size());
		assertEquals("nine", list.get(8));

		list.add(9, "ten");
		assertEquals(10, list.size());
		assertEquals("ten", list.get(9));

		list.add(10, "eleven");
		assertEquals(11, list.size());
		assertEquals("eleven", list.get(10));

		try {
			list.add(15, "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

	}

	/**
	 * test add last
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(list.size() - 1));

	}

	/**
	 * test last
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("two", list.last());
	}

	/**
	 * test add first
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
	}

	/**
	 * test first
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals("two", list.first());
	}

	/**
	 * test iterator
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();

		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// System.out.printf("%d", list.size());

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertTrue(it.hasNext());
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		it.remove();
		assertEquals(4, list.size());
	}

	/**
	 * test remove
	 */
	@Test
	public void testRemoveIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));

		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));

		list.add(3, "four");
		assertEquals(4, list.size());
		assertEquals("four", list.get(3));

		list.remove(1);
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));

	}

	/**
	 * test remove first
	 */
	@Test
	public void testRemoveFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));

		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));

		list.add(3, "four");
		assertEquals(4, list.size());
		assertEquals("four", list.get(3));

		list.removeFirst();
		assertEquals(3, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));

	}

	/**
	 * test remove last
	 */
	@Test
	public void testRemoveLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));

		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));

		list.add(3, "four");
		assertEquals(4, list.size());
		assertEquals("four", list.get(3));

		list.removeLast();
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));

	}

	/**
	 * test set
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));

		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));

		list.add(3, "four");
		assertEquals(4, list.size());
		assertEquals("four", list.get(3));

		list.set(1, "twoo");
		assertEquals(4, list.size());
		assertEquals("one", list.get(0));
		assertEquals("twoo", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
	}
}