package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
//import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * test positional linked list
 * 
 * @author sanjana cheerla
 *
 */
public class PositionalLinkedListTest {

	/**
	 * list
	 */
	private PositionalList<String> list;

	/**
	 * before
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}

	/**
	 * test first
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
	}

	/**
	 * test last
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.last());

		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());

		Position<String> last = list.addLast("one");
		assertEquals(2, list.size());
		assertEquals(last, list.last());
	}

	/**
	 * test add first
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		list.addFirst("one");
		assertEquals(1, list.size());
	}

	/**
	 * test add last
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		list.addLast("one");
		assertEquals(1, list.size());
		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("one", list.first().getElement());
	}

	/**
	 * test before
	 */
	@Test
	public void testBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		Position<String> first = list.addFirst("one");
		list.addBefore(first, "two");

		assertEquals("two", list.before(first).getElement());
	}

	/**
	 * test after
	 */
	@Test
	public void testAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		Position<String> first = list.addFirst("one");
		list.addAfter(first, "two");

		assertEquals("two", list.after(first).getElement());
	}

	/**
	 * test add before
	 */
	@Test
	public void testAddBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		Position<String> first = list.addFirst("one");
		list.addBefore(first, "two");

		assertEquals("two", list.first().getElement());
	}

	/**
	 * test add after
	 */
	@Test
	public void testAddAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		Position<String> first = list.addFirst("one");
		list.addAfter(first, "two");

		assertEquals("two", list.last().getElement());
	}

	/**
	 * test set
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		Position<String> first = list.addFirst("one");
		list.addAfter(first, "two");

		assertEquals("two", list.last().getElement());

		list.set(first, "three");
		assertEquals("three", list.first().getElement());
	}

	/**
	 * test remove
	 */
	@Test
	public void testRemove() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		Position<String> first = list.addFirst("one");
		list.addAfter(first, "two");

		assertEquals("two", list.last().getElement());

		list.remove(first);
		assertEquals(1, list.size());
	}

	/**
	 * test iterator
	 */
	@Test
	public void testIterator() {
		assertEquals(0, list.size());
		list.addFirst("one");
		list.addFirst("two");
		list.addFirst("three");
		assertEquals(3, list.size());

		Iterator<String> iterator = list.iterator();
		assertEquals(iterator.hasNext(), true);
		assertEquals("three", iterator.next());
		assertEquals("two", iterator.next());
		assertEquals("one", iterator.next());
		iterator.remove();
	}

	/**
	 * test positions
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());

		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
	}

}