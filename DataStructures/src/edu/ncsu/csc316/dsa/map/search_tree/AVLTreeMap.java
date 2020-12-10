package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * AVL tree extends binary search tree map
 *
 * Implementation based off of information provided by "Data Structures and
 * Algorithms" by Goodrich, Tamassia, Goldwasser.
 *
 * @author Sanjana Cheerla
 * @param <K> key
 * @param <V> value
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * avl tree constructor
	 */
	public AVLTreeMap() {
		super(null);
	}

	/**
	 * avl tree constructor with parameter
	 * 
	 * @param compare comparator free tree
	 */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * Helper method to trace upward from position p checking to make sure each node
	 * on the path is balanced. If a rebalance is necessary, trigger a trinode
	 * resturcturing
	 * 
	 * @param p to rebalance
	 */
	private void rebalance(Position<Entry<K, V>> p) {
		var oldHeight = 0;
		var newHeight = 0;
		do {
			oldHeight = getProperty(p);
			if (!isBalanced(p)) {
				p = restructure(tallerChild(tallerChild(p)));
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = getProperty(p);
			p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}

	/**
	 * Returns the child of p that has the greater height If both children have the
	 * same height, use the child that matches the parent's orientation
	 * 
	 * @param p to get the taller child
	 * @return the taller child
	 */
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (getProperty(left(p)) > getProperty(right(p))) {
			return left(p);
		}
		if (getProperty(left(p)) < getProperty(right(p))) {
			return right(p);
		}
		if (isRoot(p)) {
			return left(p);
		}
		if (p == left(parent(p))) {
			return left(p);
		} else {
			return right(p);
		}
	}

	/**
	 * check to see if the tree is balanced
	 * 
	 * @param p node
	 * @return true if balanced false otherwise
	 */
	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}

	/**
	 * recompute the height of the tree
	 * 
	 * @param p to recompute the height for
	 */
	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	/**
	 * action when a node is inserted, re-balance tree
	 * 
	 * @param node that is inserted
	 */
	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	/**
	 * action when a node is deleted, re-balance tree
	 * 
	 * @param node that is deleted
	 */
	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if (!isRoot(node)) {
			rebalance(parent(node));
		}
	}
}