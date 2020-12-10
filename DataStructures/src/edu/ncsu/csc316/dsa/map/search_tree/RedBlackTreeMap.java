package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * red black tree extends binary search tree map
 *
 * Implementation based off of information provided by "Data Structures and
 * Algorithms" by Goodrich, Tamassia, Goldwasser.
 *
 * @author Sanjana Cheerla
 * @param <K> key
 * @param <V> value
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * create a red black tree
	 */
	public RedBlackTreeMap() {
		super(null);
	}

	/**
	 * create red black tree withh comparator
	 * 
	 * @param compare for tree
	 */
	public RedBlackTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * Helper method to abstract the idea of black
	 * 
	 * @param p node
	 * @return if the node is black or not
	 */
	private boolean isBlack(Position<Entry<K, V>> p) {
		return getProperty(p) == 0;
	}

	/**
	 * Helper method to abstract the idea of red
	 * 
	 * @param p node
	 * @return if the node is red or not
	 */
	private boolean isRed(Position<Entry<K, V>> p) {
		return getProperty(p) == 1;
	}

	/**
	 * Set the color for a node to be black
	 * 
	 * @param p to set the node
	 */
	private void makeBlack(Position<Entry<K, V>> p) {
		setProperty(p, 0);
	}

	/**
	 * Set the color for a node to be red
	 * 
	 * @param p to set the node
	 */
	private void makeRed(Position<Entry<K, V>> p) {
		setProperty(p, 1);
	}

	/**
	 * resolve the node if necessary
	 * 
	 * @param p node to resolve
	 */
	private void resolveRed(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> parent, uncle, middle, grand;
		parent = parent(p);
		if (isRed(parent)) {
			uncle = sibling(parent);
			if (isBlack(uncle)) {
				middle = restructure(p);
				makeBlack(middle);
				makeRed(left(middle));
				makeRed(right(middle));
			} else {
				makeBlack(parent);
				makeBlack(uncle);
				grand = parent(parent);
				if (!isRoot(grand)) {
					makeRed(grand);
					resolveRed(grand);
				}
			}
		}
	}

	/**
	 * remedy double black nodes if necessary
	 * 
	 * @param p to remedy
	 */
	private void remedyDoubleBlack(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> z = parent(p);
		Position<Entry<K, V>> y = sibling(p);

		if (isBlack(y)) {
			if (isRed(left(y)) || isRed(right(y))) {
				Position<Entry<K, V>> x = (isRed(left(y)) ? left(y) : right(y));
				Position<Entry<K, V>> middle = restructure(x);
				if (isRed(z)) {
					makeRed(middle);
				} else {
					makeBlack(middle);
				}
//				setColor(middle, isRed(z));
				makeBlack(left(middle));
				makeBlack(right(middle));
			} else {
				makeRed(y);
				if (isRed(z)) {
					makeBlack(z);
				} else if (!isRoot(z)) {
					remedyDoubleBlack(z);
				}
			}
		} else {
			rotate(y);
			makeBlack(y);
			makeRed(z);
			remedyDoubleBlack(p);
		}
	}

	/**
	 * take action on insert if p isn't the root, make it red and resolve
	 * 
	 * @param p node to take action on
	 */
	@Override
	protected void actionOnInsert(Position<Entry<K, V>> p) {
		if (!isRoot(p)) {
			makeRed(p);
			resolveRed(p);
		}
	}

	/**
	 * action on delete remedy black and make black if necessary
	 * 
	 * @param p node to take action on
	 */
	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if (isRed(p)) {
			makeBlack(p);
		} else if (!isRoot(p)) {
			Position<Entry<K, V>> sib = sibling(p);
			if (isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))) {
				remedyDoubleBlack(p);
			}
		}
	}
}