package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * uptree disjoint set implements disjoint set forest
 * 
 * @author sanjana cheerla
 *
 * @param <E> generic param
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

	/**
	 * We need a secondary map to quickly locate an entry within the forest of
	 * up-trees NOTE: The textbook implementation does not include this
	 * functionality; instead, the textbook implementation leaves the responsibility
	 * of tracking positions to the client in a separate map structure
	 */
	private Map<E, UpTreeNode<E>> map;

	/**
	 * create a new disjoint set forest
	 */
	public UpTreeDisjointSetForest() {
		// Use an efficient map!
		map = new LinearProbingHashMap<E, UpTreeNode<E>>();
	}

	/**
	 * Creates a disjoint set that contains the element e, then returns the position
	 * of the set
	 * 
	 * @param value to make set from
	 * @return position of the set
	 */
	@Override
	public Position<E> makeSet(E value) {
		Position<E> node = new UpTreeNode<E>(value);
		map.put(value, (UpTreeNode<E>) node);
		return node;
	}

	/**
	 * find the value in the set
	 * 
	 * @param value to find
	 * @return position of value in set
	 */
	@Override
	public Position<E> find(E value) {
		return findHelper(map.get(value));
	}

	private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
		current = validate(current);
		if (current != current.getParent()) {
			current.setParent(findHelper(current.getParent()));
		}
		return current.getParent();
	}

	/**
	 * Merges the disjoint sets that contain positions s and t
	 * 
	 * @param s set s to union
	 * @param t set t to union
	 */
	@Override
	public void union(Position<E> s, Position<E> t) {
		// Instead of trusting the client to give us the roots
		// of two up-trees, we will verify by finding the root
		// of the up-tree that contains the element in positions s and t
		UpTreeNode<E> a = validate(find(s.getElement()));
		UpTreeNode<E> b = validate(find(t.getElement()));
		if (a.getCount() <= b.getCount()) {
			b.setCount(a.getCount() + b.getCount());
			a.setParent(b);
		} else {
			a.setCount(a.getCount() + b.getCount());
			b.setParent(a);
		}
	}

	/**
	 * validate the position
	 * 
	 * @param p position to validate
	 * @return the validated position
	 * @throws IllegalArgumentException with the message "Position is not a valid up
	 *                                  tree node."
	 */
	private UpTreeNode<E> validate(Position<E> p) {
		if (!(p instanceof UpTreeNode)) {
			throw new IllegalArgumentException("Position is not a valid up tree node.");
		}
		return (UpTreeNode<E>) p;
	}

	/**
	 * up tree node implements position
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <E> generic param
	 */
	private static class UpTreeNode<E> implements Position<E> {

		/**
		 * element for position
		 */
		private E element;
		/**
		 * parent of up tree node
		 */
		private UpTreeNode<E> parent;
		/**
		 * count
		 */
		private int count;

		/**
		 * constructor to create a new up tree node
		 * 
		 * @param element
		 */
		public UpTreeNode(E element) {
			setElement(element);
			setParent(this);
			setCount(1);
		}

		/**
		 * set the element
		 * 
		 * @param element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}

		/**
		 * get the element
		 * 
		 * @return element
		 */
		public E getElement() {
			return element;
		}

		/**
		 * set the parent
		 * 
		 * @param parent to set
		 */
		public void setParent(UpTreeNode<E> parent) {
			this.parent = parent;
		}

		/**
		 * get the parent
		 * 
		 * @return parent
		 */
		public UpTreeNode<E> getParent() {
			return parent;
		}

		/**
		 * set count
		 * 
		 * @param count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * get the count
		 * 
		 * @return count
		 */
		public int getCount() {
			return count;
		}
	}
}