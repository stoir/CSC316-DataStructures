package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * abstract graph
 * 
 * @author sanjana cheerla
 *
 * @param <V> vertex
 * @param <E> edge
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {

	/**
	 * field to see if graph is directed or not
	 */
	private boolean isDirected;

	/**
	 * create a graph
	 * 
	 * @param directed boolean for graph
	 */
	public AbstractGraph(boolean directed) {
		setDirected(directed);
	}

	/**
	 * set boolean directed
	 * 
	 * @param directed to set
	 */
	private void setDirected(boolean directed) {
		isDirected = directed;
	}

	/**
	 * check to see if the graph is directed
	 * 
	 * @return true if the graph is a directed graph, otherwise returns false
	 */
	public boolean isDirected() {
		return isDirected;
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
		return validate(edge).getEndpoints();
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
	 * graph vertex
	 * 
	 * @author sanjana cheerla
	 *
	 */
	protected class GraphVertex implements Vertex<V> {

		/**
		 * element of vertex
		 */
		private V element;

		/**
		 * vertex position
		 */
		private Position<Vertex<V>> position;

		/**
		 * set the element of the vertex
		 * 
		 * @param element to set
		 */
		public GraphVertex(V element) {
			setElement(element);
		}

		/**
		 * set element of the fied
		 * 
		 * @param element to set
		 */
		public void setElement(V element) {
			this.element = element;
		}

		/**
		 * get vertex element
		 * 
		 * @return vertex element
		 */
		@Override
		public V getElement() {
			return element;
		}

		/**
		 * get position vertex
		 * 
		 * @return position vertex
		 */
		public Position<Vertex<V>> getPosition() {
			return position;
		}

		/**
		 * set position vertex
		 * 
		 * @param pos vertex to set
		 */
		public void setPosition(Position<Vertex<V>> pos) {
			position = pos;
		}
	}

	/**
	 * graph edge
	 * 
	 * @author sanjana cheerla
	 *
	 */
	protected class GraphEdge implements Edge<E> {

		/**
		 * element of edge
		 */
		private E element;
		/**
		 * edge end points
		 */
		private Vertex<V>[] endpoints;
		/**
		 * edge
		 */
		private Position<Edge<E>> position;

		/**
		 * create a graph edge
		 * 
		 * @param element for edge
		 * @param v1      vertex one
		 * @param v2      vertex two
		 */
		@SuppressWarnings("unchecked")
		public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
			setElement(element);
			endpoints = (Vertex<V>[]) new Vertex[] { v1, v2 };
		}

		/**
		 * set element of edge
		 * 
		 * @param element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}

		/**
		 * get edge element
		 * 
		 * @return edge element
		 */
		@Override
		public E getElement() {
			return element;
		}

		/**
		 * get end points
		 * 
		 * @return array of vertex end points
		 */
		public Vertex<V>[] getEndpoints() {
			return endpoints;
		}

		/**
		 * get position edge
		 * 
		 * @return position edge
		 */
		public Position<Edge<E>> getPosition() {
			return position;
		}

		/**
		 * set position edge
		 * 
		 * @param pos to set
		 */
		public void setPosition(Position<Edge<E>> pos) {
			position = pos;
		}

		/**
		 * to string of edge
		 * 
		 * @return string of edge
		 */
		@Override
		public String toString() {
			return "Edge[element=" + element + "]";
		}
	}

	/**
	 * validate the graph edge
	 * 
	 * @param e to validate
	 * @return validated graph edge
	 * @throws IllegalArgumentException with message "Edge is not a valid graph
	 *                                  edge."
	 */
	protected GraphEdge validate(Edge<E> e) {
		if (!(e instanceof AbstractGraph.GraphEdge)) {
			throw new IllegalArgumentException("Edge is not a valid graph edge.");
		}
		return (GraphEdge) e;
	}
}