package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests linked binary tree
 * 
 * @author sanjana cheerla
 */
public class LinkedBinaryTreeTest {

	/**
	 * tree
	 */
	private LinkedBinaryTree<String> tree;
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
	 * setup
	 */
	@Before
	public void setUp() {
		tree = new LinkedBinaryTree<String>();
	}

	/**
	 * Sample tree to help with testing
	 *
	 * One -> Two -> Six -> Ten -> Seven -> Five -> Three -> Four -> Eight -> Nine
	 * 
	 * Or, visually: one / \ two three / \ / six ten four / \ / \ seven five eight
	 * nine
	 */
	private void createTree() {
		one = tree.addRoot("one");
		two = tree.addLeft(one, "two");
		three = tree.addRight(one, "three");
		six = tree.addLeft(two, "six");
		ten = tree.addRight(two, "ten");
		four = tree.addLeft(three, "four");
		seven = tree.addLeft(ten, "seven");
		five = tree.addRight(ten, "five");
		eight = tree.addLeft(four, "eight");
		nine = tree.addRight(four, "nine");
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
		assertEquals(2, tree.numChildren(two));
		assertEquals(0, tree.numChildren(seven));
	}

	/**
	 * test parent
	 */
	@Test
	public void testParent() {
		createTree();
		assertEquals(two, tree.parent(six));
		assertEquals(two, tree.parent(ten));
		assertEquals(ten, tree.parent(seven));
		assertEquals(one, tree.parent(two));
		assertEquals(one, tree.parent(three));
		assertEquals(ten, tree.parent(five));
	}

	/**
	 * test iterator
	 */
	@Test
	public void testIterator() {
		createTree();
		Iterator<String> pre = tree.iterator();
		assertEquals("six", pre.next());
		assertEquals("two", pre.next());
		assertEquals("seven", pre.next());
		assertEquals("ten", pre.next());
		assertEquals("five", pre.next());
		assertEquals("one", pre.next());
		assertEquals("eight", pre.next());
		assertEquals("four", pre.next());
		assertEquals("nine", pre.next());
	}

	/**
	 * test sibling
	 */
	@Test
	public void testSibling() {
		createTree();
		assertEquals(two, tree.sibling(three));
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
	 * test pre order
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> pre = tree.preOrder().iterator();
		assertEquals(one, pre.next());
		assertEquals(two, pre.next());
		assertEquals(six, pre.next());
		assertEquals(ten, pre.next());
		assertEquals(seven, pre.next());
		assertEquals(five, pre.next());
		assertEquals(three, pre.next());
		assertEquals(four, pre.next());
		assertEquals(eight, pre.next());
		assertEquals(nine, pre.next());
	}

	/**
	 * test post order
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> post = tree.postOrder().iterator();
		assertEquals(six, post.next());
		assertEquals(seven, post.next());
		assertEquals(five, post.next());
		assertEquals(ten, post.next());
		assertEquals(two, post.next());
		assertEquals(eight, post.next());
		assertEquals(nine, post.next());
		assertEquals(four, post.next());
		assertEquals(three, post.next());
		assertEquals(one, post.next());
	}

	/**
	 * test in order
	 */
	@Test
	public void testInOrder() {
		createTree();
		Iterator<Position<String>> in = tree.inOrder().iterator();
		assertEquals(six, in.next());
		assertEquals(two, in.next());
		assertEquals(seven, in.next());
		assertEquals(ten, in.next());
		assertEquals(five, in.next());
		assertEquals(one, in.next());
		assertEquals(eight, in.next());
		assertEquals(four, in.next());
		assertEquals(nine, in.next());
		assertEquals(three, in.next());
	}

	/**
	 * test emptyy tree
	 */
	@Test
	public void testEmptyTree() {
		Tree<String> bTree = new GeneralTree<String>();
		assertTrue(bTree.isEmpty());
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
		assertEquals(ten, level.next());
		assertEquals(four, level.next());
		assertEquals(seven, level.next());
		assertEquals(five, level.next());
		assertEquals(eight, level.next());
		assertEquals(nine, level.next());
	}

	/**
	 * test add children
	 */
	@Test
	public void testAddChildren() {
		assertTrue(tree.isEmpty());
		one = tree.addRoot("one");
		assertEquals(1, tree.size());
		assertNull(tree.parent(one));
		assertEquals("LinkedBinaryTree[\none\n]", tree.toString());

		tree.addLeft(one, "two");
		assertEquals(2, tree.size());
		three = tree.addRight(one, "three");
		assertEquals(3, tree.size());

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
		assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n three\n  four\n   eight\n]",
				tree.toString());
		assertEquals(9, tree.size());
		assertEquals(1, tree.numChildren(four));

		tree.remove(eight);
		assertEquals(8, tree.size());
		tree.remove(five);
		assertEquals(7, tree.size());
	}

}
