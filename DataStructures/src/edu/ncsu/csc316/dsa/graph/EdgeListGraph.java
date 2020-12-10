package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * edge list graph
 * 
 * @author sanjana cheerla
 *
 * @param <V> vertex
 * @param <E> edge
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {

	/**
	 * vertices
	 */
	private PositionalList<Vertex<V>> vertexList;
	/**
	 * edges
	 */
	private PositionalList<Edge<E>> edgeList;

	/**
	 * create a edge list graph that is undirected
	 */
	public EdgeListGraph() {
		this(false);
	}

	/**
	 * create a directed or undirected graph
	 * 
	 * @param directed for graph
	 */
	public EdgeListGraph(boolean directed) {
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
	 * validate vertex
	 * 
	 * @param v to validate
	 * @return validated vertex
	 * @throws IllegalArgumentException with the message "Vertex is not a valid edge
	 *                                  list vertex."
	 */
	private GraphVertex validate(Vertex<V> v) {
		if (!(v instanceof AbstractGraph.GraphVertex)) {
			throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
		}
		return (GraphVertex) v;
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
		GraphVertex v1 = validate(vertex1);
		GraphVertex v2 = validate(vertex2);
		Iterator<Edge<E>> it = edgeList.iterator();
		while (it.hasNext()) {
			GraphEdge current = validate(it.next());
			Vertex<V>[] ends = current.getEndpoints();
			if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
				return current;
			}
			if (ends[0] == vertex1 && ends[1] == v2) {
				return current;
			}
		}
		return null;
	}

	/**
	 * get the number of outgoing edges from v
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return the number of outgoing edges from v
	 */
	@Override
	public int outDegree(Vertex<V> vertex) {
		return outgoingEdgeList(vertex).size();
	}

	/**
	 * get a list of outgoing edges from v
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return a list of outgoing edges from v
	 */
	private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
		GraphVertex v = validate(vertex);
		List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
		Iterator<Edge<E>> it = edgeList.iterator();
		while (it.hasNext()) {
			GraphEdge edge = validate(it.next());
			Vertex<V>[] ends = edge.getEndpoints();
			if (ends[0] == v) {
				list.addLast(edge);
			} else if (!isDirected() && ends[1] == v) {
				list.addLast(edge);
			}
		}
		return list;
	}

	/**
	 * get a list of incoming edges from v
	 * 
	 * @param vertex to get the incoming edges of
	 * @return a list of incoming edges from v
	 */
	private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
		GraphVertex v = validate(vertex);
		List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
		Iterator<Edge<E>> it = edgeList.iterator();
		while (it.hasNext()) {
			GraphEdge edge = validate(it.next());
			Vertex<V>[] ends = edge.getEndpoints();
			if (ends[1] == v) {
				list.addLast(edge);
			} else if (!isDirected() && ends[0] == v) {
				list.addLast(edge);
			}
		}
		return list;
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
		return incomingEdgeList(vertex).size();
	}

	/**
	 * iterator for outgoing edges
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return iterator for outgoing edges
	 */
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
		return outgoingEdgeList(vertex);
	}

	/**
	 * iterator for incoming edges
	 * 
	 * @param vertex to get the incoming edges of
	 * @return iterator for incoming edges
	 */
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
		return incomingEdgeList(vertex);
	}

	/**
	 * Creates and returns a new Vertex storing element x
	 * 
	 * @param vertexData for new vertex
	 * @return Creates and returns a new Vertex storing element x
	 */
	@Override
	public Vertex<V> insertVertex(V vertexData) {
		GraphVertex v = new GraphVertex(vertexData);
		Position<Vertex<V>> pos = vertexList.addLast(v);
		v.setPosition(pos);
		return v;
	}

	/**
	 * Creates and returns a new Edge from vertex u to vertex v and stores element x
	 * 
	 * @param vertex1       for edge
	 * @param vertex2       for edge
	 * @param edgeData for edge
	 * @return Creates and returns a new Edge from vertex u to vertex v and stores
	 *         element x
	 */
	@Override
	public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
		GraphVertex origin = validate(vertex1);
		GraphVertex destination = validate(vertex2);
		GraphEdge e = new GraphEdge(edgeData, origin, destination);
		Position<Edge<E>> pos = edgeList.addLast(e);
		e.setPosition(pos);
		return e;
	}

	/**
	 * Removes vertex v and all its incident edges from the graph
	 * 
	 * @param vertex to remove
	 * @return removed vertex
	 */
	@Override
	public Vertex<V> removeVertex(Vertex<V> vertex) {
		GraphVertex v = validate(vertex);
		for (Edge<E> e : outgoingEdges(v)) {
			removeEdge(e);
		}
		for (Edge<E> e : incomingEdges(v)) {
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
		return edgeList.remove(e.getPosition());
	}
}