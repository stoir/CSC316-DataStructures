package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * adjacency map graph
 * 
 * @author sanjana cheerla
 *
 * @param <V> vertex
 * @param <E> edge
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

	/**
	 * vertex list
	 */
	private PositionalList<Vertex<V>> vertexList;
	/**
	 * edge list
	 */
	private PositionalList<Edge<E>> edgeList;

	/**
	 * create a adjacency map graph that is undirected
	 */
	public AdjacencyMapGraph() {
		this(false);
	}

	/**
	 * create a adjacency map graph that is directed or undirected
	 * 
	 * @param directed for graph
	 */
	public AdjacencyMapGraph(boolean directed) {
		super(directed);
		vertexList = new PositionalLinkedList<Vertex<V>>();
		edgeList = new PositionalLinkedList<Edge<E>>();
	}

	/**
	 * get num vertices
	 * 
	 * @return the number of vertices in the graph
	 */
	@Override
	public int numVertices() {
		return vertexList.size();
	}

	/**
	 * iterator for vertices
	 * 
	 * @return iterator of vertices
	 */
	@Override
	public Iterable<Vertex<V>> vertices() {
		return vertexList;
	}

	/**
	 * get the number of edges in a graph
	 * 
	 * @return the number of edges
	 */
	@Override
	public int numEdges() {
		return edgeList.size();
	}

	/**
	 * iterator for edges
	 * 
	 * @return iterator of edges
	 */
	@Override
	public Iterable<Edge<E>> edges() {
		return edgeList;
	}

	/**
	 * get the edge between 2 vertices
	 * 
	 * @param vertex1 to get edge of
	 * @param vertex2 to get edge of
	 * @return the edge between 2 vertices
	 */
	@Override
	public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
		return validate(vertex1).getOutgoing().get(vertex2);
	}

	/**
	 * get the number of outgoing edges from v
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return the number of outgoing edges from v
	 */
	@Override
	public int outDegree(Vertex<V> vertex) {
		return validate(vertex).getOutgoing().size();
	}

	/**
	 * get the number of incoming edges to v. For undirected graphs, outDegree(v) =
	 * inDegree(v)
	 * 
	 * @param vertex to get incoming edges of
	 * @return the number of incoming edges to v. For undirected graphs,
	 *         outDegree(v) = inDegree(v)
	 */
	@Override
	public int inDegree(Vertex<V> vertex) {
		return validate(vertex).getIncoming().size();
	}

	/**
	 * iterator for outgoing edges
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return iterator for outgoing edges
	 */
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
		return validate(vertex).getOutgoing().values();
	}

	/**
	 * iterator for incoming edges
	 * 
	 * @param vertex to get the incoming edges of
	 * @return iterator for incoming edges
	 */
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
		return validate(vertex).getIncoming().values();
	}

	/**
	 * Creates and returns a new Vertex storing element x
	 * 
	 * @param vertexData for new vertex
	 * @return Creates and returns a new Vertex storing element x
	 */
	@Override
	public Vertex<V> insertVertex(V vertexData) {
		AMVertex vertex = new AMVertex(vertexData, isDirected());
		Position<Vertex<V>> pos = vertexList.addLast(vertex);
		vertex.setPosition(pos);
		return vertex;
	}

	/**
	 * Creates and returns a new Edge from vertex u to vertex v and stores element x
	 * 
	 * @param v1       for edge
	 * @param v2       for edge
	 * @param edgeData for edge
	 * @return Creates and returns a new Edge from vertex u to vertex v and stores
	 *         element x
	 */
	@Override
	public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
		AMVertex origin = validate(v1);
		AMVertex destination = validate(v2);
		GraphEdge edge = new GraphEdge(edgeData, origin, destination);
		Position<Edge<E>> pos = edgeList.addLast(edge);
		edge.setPosition(pos);
		origin.getOutgoing().put(v2, edge);
		destination.getIncoming().put(v1, edge);
		return edge;
		// Remember to add the edge to the maps for each endpoint vertex
	}

	/**
	 * Removes vertex v and all its incident edges from the graph
	 * 
	 * @param vertex to remove
	 * @return removed vertex
	 */
	@Override
	public Vertex<V> removeVertex(Vertex<V> vertex) {
		AMVertex v = validate(vertex);
		for (Edge<E> e : v.getOutgoing().values()) {
			removeEdge(e);
		}
		for (Edge<E> e : v.getIncoming().values()) {
			removeEdge(e);
		}
		return vertexList.remove(v.getPosition());
	}

	/**
	 * Removes edge e from the graph
	 * 
	 * @param edge to remove
	 * @return removed edge
	 */
	@Override
	public Edge<E> removeEdge(Edge<E> edge) {
		GraphEdge e = validate(edge);
		Vertex<V>[] ends = e.getEndpoints();
		AMVertex origin = validate(ends[0]);
		AMVertex dest = validate(ends[1]);

		var outgoing = origin.getOutgoing();
		outgoing.remove(dest);
		var incoming = dest.getIncoming();
		incoming.remove(origin);

		var removed = edgeList.remove(e.getPosition());
		return removed;
	}

	/**
	 * adjacency map vertex
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class AMVertex extends GraphVertex {

		/**
		 * outgoing edge
		 */
		private Map<Vertex<V>, Edge<E>> outgoing;

		/**
		 * incoming edge
		 */
		private Map<Vertex<V>, Edge<E>> incoming;

		/**
		 * create a adjacency map vertex
		 * 
		 * @param data       for vertex
		 * @param isDirected for vertex
		 */
		public AMVertex(V data, boolean isDirected) {
			super(data);
			outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
			if (isDirected) {
				incoming = new LinearProbingHashMap<>();
			} else {
				incoming = outgoing;
			}
		}

		/**
		 * get the outgoing edge
		 * 
		 * @return outgoing edge
		 */
		public Map<Vertex<V>, Edge<E>> getOutgoing() {
			return outgoing;
		}

		/**
		 * get the incoming edge
		 * 
		 * @return the incoming edge
		 */
		public Map<Vertex<V>, Edge<E>> getIncoming() {
			return incoming;
		}
	}

	/**
	 * validate vertex
	 * 
	 * @param v to validate
	 * @return validated vertex
	 * @throws IllegalArgumentException with the message "Vertex is not a valid
	 *                                  adjacency map vertex."
	 */
	private AMVertex validate(Vertex<V> v) {
		if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
			throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
		}
		return (AMVertex) v;
	}
}