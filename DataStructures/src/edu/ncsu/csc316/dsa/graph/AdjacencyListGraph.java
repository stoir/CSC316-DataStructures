package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * adjacency list graph
 * 
 * @author sanjana cheerla
 *
 * @param <V> vertex
 * @param <E> edge
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

	/**
	 * vertex list
	 */
	private PositionalList<Vertex<V>> vertexList;
	/**
	 * edge list
	 */
	private PositionalList<Edge<E>> edgeList;

	/**
	 * create a adjacency list graph that is undirected
	 */
	public AdjacencyListGraph() {
		this(false);
	}

	/**
	 * create a adjacency list graph that is directed or undirected
	 * 
	 * @param directed for graph
	 */
	public AdjacencyListGraph(boolean directed) {
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
	 * iterator for outgoing edges
	 * 
	 * @param vertex to get the outgoing edges of
	 * @return iterator for outgoing edges
	 */
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
		return validate(vertex).getOutgoing();
	}

	/**
	 * iterator for incoming edges
	 * 
	 * @param vertex to get the incoming edges of
	 * @return iterator for incoming edges
	 */
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
		return validate(vertex).getIncoming();
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
		ALVertex v1 = validate(vertex1);
		ALVertex v2 = validate(vertex2);
		Iterator<Edge<E>> it = edgeList.iterator();
		while (it.hasNext()) {
			GraphEdge current = validate(it.next());
			Vertex<V>[] ends = current.getEndpoints();
			if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
				return current;
			}
			if (ends[0] == v1 && ends[1] == v2) {
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
	 * Creates and returns a new Vertex storing element x
	 * 
	 * @param vertexData for new vertex
	 * @return Creates and returns a new Vertex storing element x
	 */
	@Override
	public Vertex<V> insertVertex(V vertexData) {
		ALVertex vertex = new ALVertex(vertexData, isDirected());
		Position<Vertex<V>> pos = vertexList.addLast(vertex);
		vertex.setPosition(pos);
		return vertex;

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
		ALVertex origin = validate(vertex1);
		ALVertex destination = validate(vertex2);
		ALEdge edge = new ALEdge(edgeData, origin, destination);
		Position<Edge<E>> pos = edgeList.addLast(edge);
		edge.setPosition(pos);
		// Remember to set the edge's positions in the outgoingEdges
		// and incomingEdges lists for the appropriate vertices
		var outgoing = origin.getOutgoing();
		var incoming = destination.getIncoming();
		edge.setOutgoingListPosition(outgoing.addLast(edge));
		edge.setIncomingListPosition(incoming.addLast(edge));
		return edge;
	}

	/**
	 * Removes vertex v and all its incident edges from the graph
	 * 
	 * @param vertex to remove
	 * @return removed vertex
	 */
	@Override
	public Vertex<V> removeVertex(Vertex<V> vertex) {
		ALVertex v = validate(vertex);
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
		ALEdge e = validate(edge);
		Vertex<V>[] ends = e.getEndpoints();
		ALVertex origin = validate(ends[0]);
		ALVertex dest = validate(ends[1]);
		dest.getOutgoing().remove(e.getOutgoingListPosition());
		origin.getIncoming().remove(e.getIncomingListPosition());
		return edgeList.remove(e.getPosition());
	}

	/**
	 * create a adjacency list vertex
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class ALVertex extends GraphVertex {

		/**
		 * outgoing edge
		 */
		private PositionalList<Edge<E>> outgoing;

		/**
		 * incoming edge
		 */
		private PositionalList<Edge<E>> incoming;

		/**
		 * create a adjacency list vertex
		 * 
		 * @param data       for vertex
		 * @param isDirected for vertex
		 */
		public ALVertex(V data, boolean isDirected) {
			super(data);
			outgoing = new PositionalLinkedList<Edge<E>>();
			if (isDirected) {
				incoming = new PositionalLinkedList<Edge<E>>();
			} else {
				incoming = outgoing;
			}
		}

		/**
		 * get the outgoing edge
		 * 
		 * @return outgoing edge
		 */
		public PositionalList<Edge<E>> getOutgoing() {
			return outgoing;
		}

		/**
		 * get the incoming edge
		 * 
		 * @return the incoming edge
		 */
		public PositionalList<Edge<E>> getIncoming() {
			return incoming;
		}
	}

	/**
	 * adjacency list edge
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class ALEdge extends GraphEdge {

		/**
		 * outgoing edge position
		 */
		private Position<Edge<E>> outgoingListPosition;
		/**
		 * incoming edge position
		 */
		private Position<Edge<E>> incomingListPosition;

		/**
		 * adjacency list edge
		 * 
		 * @param element for edge
		 * @param v1      vertex 1 of edge
		 * @param v2      vertex 2 of edge
		 */
		public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
			super(element, v1, v2);
		}

		/**
		 * get the outgoing list position
		 * 
		 * @return the outgoing list position
		 */
		public Position<Edge<E>> getOutgoingListPosition() {
			return outgoingListPosition;
		}

		/**
		 * set the outgoing list position
		 * 
		 * @param outgoingListPosition to set
		 */
		public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
			this.outgoingListPosition = outgoingListPosition;
		}

		/**
		 * get the incoming list position
		 * 
		 * @return incoming list position
		 */
		public Position<Edge<E>> getIncomingListPosition() {
			return incomingListPosition;
		}

		/**
		 * set incoming list position
		 * 
		 * @param incomingListPosition to set
		 */
		public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
			this.incomingListPosition = incomingListPosition;
		}
	}

	/**
	 * validate the adjacency list vertex
	 * 
	 * @param v to validate
	 * @return validated vertex
	 * @throws IllegalArgumentException with the message"Vertex is not a valid
	 *                                  adjacency list vertex."
	 */
	private ALVertex validate(Vertex<V> v) {
		if (!(v instanceof AdjacencyListGraph.ALVertex)) {
			throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
		}
		return (ALVertex) v;
	}

	/**
	 * validate the adjacency list edge
	 * 
	 * @param e to validate
	 * @return validated edge
	 * @throws IllegalArgumentException with the message "Edge is not a valid
	 *                                  adjacency list edge."
	 */
	protected ALEdge validate(Edge<E> e) {
		if (!(e instanceof AdjacencyListGraph.ALEdge)) {
			throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
		}
		return (ALEdge) e;
	}
}