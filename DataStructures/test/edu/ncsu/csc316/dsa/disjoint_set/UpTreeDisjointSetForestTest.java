package edu.ncsu.csc316.dsa.disjoint_set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * up tree disjoint set forest test
 * 
 * @author sanjana cheerla
 */
public class UpTreeDisjointSetForestTest {

	/**
	 * set
	 */
	private DisjointSetForest<String> set;

	/**
	 * setup
	 */
	@Before
	public void setUp() {
		set = new UpTreeDisjointSetForest<>();
	}

	/**
	 * test make set
	 */
	@Test
	public void testMakeSet() {
		Position<String> one = set.makeSet("one");
		assertEquals("one", one.getElement());
		Position<String> two = set.makeSet("two");
		assertEquals("two", two.getElement());
	}

	/**
	 * test union find
	 */
	@SuppressWarnings("unused")
	@Test
	public void testUnionFind() {
		Position<String> one = set.makeSet("one");
		Position<String> two = set.makeSet("two");
		Position<String> three = set.makeSet("three");
		Position<String> four = set.makeSet("four");
		Position<String> five = set.makeSet("five");
		Position<String> six = set.makeSet("six");
		Position<String> seven = set.makeSet("seven");
		Position<String> eight = set.makeSet("eight");
		Position<String> nine = set.makeSet("nine");
		Position<String> ten = set.makeSet("ten");

		assertEquals(one, set.find("one"));
		set.union(two, three);
		set.union(nine, six);
	}
}