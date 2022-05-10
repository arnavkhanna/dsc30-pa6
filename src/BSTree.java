/*
 * Name: TODO
 * PID:  TODO
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Arnav Khanna
 * @since  5/9/22
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = new LinkedList<>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.nelems = 0;
        this.root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }

        if (nelems == 0) {
            root = new BSTNode(null, null, key);
            nelems += 1;
            return true;
        }

        BSTNode node = this.root;
        BSTNode parent = null;
        boolean less = false;

        while (node != null) {
            less = key.compareTo(node.getKey()) < 0;
            if (less) {
                parent = node;
                node = node.getLeft();

            }

            else if (key.compareTo(node.getKey()) == 0) {
                return false;
            }
            else {
                parent = node;
                node = node.getRight();
            }
        }

        if (less) {
            parent.setleft(new BSTNode(null, null, key));
        }
        else {
            parent.setright(new BSTNode(null, null, key));
        }

        nelems += 1;
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        BSTNode node = this.root;
        BSTNode parent = null;

        while (node != null) {
            if (key.compareTo(node.getKey()) < 0) {
                parent = node;
                node = node.getLeft();

            }

            else if (key.compareTo(node.getKey()) == 0) {
                return true;
            }
            else {
                parent = node;
                node = node.getRight();
            }
        }

        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }

        BSTNode node = this.root;
        BSTNode parent = null;

        while (node != null) {
            if (key.compareTo(node.getKey()) < 0) {
                parent = node;
                node = node.getLeft();

            }

            else if (key.compareTo(node.getKey()) == 0) {
                node.dataList.add(data);
            }
            else {
                parent = node;
                node = node.getRight();
            }
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)){
            throw new IllegalArgumentException();
        }
        BSTNode node = this.root;
        BSTNode parent = null;

        while (node != null) {
            if (key.compareTo(node.getKey()) < 0) {
                parent = node;
                node = node.getLeft();

            }

            else if (key.compareTo(node.getKey()) == 0) {
                return node.getDataList();
            }
            else {
                parent = node;
                node = node.getRight();
            }
        }
        return null;
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if (nelems == 0) {
            return -1;
        }
        return findHeightHelper(this.root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }

        int right = findHeightHelper(root.getRight());
        int left = findHeightHelper(root.getLeft());
        return Math.max(right, left);

    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        private Stack<T> stack;
        public BSTree_Iterator() {
            stack = new Stack<>();
            BSTNode node = root;
            while (node != null) {
                stack.push(node.getKey());
                node = node.getLeft();
            }
        }

        public boolean hasNext() {
            return !(stack.isEmpty());
        }

        public T next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }
            return stack.pop();
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
