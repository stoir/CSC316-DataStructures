package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractSortedMap;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.tree.BinaryTree;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * Binary Search Tree Map for implementing a binary search tree and other binary
 * trees such as red black trees, splay trees, and avl trees.
 *
 * Implementation based off of information provided by "Data Structures and
 * Algorithms" by Goodrich, Tamassia, Goldwasser.
 *
 * @author Sanjana Cheerla
 * @param <K> key
 * @param <V> value
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V>
		implements BinaryTree<Entry<K, V>> {

	/**
	 * balancable binary tree of key value pairs
	 */
	private BalanceableBinaryTree<K, V> tree;

	/**
	 * constructor to create a binary search tree
	 */
	public BinarySearchTreeMap() {
		this(null);
	}

	/**
	 * constructor to create a tree for an object with a comparator
	 * 
	 * @param compare comparator for tree
	 */
	public BinarySearchTreeMap(Comparator<K> compare) {
		super(compare);
		tree = new BalanceableBinaryTree<K, V>();
		tree.addRoot(null);
	}

	/**
	 * get the size of the tree
	 * 
	 * @return size of tree
	 */
	@Override
	public int size() {
		// Our search trees will all use dummy/sentinel leaf nodes,
		// so the actual number of elements in the tree will be (size-1)/2
		return (tree.size() - 1) / 2;
	}

	/**
	 * This method is used to add dummy/sentinel left and right children as leaves
	 * 
	 * @param p     position of dummy nodes
	 * @param entry to add the tree nodes at
	 */
	private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
		// initially, p is a dummy/sentinel node,
		// so replace the null entry with the new actual entry
		tree.set(p, entry);
		// Then add new dummy/sentinel children
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}

	/**
	 * This helper method traces a path down the tree to locate the position that
	 * contains an entry with the given key. Think of "lookUp" as returning the last
	 * node visited when tracing a path down the tree to find the given key
	 * 
	 * @param p   position to start at
	 * @param key to look up
	 * @return the looked up position or return the dummy node
	 */
	private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
		// If we have reached a dummy/sentinel node (a leaf), return that sentinel node
		if (isLeaf(p)) {
			return p;
		}
		int comp = compare(key, p.getElement().getKey());
		if (comp == 0) {
			// Return the position that contains the entry with the key
			return p;
		} else if (comp < 0) {
			return lookUp(left(p), key);
		} else {
			return lookUp(right(p), key);
		}
	}

	/**
	 * get the value given the key
	 * 
	 * @param key to get
	 * @return value obtained from key or null if key is a dummy node
	 */
	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(tree.root(), key);
		// actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
		actionOnAccess(p);
		if (isLeaf(p)) {
			return null;
		}
		return p.getElement().getValue();
	}

	/**
	 * add a new key value pair to the tree or override the value if the key already
	 * exists.
	 * 
	 * @param key   to add or override
	 * @param value to add or override
	 * @return null if key was added, old value if key was overriden with new value
	 */
	@Override
	public V put(K key, V value) {
		// Create the new map entry
		Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
		// Get the last node visited when looking for the key
		Position<Entry<K, V>> p = lookUp(root(), key);
		// If the last node visited is a dummy/sentinel node
		if (isLeaf(p)) {
			expandLeaf(p, newEntry);
			// actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnInsert(p);
			return null;
		} else {
			V original = p.getElement().getValue();
			set(p, newEntry);
			// actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnAccess(p);
			return original;
		}
	}

	/**
	 * remove a key
	 * 
	 * @param key to remove
	 * @return null if key does not exist, return the original value removed
	 */
	@Override
	public V remove(K key) {
		// Get the last node visited when looking for the key
		Position<Entry<K, V>> p = lookUp(root(), key);
		// If p is a dummy/sentinel node
		if (isLeaf(p)) {
			// actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnAccess(p);
			return null;
		} else {
			V original = p.getElement().getValue();
			// If the node has two children (that are not dummy/sentinel nodes)
			if (isInternal(left(p)) && isInternal(right(p))) {
				// Replace with the inorder successor
				Position<Entry<K, V>> replacement = treeMin(right(p));
				set(p, replacement.getElement());
				// Move p to the replacement node in the right subtree
				p = replacement;
			}
			// Get the dummy/sentinel node (in case the node has an actual entry as a
			// child)...
			Position<Entry<K, V>> leaf = (isLeaf(left(p)) ? left(p) : right(p));
			// ... then get its sibling (will be another sentinel or an actual entry node)
			Position<Entry<K, V>> sib = sibling(leaf);
			// Remove the leaf NODE (this is your binary tree remove method)
			remove(leaf);
			// Remove the NODE (this is your binary tree remove method)
			// which will "promote" the sib node to replace p
			remove(p);
			// actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use
			actionOnDelete(sib);
			return original;
		}
	}

	/**
	 * Returns the inorder successor (the minimum from the right subtree)
	 * 
	 * @param node to get the successor from
	 * @return the successor position
	 */
	private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> node) {
		Position<Entry<K, V>> current = node;
		while (isInternal(current)) {
			current = left(current);
		}
		return parent(current);
	}

	/**
	 * get the entry set of the tree in order
	 * 
	 * @return the iterable of the entry set
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>(size());
		for (Position<Entry<K, V>> n : tree.inOrder()) {
			set.addLast(n.getElement());
		}
		return set;
	}

	/**
	 * toString the tree
	 * 
	 * @return a string representation of tree
	 */
	@Override
	public String toString() {
		return tree.toString();
	}

	/**
	 * take action on access used for avl, splay and redblack trees
	 * 
	 * @param node to get the action for on access from
	 */
	protected void actionOnAccess(Position<Entry<K, V>> node) {
		// Do nothing for BST
	}

	/**
	 * take action on insert used for avl, splay and redblack trees
	 * 
	 * @param node to get the action for on insert from
	 */
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		// Do nothing for BST
	}

	/**
	 * take action on delete used for avl, splay and redblack trees
	 * 
	 * @param node to get the action for on delete from
	 */
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		// Do nothing for BST
	}

	/**
	 * inner class for balancable tree extending linked binary tree
	 * 
	 * @author sanjana cheerla
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

		/**
		 * Relink is a helper method for trinode restructuring and rotations
		 * 
		 * @param parent        to link
		 * @param child         to link
		 * @param makeLeftChild if necessary
		 */
		private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
			child.setParent(parent);
			if (makeLeftChild) {
				parent.setLeft(child);
			} else {
				parent.setRight(child);
			}
		}

		/**
		 * Rotate is a helper method for handling rotations and relinking nodes
		 * 
		 * @param p to rotate at
		 */
		public void rotate(Position<Entry<K, V>> p) {
			Node<Entry<K, V>> node = validate(p);
			Node<Entry<K, V>> parent = node.getParent();
			Node<Entry<K, V>> gParent = parent.getParent();

			if (gParent == null) {
				this.setRoot(node);
				this.isRoot(node);
				node.setParent(null);
			} else {
				if (parent == gParent.getLeft()) {
					this.relink(gParent, node, true);
				} else {
					this.relink(gParent, node, false);
				}
			}

			if (node == parent.getLeft()) {
				this.relink(parent, node.getRight(), true);
				this.relink(node, parent, false);
			} else {
				this.relink(parent, node.getLeft(), false);
				this.relink(node, parent, true);
			}

		}

		/**
		 * Restructure is a helper method to perform a trinode restructuring and trigger
		 * the appropriate rotations
		 * 
		 * @param x to restructure at
		 * @return the rotated node, or parent
		 */
		public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
			Node<Entry<K, V>> node = validate(x);
			Node<Entry<K, V>> parent = node.getParent();
			Node<Entry<K, V>> gParent = parent.getParent();

			if ((node == parent.getLeft() && parent == gParent.getLeft())
					|| (node == parent.getRight() && parent == gParent.getRight())) {
				this.rotate(parent);
				return parent;
			} else {
				this.rotate(node);
				this.rotate(node);
				return node;
			}

		}

		/**
		 * create a node
		 * 
		 * @param element for node
		 * @param parent  of node
		 * @param left    of node
		 * @param right   of node
		 * @return created node
		 */
		@Override
		protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
				Node<Entry<K, V>> right) {
			BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
			newNode.setParent(parent);
			newNode.setLeft(left);
			newNode.setRight(right);
			newNode.setProperty(0);
			return newNode;
		}

		/**
		 * A binary search tree node
		 * 
		 * @author sanjana cheerla
		 *
		 * @param <E> generic param
		 */
		protected static class BSTNode<E> extends Node<E> {

			/**
			 * property of node
			 */
			private int property;

			/**
			 * create a node with value as element
			 * 
			 * @param element of node
			 */
			public BSTNode(E element) {
				super(element);
				setProperty(0);
			}

			/**
			 * set the property
			 * 
			 * @param height to set
			 */
			public void setProperty(int height) {
				this.property = height;
			}

			/**
			 * get the property
			 * 
			 * @return the property
			 */
			public int getProperty() {
				return property;
			}
		}

		/**
		 * get the height(property) of a tree
		 * 
		 * @param p position of the tree
		 * @return the height of tree
		 */
		public int getProperty(Position<Entry<K, V>> p) {
			if (p == null) {
				return 0;
			}
			BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
			return node.getProperty();
		}

		/**
		 * set the property
		 * 
		 * @param p     to set the property
		 * @param value of property to change
		 */
		public void setProperty(Position<Entry<K, V>> p, int value) {
			BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
			node.setProperty(value);
		}
	}

	/**
	 * get the root of tree
	 * 
	 * @return tree root
	 */
	@Override
	public Position<Entry<K, V>> root() {
		return tree.root();
	}

	/**
	 * return parent of node
	 * 
	 * @param p node
	 * @return parent
	 */
	@Override
	public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
		return tree.parent(p);
	}

	/**
	 * return iterable children of node
	 * 
	 * @param p node
	 * @return iterable of children nodes
	 */
	@Override
	public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) {
		return tree.children(p);
	}

	/**
	 * return number of children of node
	 * 
	 * @param p node
	 * @return number of children nodes
	 */
	@Override
	public int numChildren(Position<Entry<K, V>> p) {
		return tree.numChildren(p);
	}

	/**
	 * check if a node is internal
	 * 
	 * @param p node
	 * @return true if node is internal, false otherwise
	 */
	@Override
	public boolean isInternal(Position<Entry<K, V>> p) {
		return tree.isInternal(p);
	}

	/**
	 * set the position entry to the entry
	 * 
	 * @param p     entry to set
	 * @param entry to set p to
	 * @return the old entry
	 */
	public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
		return tree.set(p, entry);
	}

	/**
	 * check to see if p is a leaf
	 * 
	 * @param p node to check
	 * @return true if a leaf, false otherwise
	 */
	@Override
	public boolean isLeaf(Position<Entry<K, V>> p) {
		return tree.isLeaf(p);
	}

	/**
	 * check to see if p is a root
	 * 
	 * @param p node to check
	 * @return true if a root, false otherwise
	 */
	@Override
	public boolean isRoot(Position<Entry<K, V>> p) {
		return tree.isRoot(p);
	}

	/**
	 * pre order iterable of tree
	 * 
	 * @return preorder iterable
	 */
	@Override
	public Iterable<Position<Entry<K, V>>> preOrder() {
		return tree.preOrder();
	}

	/**
	 * post order iterable of tree
	 * 
	 * @return postorder iterable
	 */
	@Override
	public Iterable<Position<Entry<K, V>>> postOrder() {
		return tree.postOrder();
	}

	/**
	 * level order iterable of tree
	 * 
	 * @return level order iterable
	 */
	@Override
	public Iterable<Position<Entry<K, V>>> levelOrder() {
		return tree.levelOrder();
	}

	/**
	 * get left of position
	 * 
	 * @param p position
	 * @return left of position
	 */
	@Override
	public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
		return tree.left(p);
	}

	/**
	 * remove p
	 * 
	 * @param p to remove
	 * @return removed entry
	 */
	protected Entry<K, V> remove(Position<Entry<K, V>> p) {
		return tree.remove(p);
	}

	/**
	 * get right of position
	 * 
	 * @param p position
	 * @return right of position
	 */
	@Override
	public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
		return tree.right(p);
	}

	/**
	 * get sibling of position
	 * 
	 * @param p position
	 * @return sibling of position
	 */
	@Override
	public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
		return tree.sibling(p);
	}

	/**
	 * in order iterable of tree
	 * 
	 * @return in order iterable
	 */
	@Override
	public Iterable<Position<Entry<K, V>>> inOrder() {
		return tree.inOrder();
	}

	/**
	 * rotate p
	 * 
	 * @param p to rotate
	 */
	protected void rotate(Position<Entry<K, V>> p) {
		tree.rotate(p);
	}

	/**
	 * restructure x
	 * 
	 * @param x to restructure
	 * @return restructured x
	 */
	protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
		return tree.restructure(x);
	}

	/**
	 * get height of p
	 * 
	 * @param p to get height of
	 * @return height
	 */
	public int getProperty(Position<Entry<K, V>> p) {
		return tree.getProperty(p);
	}

	/**
	 * set the property
	 * 
	 * @param p     to set the property for
	 * @param value of property
	 */
	public void setProperty(Position<Entry<K, V>> p, int value) {
		tree.setProperty(p, value);
	}
}