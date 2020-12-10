package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test general tree class
 * 
 * @author sanjana cheerla
 */
public class GeneralTreeTest {

	/**
	 * tree
	 */
	private GeneralTree<String> tree;
	/**
	 * empty tree
	 */
	private GeneralTree<String> emptyTree;

	/**
	 * pos 1
	 */
	private Position<String> one;
	/**
	 * pos 2
	 */
	private Position<String> two;
	/**
	 * pos 3
	 */
	private Position<String> three;
	/**
	 * pos 4
	 */
	private Position<String> four;
	/**
	 * pos 5
	 */
	private Position<String> five;
	/**
	 * pos 6
	 */
	private Position<String> six;
	/**
	 * pos 7
	 */
	private Position<String> seven;
	/**
	 * pos 8
	 */
	private Position<String> eight;
	/**
	 * pos 9
	 */
	private Position<String> nine;
	/**
	 * pos 10
	 */
	private Position<String> ten;

	/**
	 * An invalid Position to help test validate(p)
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> general param
	 */
	private class InvalidPosition<E> implements Position<E> {

		@Override
		public E getElement() {
			return null;
		}

	}

	/**
	 * set up
	 */
	@Before
	public void setUp() {
		tree = new GeneralTree<String>();
		emptyTree = new GeneralTree<String>();
	}

	/**
	 * Helper method to construct a sample tree
	 *
	 * One -> Two -> Six -> Five -> Ten -> Seven -> Three -> Four -> Eight -> Nine
	 *
	 * Or, visually: one / \ two three / | \ | six five ten four | / \ seven eight
	 * nine
	 */
	private void createTree() {
		one = tree.addRoot("one");
		two = tree.addChild(one, "two");
		three = tree.addChild(one, "three");
		six = tree.addChild(two, "six");
		five = tree.addChild(two, "five");
		ten = tree.addChild(two, "ten");
		seven = tree.addChild(ten, "seven");
		four = tree.addChild(three, "four");
		eight = tree.addChild(four, "eight");
		nine = tree.addChild(four, "nine");
	}

	/**
	 * test set
	 */
	@Test
	public void testSet() {
		createTree();
		assertEquals("one", tree.set(one, "ONE"));
		var tmp = new InvalidPosition<String>();
		assertNull(tmp.getElement());
		try {
			var pos = new InvalidPosition<String>();
			tree.set(pos, "invalid");
			assertNull(pos.getElement());
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		assertEquals("two", tree.set(two, "TWO"));
		assertEquals("three", tree.set(three, "THREE"));
		assertEquals("four", tree.set(four, "FOUR"));
		assertEquals("five", tree.set(five, "FIVE"));
		assertEquals("six", tree.set(six, "SIX"));
		assertEquals("seven", tree.set(seven, "SEVEN"));
		assertEquals("eight", tree.set(eight, "EIGHT"));
		assertEquals("nine", tree.set(nine, "NINE"));
		assertEquals("ten", tree.set(ten, "TEN"));
	}

	/**
	 * test size
	 */
	@Test
	public void testSize() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		createTree();
		assertEquals(10, tree.size());
		assertFalse(tree.isEmpty());
	}

	/**
	 * test num children
	 */
	@Test
	public void testNumChildren() {
		createTree();
		assertEquals(2, tree.numChildren(one));
		assertEquals(3, tree.numChildren(two));
		assertEquals(0, tree.numChildren(seven));
	}

	/**
	 * test parent
	 */
	@Test
	public void testParent() {
		createTree();
		assertEquals(two, tree.parent(six));
		assertEquals(two, tree.parent(five));
		assertEquals(two, tree.parent(ten));
		assertEquals(ten, tree.parent(seven));
		assertEquals(one, tree.parent(two));
		assertEquals(one, tree.parent(three));
	}

	/**
	 * test is internal
	 */
	@Test
	public void testIsInternal() {
		createTree();
		assertTrue(tree.isInternal(one));
		assertTrue(tree.isInternal(two));
		assertFalse(tree.isInternal(six));
	}

	/**
	 * test is leaf
	 */
	@Test
	public void isLeaf() {
		createTree();
		assertFalse(tree.isLeaf(one));
		assertFalse(tree.isLeaf(three));
		assertFalse(tree.isLeaf(four));
		assertTrue(tree.isLeaf(eight));
	}

	/**
	 * test is root
	 */
	@Test
	public void isRoot() {
		createTree();
		assertTrue(tree.isRoot(one));
		assertFalse(tree.isRoot(two));
		assertFalse(tree.isRoot(three));
		assertFalse(tree.isRoot(four));
		assertFalse(tree.isRoot(five));
		assertFalse(tree.isRoot(six));
		assertFalse(tree.isRoot(seven));
		assertFalse(tree.isRoot(eight));
		assertFalse(tree.isRoot(nine));
		assertFalse(tree.isRoot(ten));
	}

	/**
	 * test is empty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(emptyTree.isEmpty());
		createTree();
		assertFalse(tree.isEmpty());
	}

	/**
	 * test pre order
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> pre = tree.preOrder().iterator();
		assertEquals(one, pre.next());
		assertEquals(two, pre.next());
		assertEquals(six, pre.next());
		assertEquals(five, pre.next());
		assertEquals(ten, pre.next());
		assertEquals(seven, pre.next());
		assertEquals(three, pre.next());
		assertEquals(four, pre.next());
		assertEquals(eight, pre.next());
		assertEquals(nine, pre.next());
	}

	/**
	 * test iterator
	 */
	@Test
	public void testIterator() {
		createTree();
		Iterator<String> pre = tree.iterator();
		assertEquals("one", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("two", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("six", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("five", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("ten", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("seven", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("three", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("four", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("eight", pre.next());
		assertTrue(pre.hasNext());
		assertEquals("nine", pre.next());

		try {
			pre.remove();
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	/**
	 * test post order
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> post = tree.postOrder().iterator();
		assertEquals(six, post.next());
		assertEquals(five, post.next());
		assertEquals(seven, post.next());
		assertEquals(ten, post.next());
		assertEquals(two, post.next());
		assertEquals(eight, post.next());
		assertEquals(nine, post.next());
		assertEquals(four, post.next());
		assertEquals(three, post.next());
		assertEquals(one, post.next());
	}

	/**
	 * test level order
	 */
	@Test
	public void testLevelOrder() {
		createTree();
		Iterator<Position<String>> level = tree.levelOrder().iterator();
		assertEquals(one, level.next());
		assertEquals(two, level.next());
		assertEquals(three, level.next());
		assertEquals(six, level.next());
		assertEquals(five, level.next());
		assertEquals(ten, level.next());
		assertEquals(four, level.next());
		assertEquals(seven, level.next());
		assertEquals(eight, level.next());
		assertEquals(nine, level.next());
	}

	/**
	 * test add child
	 */
	@Test
	public void testAddChild() {
		assertTrue(tree.isEmpty());
		one = tree.addRoot("one");
		assertEquals(1, tree.size());
		assertNull(tree.parent(one));
		assertEquals("GeneralTree[\none\n]", tree.toString());

		tree.addChild(one, "two");
		assertEquals(2, tree.size());
		three = tree.addChild(one, "three");
		assertEquals(3, tree.size());
		six = tree.addChild(one, "six");
		assertEquals(4, tree.size());
		five = tree.addChild(one, "five");
		assertEquals(5, tree.size());
	}

	/**
	 * test remove
	 */
	@Test
	public void testRemove() {
		createTree();
		assertEquals(10, tree.size());
		assertEquals(2, tree.numChildren(four));
		tree.remove(nine);
		assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]",
				tree.toString());
		assertEquals(9, tree.size());
		assertEquals(1, tree.numChildren(four));

		tree.remove(eight);
		assertEquals(8, tree.size());
		tree.remove(five);
		assertEquals(7, tree.size());
	}

	/**
	 * test empty tree
	 */
	@Test
	public void testEmptyTree() {
		Tree<String> bTree = new GeneralTree<String>();
		assertTrue(bTree.isEmpty());
	}

}