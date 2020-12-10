package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the array based queue
 * 
 * @author sanjana cheerla
 *
 */
public class ArrayBasedQueueTest {

	/**
	 * queue used for testing
	 */
	private Queue<String> queue;

	/**
	 * setting up for testing
	 */
	@Before
	public void setUp() {
		queue = new ArrayBasedQueue<String>();
	}

	/**
	 * test enqueue
	 */
	@Test
	public void testEnqueue() {
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());

		queue.enqueue("one");
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());
	}

	/**
	 * test dequeue
	 */
	@Test
	public void testDequeue() {
		assertEquals(0, queue.size());
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");
		queue.enqueue("four");
		queue.enqueue("five");
		queue.enqueue("six");
		assertEquals(6, queue.size());

		assertEquals("one", queue.dequeue());
		assertEquals("two", queue.front());
		assertEquals(5, queue.size());

		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();

		try {
			queue.dequeue();
			fail("NoSuchElementException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}

	/**
	 * test front
	 */
	@Test
	public void testFront() {
		assertEquals(0, queue.size());
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");
		queue.enqueue("four");
		queue.enqueue("five");
		queue.enqueue("six");
		assertEquals(6, queue.size());

		assertEquals("one", queue.dequeue());
		assertEquals("two", queue.front());
		assertEquals(5, queue.size());
	}

}