package edu.ncsu.csc316.dsa.graph;

/**
 * graph interface
 * 
 * @author sanjana cheerla
 *
 * @param <V> vertex
 * @param <E> edge
 */
public interface Graph<V, E> {

	/**
	 * check to see if the graph is directed
	 * 
	 * @return true if the graph is a directed graph, otherwise returns false
	 */
	boolean isDirected();

	/**
	 * get num vertices
	 * 
	 * @return the number of vertices in the graph
	 */
	int numVertices();

	/**
	 * iterator for vertices
	 * 
	 * @return iterator of vertices
	 */
	Iterable<Vertex<V>> vertices();

	/**
	 * get the number of edges in a graph
	 * 
	 * @return the number of edges
	 */
	int numEdges();

	/**
	 * iterator for edges
	 * 
	 * @return iterator of edges
	 */
	Iterable<Edge<E>> edges();

	/**
	 * get the edge between 2 vertices
	 * 
	 * @param vertex1 to get edge of
	 * @param vertex2 to get edge of
	 * @return the edge between 2 vertices
	 */
	Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);

	/**
	 * get vertices of the edge
	 * 
	 * @param edge to get vertices of
	 * @return the two end points vertices of edge e. For a directed graph, the
	 *         first vertex is the source vertex and the second is the destination
	 *         vertex
	 */
	Vertex<V>[] endVertices(Edge<E> edge);

	/**
	 * get the other vertex of the edge, given an edge e incident to vertex v
	 * 
	 * @param vertex to get edge of
	 * @param edge   that exists
	 * @return the other vertex of the edge, given an edge e incident to vertex v
	 */
	Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);

	/**
	 * get the number of outgoing edges from v
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return the number of outgoing edges from v
	 */
	int outDegree(Vertex<V> vertex);

	/**
	 * get the number of incoming edges to v. For undirected graphs, outDegree(v) =
	 * inDegree(v)
	 * 
	 * @param vertex to get incoming edges of
	 * @return the number of incoming edges to v. For undirected graphs,
	 *         outDegree(v) = inDegree(v)
	 */
	int inDegree(Vertex<V> vertex);

	/**
	 * iterator for outgoing edges
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return iterator for outgoing edges
	 */
	Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);

	/**
	 * iterator for incoming edges
	 * 
	 * @param vertex to get the incoming edges of
	 * @return iterator for incoming edges
	 */
	Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);

	/**
	 * Creates and returns a new Vertex storing element x
	 * 
	 * @param vertexData for new vertex
	 * @return Creates and returns a new Vertex storing element x
	 */
	Vertex<V> insertVertex(V vertexData);

	/**
	 * Creates and returns a new Edge from vertex u to vertex v and stores element x
	 * 
	 * @param v1       for edge
	 * @param v2       for edge
	 * @param edgeData for edge
	 * @return Creates and returns a new Edge from vertex u to vertex v and stores
	 *         element x
	 */
	Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);

	/**
	 * Removes vertex v and all its incident edges from the graph
	 * 
	 * @param vertex to remove
	 * @return removed vertex
	 */
	Vertex<V> removeVertex(Vertex<V> vertex);

	/**
	 * Removes edge e from the graph
	 * 
	 * @param edge to remove
	 * @return removed edge
	 */
	Edge<E> removeEdge(Edge<E> edge);

	/**
	 * edge interface
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> edge
	 */
	interface Edge<E> {

		/**
		 * get edge element
		 * 
		 * @return edge element
		 */
		E getElement();
	}

	/**
	 * vertex interface
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <V> vertex
	 */
	interface Vertex<V> {

		/**
		 * get vertex element
		 * 
		 * @return vertex element
		 */
		V getElement();
	}
}