package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * test adaptable heap pq
 * 
 * @author sanjana cheerla
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/**
	 * heap to test
	 */
	private HeapAdaptablePriorityQueue<Integer, String> heap;

	/**
	 * setup
	 */
	@Before
	public void setUp() {
		heap = new HeapAdaptablePriorityQueue<Integer, String>();
	}

	/**
	 * test replace key
	 */
	@SuppressWarnings("unused")
	@Test
	public void testReplaceKey() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.replaceKey(e8, -5);
		assertEquals(-5, (int) heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(9, heap.size());

	}

	/**
	 * test replace value
	 */
	@SuppressWarnings("unused")
	@Test
	public void testReplaceValue() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.replaceValue(e8, "EIGHT");
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("EIGHT", e8.getValue());

	}

	/**
	 * test remove
	 */
	@SuppressWarnings("unused")
	@Test
	public void testRemove() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.remove(e0);
		assertEquals(1, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(8, heap.size());

	}

	/**
	 * test student heap
	 */
	@Test
	public void testStudentHeap() {
		AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(
				new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");

		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());

		sHeap.insert(s1, "one");
		sHeap.insert(s2, "one");
		sHeap.insert(s3, "one");
		sHeap.insert(s4, "one");
		sHeap.insert(s5, "one");
		assertEquals(5, sHeap.size());
		sHeap.deleteMin();
		assertEquals(4, sHeap.size());
	}
}