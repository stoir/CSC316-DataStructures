package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests GraphTraversalUtil class
 * 
 * @author Sanjana Cheerla
 *
 */
public class GraphTraversalUtilTest {

	/**
	 * undirected graph
	 */
	private Graph<String, Integer> undirectedGraph;
	/**
	 * directed graph
	 */
	private Graph<String, Integer> directedGraph;

	/*
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
	/**
	 * Sets up graphs for testing
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Integer>();
		directedGraph = new AdjacencyListGraph<String, Integer>(true);

		v1 = undirectedGraph.insertVertex("Raleigh");
		v2 = undirectedGraph.insertVertex("Asheville");
		v3 = undirectedGraph.insertVertex("Wilmington");
		v4 = undirectedGraph.insertVertex("Durham");
		v5 = undirectedGraph.insertVertex("Greenville");

		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);
	}

	/**
	 * Tests depthFirstSearch()
	 */
	@Test
	public void testDepthFirstSearch() {
		Map<Vertex<String>, Edge<Integer>> um = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
		assertFalse(um.isEmpty());
		assertEquals(um.size(), 4);
		assertEquals(um.get(v2).getElement(), (Integer) 5);
		assertEquals(um.get(v3).getElement(), (Integer) 25);
		assertEquals(um.get(v4).getElement(), (Integer) 40);
		assertEquals(um.get(v5).getElement(), (Integer) 50);

		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");

		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		Map<Vertex<String>, Edge<Integer>> dm = GraphTraversalUtil.depthFirstSearch(directedGraph, v1);
		assertFalse(dm.isEmpty());
		assertEquals(dm.size(), 5);
		assertEquals(dm.get(v2).getElement(), (Integer) 5);
		assertEquals(dm.get(v3).getElement(), (Integer) 25);
		assertEquals(dm.get(v4).getElement(), (Integer) 40);
		assertEquals(dm.get(v5).getElement(), (Integer) 50);

	}

	/**
	 * Tests breadthFirstSearch()
	 */
	@Test
	public void testbreadthFirstSearch() {
		Map<Vertex<String>, Edge<Integer>> um = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
		assertFalse(um.isEmpty());
		assertEquals(um.size(), 4);
		assertEquals(um.get(v2).getElement(), (Integer) 5);
		assertEquals(um.get(v3).getElement(), (Integer) 10);
		assertEquals(um.get(v4).getElement(), (Integer) 15);
		assertEquals(um.get(v5).getElement(), (Integer) 20);

		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");

		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		Map<Vertex<String>, Edge<Integer>> dm = GraphTraversalUtil.breadthFirstSearch(directedGraph, v1);
		assertFalse(dm.isEmpty());
		assertEquals(dm.size(), 5);
		assertEquals(dm.get(v2).getElement(), (Integer) 5);
		assertEquals(dm.get(v3).getElement(), (Integer) 10);
		assertEquals(dm.get(v4).getElement(), (Integer) 15);
		assertEquals(dm.get(v5).getElement(), (Integer) 20);

	}

}