package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.UnorderedArrayMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * Graph traversals
 * 
 * @author sanjana cheerla
 *
 */
public class GraphTraversalUtil {

	/**
	 * perform depth first search
	 * 
	 * @param <V>   vertex
	 * @param <E>   edge
	 * @param graph graph
	 * @param start vertex
	 * @return searched element
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
		Set<Vertex<V>> hs = new HashSet<Vertex<V>>();
		Map<Vertex<V>, Edge<E>> map = new UnorderedArrayMap<Vertex<V>, Edge<E>>();
		dfsHelper(graph, start, hs, map);
		return map;
	}

	/**
	 * helper for depth first search
	 * 
	 * @param <V>    vertex
	 * @param <E>    edge
	 * @param graph  graph
	 * @param u      vertex
	 * @param known  set
	 * @param forest edge
	 */
	private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> u, Set<Vertex<V>> known,
			Map<Vertex<V>, Edge<E>> forest) {
		known.add(u);
		for (Edge<E> e : graph.outgoingEdges(u)) {
			Vertex<V> v = graph.opposite(u, e);
			if (!known.contains(v)) {
				forest.put(v, e);
				dfsHelper(graph, v, known, forest);
			}
		}
	}

	/**
	 * breadth first search
	 * 
	 * @param <V>   vertex
	 * @param <E>   edge
	 * @param graph edge
	 * @param start vertex
	 * @return searched element
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
		Set<Vertex<V>> hs = new HashSet<Vertex<V>>();
		Map<Vertex<V>, Edge<E>> map = new UnorderedArrayMap<Vertex<V>, Edge<E>>();
		Queue<Vertex<V>> queue = new ArrayBasedQueue<Vertex<V>>();
		hs.add(start);
		queue.enqueue(start);
		while (!queue.isEmpty()) {
			Vertex<V> u = queue.dequeue();
			for (Edge<E> e : graph.outgoingEdges(u)) {
				Vertex<V> w = graph.opposite(u, e);
				if (!hs.contains(w)) {
					hs.add(w);
					map.put(w, e);
					queue.enqueue(w);
				}
			}
		}
		return map;
	}
}