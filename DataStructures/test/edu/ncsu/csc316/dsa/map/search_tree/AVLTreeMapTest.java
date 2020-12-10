package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test the AVL tree map
 * 
 * @author sanjana cheerla
 *
 */
public class AVLTreeMapTest {

	/**
	 * Student Test Object
	 */
	private Student sOne;
	/**
	 * Student Test Object
	 */
	private Student sTwo;
	/**
	 * Student Test Object
	 */
	private Student sThree;
	/**
	 * Student Test Object
	 */
	private Student sFour;
	/**
	 * Student Test Object
	 */
	private Student sFive;

	/**
	 * String tree with integer keys
	 */
	private BinarySearchTreeMap<Integer, String> tree;
	/**
	 * student tree with integer keys
	 */
	private BinarySearchTreeMap<Integer, Student> treeStudent;

	/**
	 * set up before method
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		tree = new AVLTreeMap<Integer, String>();
		treeStudent = new AVLTreeMap<Integer, Student>(Comparator.naturalOrder());
	}

	/**
	 * test putting into a tree
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		assertNull(tree.put(5, "string5"));
		assertEquals(1, tree.size());
		assertEquals(5, (int) tree.root().getElement().getKey());
		assertNull(tree.left(tree.root()).getElement());

		assertNull(tree.put(10, "string10"));
		assertEquals(5, (int) tree.root().getElement().getKey());
		assertNull(tree.left(tree.root()).getElement());
		assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
		assertNull(tree.left(tree.right(tree.root())).getElement());
		assertNull(tree.right(tree.right(tree.root())).getElement());
		assertEquals(2, tree.size());

		assertNull(tree.put(15, "string15"));
		assertEquals(10, (int) tree.root().getElement().getKey());
		assertEquals(15, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, tree.size());

		assertNull(tree.put(20, "string20"));
		assertNull(tree.put(17, "string17"));
		assertNull(tree.put(3, "string3"));
		assertNull(tree.put(1, "string1"));
		assertNull(tree.put(2, "string2"));
		assertNull(tree.put(4, "string4"));
		assertNull(tree.put(6, "string6"));
		assertNull(tree.put(8, "string8"));
		assertEquals(11, tree.size());

		assertNull(tree.put(7, "string7"));
		assertNull(tree.put(9, "string9"));
		assertNull(tree.put(13, "string13"));
		assertNull(tree.put(19, "string19"));

		treeStudent.put(1, sOne);
		treeStudent.put(2, sTwo);
		treeStudent.put(4, sFour);
		treeStudent.put(3, sThree);
		treeStudent.put(5, sFive);
		assertEquals(5, treeStudent.size());

	}

	/**
	 * test get
	 */
	@Test
	public void testGet() {
		assertTrue(tree.isEmpty());
		assertNull(tree.put(3, "string3"));
		assertFalse(tree.isEmpty());

		assertEquals("string3", tree.get(3));
		assertEquals(null, tree.get(6));
		assertEquals(null, tree.get(0));
		assertEquals("string3", tree.get(3));

		tree.remove(3);
		assertTrue(tree.isEmpty());
		assertEquals(null, tree.get(3));

	}

	/**
	 * test remove
	 */
	@Test
	public void testRemove() {
		assertTrue(tree.isEmpty());
		assertNull(tree.put(1, "one"));
		assertNull(tree.put(2, "two"));
		assertNull(tree.put(3, "three"));
		assertNull(tree.put(4, "four"));
		assertNull(tree.put(5, "five"));
		assertNull(tree.put(6, "six"));
		assertNull(tree.put(7, "seven"));
		assertEquals(7, tree.size());
		assertFalse(tree.isEmpty());

		assertNull(tree.remove(0));
		assertEquals(7, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(5, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		assertNotNull(tree.remove(1));
		assertNotNull(tree.remove(2));
		assertNotNull(tree.remove(3));
		assertNotNull(tree.remove(4));
		assertNotNull(tree.remove(5));
		assertNotNull(tree.remove(6));
		assertNotNull(tree.remove(7));

		assertTrue(tree.isEmpty());
		assertNull(tree.put(4, "four"));
		assertNull(tree.put(3, "three"));
		assertNull(tree.put(2, "two"));
	}

}
