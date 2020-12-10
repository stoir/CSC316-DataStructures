package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * shortest path algorithms
 * 
 * @author sanjana cheerla
 *
 */
public class ShortestPathUtil {

	/**
	 * dijkstras algorithm
	 * 
	 * @param <V> vertex
	 * @param <E> edge
	 * @param g   graph
	 * @param src vertex
	 * @return weight
	 */
	public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> g, Vertex<V> src) {
		Map<Vertex<V>, Integer> m = new LinearProbingHashMap<>();
		AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
		Set<Vertex<V>> known = new HashSet<>();

		for (Vertex<V> v : g.vertices()) {
			if (v == src)
				m.put(v, 0);
			else
				m.put(v, Integer.MAX_VALUE);
			pqEntries.put(v, q.insert(m.get(v), v));
		}

		while (!q.isEmpty()) {
			Vertex<V> u = q.deleteMin().getValue();
			m.put(u, m.get(u));
			known.add(u);
			for (Edge<E> e : g.outgoingEdges(u)) {
				Vertex<V> z = g.opposite(u, e);
				int r = e.getElement().getWeight() + m.get(u);
				if (!known.contains(z) && r < m.get(z)) {
					m.put(z, r);
					q.replaceKey(pqEntries.get(z), r);
				}
			}
		}
		return m;
	}

	/**
	 * shortest path tree
	 * 
	 * @param <V>       vertex
	 * @param <E>       edge
	 * @param g         graph
	 * @param s         vertex
	 * @param distances distance
	 * @return weight
	 */
	public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> g, Vertex<V> s,
			Map<Vertex<V>, Integer> distances) {
		Map<Vertex<V>, Edge<E>> map = new LinearProbingHashMap<>();
		for (Vertex<V> v : distances) {
			if (v != s) {
				for (Edge<E> e : g.incomingEdges(v)) {
					Vertex<V> u = g.opposite(v, e);
					if (distances.get(v).equals(distances.get(u) + e.getElement().getWeight())) {
						map.put(v, e);
					}
				}
			}
		}
		return map;
	}
}
