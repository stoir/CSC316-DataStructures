package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * adjacency matrix graph
 * 
 * @author sanjana cheerla
 *
 * @param <V> vertex
 * @param <E> edge
 */
public class AdjacencyMatrixGraph<V, E> extends AbstractGraph<V, E> {

	/**
	 * graph edge matrix
	 */
	private GraphEdge[][] matrix;
	/**
	 * vertex positional list
	 */
	private PositionalList<Vertex<V>> vertexList;
	/**
	 * edge positional list
	 */
	private PositionalList<Edge<E>> edgeList;
	/**
	 * vertex map of vertices and integers
	 */
	private Map<Vertex<V>, Integer> vertexMap;

	/**
	 * create a adjacency max graph that is undirected
	 */
	public AdjacencyMatrixGraph() {
		this(false);
	}

	/**
	 * create a adjacency matrix graph that is directed or undirected
	 * 
	 * @param directed for graph
	 */
	@SuppressWarnings("unchecked")
	public AdjacencyMatrixGraph(boolean directed) {
		super(directed);
		matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
		vertexList = new PositionalLinkedList<Vertex<V>>();
		edgeList = new PositionalLinkedList<Edge<E>>();
		vertexMap = new LinearProbingHashMap<Vertex<V>, Integer>();
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
		GraphVertex v1 = validate(vertex1);
		GraphVertex v2 = validate(vertex2);
		return matrix[vertexMap.get(v1)][vertexMap.get(v2)];
	}

	/**
	 * get vertices of the edge
	 * 
	 * @param edge to get vertices of
	 * @return the two end points vertices of edge e. For a directed graph, the
	 *         first vertex is the source vertex and the second is the destination
	 *         vertex
	 */
	@Override
	public Vertex<V>[] endVertices(Edge<E> edge) {
		GraphEdge e = validate(edge);
		return e.getEndpoints();
	}

	/**
	 * get the other vertex of the edge, given an edge e incident to vertex v
	 * 
	 * @param vertex to get edge of
	 * @param edge   that exists
	 * @return the other vertex of the edge, given an edge e incident to vertex v
	 */
	@Override
	public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
		GraphEdge temp = validate(edge);
		Vertex<V>[] ends = temp.getEndpoints();
		if (ends[0] == vertex) {
			return ends[1];
		}
		if (ends[1] == vertex) {
			return ends[0];
		}
		throw new IllegalArgumentException("Vertex is not incident on this edge.");
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
		for (GraphEdge e : matrix[vertexMap.get(v)]) {
			if (e != null) {
				list.addLast(e);
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
		for (int i = 0; i < matrix.length; i++) {
			GraphEdge e = matrix[i][vertexMap.get(v)];
			if (e != null) {
				list.addLast(e);
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
		vertexMap.put(v, vertexMap.size());
		v.setPosition(pos);
		growArray();
		return v;
	}

	/**
	 * grow array method
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		// We will grow the matrix each time a new vertex is added
		GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				temp[i][j] = matrix[i][j];
			}
		}
		matrix = temp;
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
		matrix[vertexMap.get(origin)][vertexMap.get(destination)] = e;
		if (!isDirected()) {
			matrix[vertexMap.get(destination)][vertexMap.get(origin)] = e;
		}
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
		int idx = vertexMap.get(v);
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[idx][i] != null) {
				removeEdge(matrix[idx][i]);
				matrix[idx][i] = null;
			}
			if (matrix[i][idx] != null) {
				removeEdge(matrix[i][idx]);
				matrix[i][idx] = null;
			}
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

		Vertex<V> origin = validate(ends[0]);
		Vertex<V> dest = validate(ends[1]);

		int originIndex = vertexMap.get(origin);
		int destIndex = vertexMap.get(dest);

		matrix[originIndex][destIndex] = null;

		return edgeList.remove(e.getPosition());
	}

	/**
	 * validate graph vertex
	 * 
	 * @param v to validate
	 * @return validated vertex
	 * @throws throw new IllegalArgumentException witth the message "Vertex is not a
	 *               valid graph vertex."
	 */
	private GraphVertex validate(Vertex<V> v) {
		if (!(v instanceof AbstractGraph.GraphVertex)) {
			throw new IllegalArgumentException("Vertex is not a valid graph vertex.");
		}
		return (GraphVertex) v;
	}
}