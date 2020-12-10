package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests ShortestPathUtil class
 * 
 * @author Sanjana Cheerla
 *
 */
public class ShortestPathUtilTest {

	/**
	 * undirected graph for testing
	 */
	private Graph<String, Highway> undirectedGraph;

	/**
	 * directed graph
	 */
	private Graph<String, Highway> directedGraph;

	/**
	 * highway for testing
	 */
	Highway h1;
	/**
	 * highway for testing
	 */
	Highway h2;
	/**
	 * highway for testing
	 */
	Highway h3;
	/**
	 * highway for testing
	 */
	Highway h4;
	/**
	 * highway for testing
	 */
	Highway h5;
	/**
	 * highway for testing
	 */
	Highway h6;
	/**
	 * highway for testing
	 */
	Highway h7;
	/**
	 * highway for testing
	 */
	Highway h8;
	/**
	 * highway for testing
	 */
	Highway h9;
	/**
	 * highway for testing
	 */
	Highway h10;

	/**
	 * vertex used in testing
	 */
	Vertex<String> v1;
	/**
	 * vertex used in testing
	 */
	Vertex<String> v2;
	/**
	 * vertex used in testing
	 */
	Vertex<String> v3;
	/**
	 * vertex used in testing
	 */
	Vertex<String> v4;
	/**
	 * vertex used in testing
	 */
	Vertex<String> v5;

	/**
	 * Sets up graphs for testing
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<>();
		directedGraph = new AdjacencyListGraph<>(true);

		h1 = new Highway("one", 5);
		h2 = new Highway("two", 10);
		h3 = new Highway("three", 15);
		h4 = new Highway("four", 20);
		h5 = new Highway("five", 25);
		h6 = new Highway("six", 30);
		h7 = new Highway("seven", 35);
		h8 = new Highway("eight", 40);
		h9 = new Highway("nine", 45);
		h10 = new Highway("ten", 50);

		v1 = undirectedGraph.insertVertex("Raleigh");
		v2 = undirectedGraph.insertVertex("Asheville");
		v3 = undirectedGraph.insertVertex("Wilmington");
		v4 = undirectedGraph.insertVertex("Durham");
		v5 = undirectedGraph.insertVertex("Greenville");

		undirectedGraph.insertEdge(v1, v2, h1);
		undirectedGraph.insertEdge(v1, v3, h2);
		undirectedGraph.insertEdge(v1, v4, h3);
		undirectedGraph.insertEdge(v1, v5, h4);
		undirectedGraph.insertEdge(v2, v3, h5);
		undirectedGraph.insertEdge(v2, v4, h6);
		undirectedGraph.insertEdge(v2, v5, h7);
		undirectedGraph.insertEdge(v3, v4, h8);
		undirectedGraph.insertEdge(v3, v5, h9);
		undirectedGraph.insertEdge(v4, v5, h10);
	}

	/**
	 * Tests dijkstra()
	 */
	@Test
	public void testDijkstra() {

		Map<Vertex<String>, Integer> um = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		assertFalse(um.isEmpty());
		assertEquals(um.size(), 5);
		assertEquals(um.get(v1), (Integer) 0);
		assertEquals(um.get(v2), (Integer) 5);
		assertEquals(um.get(v3), (Integer) 10);
		assertEquals(um.get(v4), (Integer) 15);
		assertEquals(um.get(v5), (Integer) 20);

		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");

		directedGraph.insertEdge(v1, v2, h1);
		directedGraph.insertEdge(v1, v3, h2);
		directedGraph.insertEdge(v1, v4, h3);
		directedGraph.insertEdge(v1, v5, h4);
		directedGraph.insertEdge(v2, v3, h5);
		directedGraph.insertEdge(v2, v4, h6);
		directedGraph.insertEdge(v2, v5, h7);
		directedGraph.insertEdge(v3, v4, h8);
		directedGraph.insertEdge(v3, v5, h9);
		directedGraph.insertEdge(v4, v5, h10);

		Map<Vertex<String>, Integer> dm = ShortestPathUtil.dijkstra(directedGraph, v1);
		assertFalse(dm.isEmpty());
		assertEquals(dm.size(), 5);
		assertEquals(dm.get(v1), (Integer) 0);
		assertEquals(dm.get(v2), (Integer) 5);
		assertEquals(dm.get(v3), (Integer) 10);
		assertEquals(dm.get(v4), (Integer) 15);
		assertEquals(dm.get(v5), (Integer) 20);

	}

	/**
	 * Tests shortestPathTree()
	 */
	@Test
	public void testShortestPathTree() {

		Map<Vertex<String>, Integer> distances = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		Map<Vertex<String>, Edge<Highway>> um = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, distances);
		assertFalse(um.isEmpty());
		assertEquals(um.size(), 4);
		assertEquals(um.get(v2).getElement().getLength(), 5);
		assertEquals(um.get(v3).getElement().getLength(), 10);
		assertEquals(um.get(v4).getElement().getLength(), 15);
		assertEquals(um.get(v5).getElement().getLength(), 20);

		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");

		directedGraph.insertEdge(v1, v2, h1);
		directedGraph.insertEdge(v1, v3, h2);
		directedGraph.insertEdge(v1, v4, h3);
		directedGraph.insertEdge(v1, v5, h4);
		directedGraph.insertEdge(v2, v3, h5);
		directedGraph.insertEdge(v2, v4, h6);
		directedGraph.insertEdge(v2, v5, h7);
		directedGraph.insertEdge(v3, v4, h8);
		directedGraph.insertEdge(v3, v5, h9);
		directedGraph.insertEdge(v4, v5, h10);

		distances = ShortestPathUtil.dijkstra(directedGraph, v1);
		Map<Vertex<String>, Edge<Highway>> dm = ShortestPathUtil.shortestPathTree(directedGraph, v1, distances);
		assertFalse(dm.isEmpty());
		assertEquals(dm.size(), 4);
		assertEquals(dm.get(v2).getElement().getLength(), 5);
		assertEquals(dm.get(v3).getElement().getLength(), 10);
		assertEquals(dm.get(v4).getElement().getLength(), 15);
		assertEquals(dm.get(v5).getElement().getLength(), 20);

	}

	/**
	 * highway class to test dijkstra's algorithm
	 * 
	 * @author Sanjana Cheerla
	 *
	 */
	public class Highway implements Weighted {
		@SuppressWarnings("unused")

		/**
		 * name of highway
		 */
		private String name;

		/**
		 * length of highway
		 */
		private int length;

		/**
		 * Creates new highway object with given name and length
		 * 
		 * @param n name
		 * @param l length
		 */
		public Highway(String n, int l) {
			setName(n);
			setLength(l);
		}

		/**
		 * Sets name of highway
		 * 
		 * @param name name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Gets length of highway
		 * 
		 * @return length of highway
		 */
		public int getLength() {
			return length;
		}

		/**
		 * Sets length of highway
		 * 
		 * @param length length to set
		 */
		public void setLength(int length) {
			this.length = length;
		}

		/**
		 * get weight of highway
		 * 
		 * @return weight
		 */
		@Override
		public int getWeight() {
			return getLength();
		}
	}

}