package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Splay tree extends binary search tree map
 *
 * Implementation based off of information provided by "Data Structures and
 * Algorithms" by Goodrich, Tamassia, Goldwasser.
 *
 * @author Sanjana Cheerla
 * @param <K> key
 * @param <V> value
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * create a new splay tree
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * create a new splay tree with a comparator
	 * 
	 * @param compare comparator for splay tree
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * splay tree (move p to be root)
	 * 
	 * @param p to splay
	 */
	private void splay(Position<Entry<K, V>> p) {
		var node = p;
		while (!isRoot(node)) {
			var parent = parent(node);
			var gParent = parent(parent);

			if (gParent == null) {
				rotate(node);
			}

			else if ((node == left(parent) && parent == left(gParent))
					|| (node == right(parent) && parent == right(gParent))) {
				rotate(parent);
				rotate(node);
			} else {
				rotate(node);
				rotate(node);
			}
		}
	}

	/**
	 * splay node when accessed
	 * 
	 * @param p node accessed
	 */
	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if (isLeaf(p)) {
			p = parent(p);
		}
		if (p != null) {
			splay(p);
		}
	}

	/**
	 * splay node when inserted
	 * 
	 * @param node inserted
	 */
	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	/**
	 * splay node when deleted
	 * 
	 * @param p node deleted
	 */
	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if (!isRoot(p)) {
			splay(parent(p));
		}
	}
}