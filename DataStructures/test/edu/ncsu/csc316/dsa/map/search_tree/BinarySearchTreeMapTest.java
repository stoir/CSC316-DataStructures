package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * binary search tree test
 * 
 * @author Sanjana Cheerla
 */
public class BinarySearchTreeMapTest {

	/**
	 * tree for testing
	 */
	BinarySearchTreeMap<Integer, String> tree;

	/**
	 * set up tree
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}

	/**
	 * test put
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int) tree.root().getElement().getKey());
		tree.put(1, "ONE");
		assertFalse(tree.isEmpty());
		assertEquals(1, tree.size());
	}

	/**
	 * test get
	 */
	@Test
	public void testGet() {
		tree.put(1, "1");
		assertEquals(1, tree.size());
		tree.put(2, "2");
		tree.put(3, "3");
		tree.put(4, "4");
		tree.put(5, "5");
		tree.put(6, "6");
		tree.put(7, "7");
		tree.put(8, "8");
		tree.put(9, "9");
		tree.put(10, "10");
		assertEquals(10, tree.size());

		assertEquals("1", tree.get(1));
		assertEquals("2", tree.get(2));
		assertEquals("3", tree.get(3));
		assertEquals("4", tree.get(4));
		assertEquals("5", tree.get(5));
		assertEquals("6", tree.get(6));
		assertEquals("7", tree.get(7));
		assertEquals("8", tree.get(8));
		assertEquals("9", tree.get(9));
		assertEquals("10", tree.get(10));
		assertNull("1", tree.get(100));
	}

	/**
	 * test remove
	 */
	@Test
	public void testRemove() {
		tree.put(1, "one");
		assertEquals(1, tree.size());

		assertNull(tree.remove(10));
		assertEquals(1, tree.size());

		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());

		tree.put(5, "5");
		tree.put(1, "1");
		tree.put(9, "9");
		tree.put(3, "3");
		tree.put(2, "2");
		tree.put(4, "4");
		tree.put(6, "6");
		tree.put(10, "10");
		tree.put(7, "7");
		tree.put(8, "8");

		// var set = tree.entrySet();

		tree.inOrder();
		tree.postOrder();
		tree.preOrder();
		tree.levelOrder();

		assertEquals(10, tree.size());
		assertEquals("5", tree.root().getElement().getValue());

		tree.remove(5);
		assertEquals("6", tree.root().getElement().getValue());

		tree.remove(2);
		assertEquals(8, tree.size());
		tree.remove(3);
		tree.remove(9);
		tree.remove(4);
		tree.remove(8);
		tree.remove(5);
		tree.remove(7);
		tree.remove(6);
		tree.remove(1);
		tree.remove(10);
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "1");
		tree.toString();
	}

	/**
	 * test entry set
	 */
	@Test
	public void testEntrySet() {
		tree.put(5, "5");
		tree.put(1, "1");
		tree.put(9, "9");
		tree.put(3, "3");
		tree.put(2, "2");
		tree.put(4, "4");
		tree.put(6, "6");
		tree.put(10, "10");
		tree.put(7, "7");
		tree.put(8, "8");

		Iterable<Position<Entry<Integer, String>>> in = tree.inOrder();
		for (Position<Entry<Integer, String>> p : in) {
			tree.numChildren(p);
			tree.children(p);
		}
		Iterable<Entry<Integer, String>> set = tree.entrySet();
		assertNotNull(set);
	}
}