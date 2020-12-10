package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the linked stack class
 * 
 * @author sanjana cheerla
 *
 */
public class LinkedStackTest {

	/**
	 * stack used for testing
	 */
	private Stack<String> stack;

	/**
	 * set up for testing
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}

	/**
	 * Test push
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());

		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());

		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());

		assertEquals("six", stack.pop());
		assertEquals(5, stack.size());
	}

	/**
	 * Test pop
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());

		assertEquals("six", stack.pop());
		assertEquals(5, stack.size());
		assertEquals("five", stack.top());
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		assertEquals(0, stack.size());
		try {
			stack.pop();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	/**
	 * test top
	 */
	@Test
	public void testTop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());

		assertEquals("six", stack.pop());
		assertEquals(5, stack.size());

		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		assertEquals(0, stack.size());
	}

}
