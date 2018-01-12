import java.util.LinkedList;

public abstract class AbstractBinTreeNode<T extends Comparable<? super T>> {
    protected T value;
    protected AbstractBinTreeNode<T> left;
    protected AbstractBinTreeNode<T> right;
    protected AbstractBinTreeNode<T> parent;

    /**
     * Creates a new instance with the given {@code value} but without any parent or children.
     *
     * @param value
     *            - the value to store in this node
     */
    public AbstractBinTreeNode(final T value) {
        if (value == null) {
            throw new NullPointerException("the specified element is null and this tree uses natural ordering");
        }
        this.value = value;
    }

    /**
     * Creates a new instance with the given {@code value} and the {@code left} resp. {@code right} child but without any parent.
     * <p>
     * Note that this constructor might break the search tree property of the current tree. This constructor is <b>not</b> intended to check, react to or correct such a violation!
     * </p>
     *
     * @param value
     *            - the value to store in this node
     * @param left
     *            - the {@code left} child of this node
     * @param right
     *            - the {@code right} child of this node
     */
    public AbstractBinTreeNode(final T value, final AbstractBinTreeNode<T> left, final AbstractBinTreeNode<T> right) {
        if (value == null) {
            throw new NullPointerException("the specified element is null and this tree uses natural ordering");
        }
        this.value = value;
        this.left = left;
        this.right = right;
        if (this.left != null) {
            this.left.parent = this;
        }
        if (this.right != null) {
            right.parent = this;
        }
    }

    /**
     * Returns the current value stored in this node.
     *
     * @return the current value stored in this node
     */
    public final T getValue() {
        return value;
    }

    /**
     * Returns the left child node of this node, or {@code null} if this node does not have a left child.
     *
     * @return the left child node of this node, or {@code null} if this node does not have a left child
     */
    public final AbstractBinTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Returns the right child node of this node, or {@code null} if this node does not have a right child.
     *
     * @return the right child node of this node, or {@code null} if this node does not have a right child
     */
    public final AbstractBinTreeNode<T> getRight() {
        return right;
    }

    /**
     * Returns the parent node of this node, or {@code null} if this node does not have a parent node.
     *
     * @return the parent node of this node, or {@code null} if this node does not have a parent node
     */
    public final AbstractBinTreeNode<T> getParent() {
        return parent;
    }

    /**
     * Creates a new node wrapping {@code value} and makes it the <b>left child</b> node of this node. This node becomes the parent of the new child node.
     * <p>
     * If this node previously had a left child, the old child is <b>replaced</b> by the new node.
     * </p>
     * <p>
     * Note that this method might break the search tree property of the current tree. This method is <b>not</b> intended to check, react to or correct such a violation!
     * </p>
     *
     * @param value
     *            - the value to be stored in a newly created <b>left child</b> node of this node
     */
    public abstract void addLeft(T value);

    /**
     * Creates a new node wrapping {@code value} and makes it the <b>right child</b> node of this node. This node becomes the parent of the new child node.
     * <p>
     * If this node previously had a right child, the old child is <b>replaced</b> by the new node.
     * </p>
     * <p>
     * Note that this method might break the search tree property of the current tree. This method is <b>not</b> intended to check, react to or correct such a violation!
     * </p>
     *
     * @param value
     *            - the value to be stored in a newly created <b>right child</b> node of this node
     */
    public abstract void addRight(T value);

    /**
     * Returns {@code true} if this node has a parent <b>and</b> is the <b>left</b> child of this parent.
     *
     * @return {@code true} if this node has a parent <b>and</b> is its <b>left</b> child
     */
    public abstract boolean isLeftChild();

    /**
     * Returns {@code true} if this node has a parent <b>and</b> is the <b>right</b> child of this parent.
     *
     * @return {@code true} if this node has a parent <b>and</b> is its <b>right</b> child
     */
    public abstract boolean isRightChild();

    /**
     * Unconditionally swaps the values of this and the given {@code other} node. The tree structure itself (i.e. the linking references between parents and children) remains <b>unchanged</b>!
     * <p>
     * Note that this method might break the search tree property of the current tree. This method is <b>not</b> intended to check, react to or correct such a violation!
     * </p>
     *
     * @param other
     *            - the node to switch values with
     */
    public abstract void swap(AbstractBinTreeNode<T> other);

    /**
     * Computes and returns the height of the tree represented by this node.
     * <p>
     * Nodes that have neither left nor right children (i.e. <b>leafs</b>) have height <b>0</b>.
     * </p>
     *
     * @return the height of the tree represented by this node
     */
    public abstract int getHeight();

    /**
     * Returns the actual node in this tree that currently holds a value that equals {@code value} according to its natural ordering if such a node exists.
     * <p>
     * Otherwise, this method returns the potential parent node of a new node wrapping {@code value}. The node returned by this method would become the corresponding parent of the new node if {@code value} is inserted into this tree.
     * </p>
     * <p>
     * This method <b>guarantees <i>O(this.height())</i></b> runtime complexity!
     * </p>
     *
     * @param value
     *            - the value whose wrapping node (if any) or parent node (otherwise) is to be returned
     * @return the node that actually stores the {@code value} in this tree if any, or the potential parent of a new node wrapping {@code value} otherwise
     */
    protected abstract AbstractBinTreeNode<T> findNode(T value);

    /**
     * Returns {@code true} if this tree contains the specified {@code value}. More formally, returns {@code true} if and only if this tree contains at least one node {@code n} such that {@code (value!=null && n.value!=null && value.compareTo(n.value)==0)}.
     * <p>
     * This method <b>guarantees <i>O(this.height())</i></b> runtime complexity!
     * </p>
     *
     * @param value
     *            - value whose presence in this tree is to be tested
     * @return {@code true} if this tree contains the specified value
     */
    public abstract boolean contains(T value);

    /**
     * Adds the specified {@code value} to this tree <i>(in the proper position satisfying the binary search tree conditions)</i> if it is not already present. More formally, adds the specified {@code value} to this tree if this tree contains no value {@code v2} such that (value!=null && v2!=null && value.compareTo(v2)==0). If this tree already contains the value, the call leaves the tree unchanged and returns {@code false}.
     * <p>
     * This method <b>guarantees <i>O(this.height())</i></b> runtime complexity!
     * </p>
     *
     * @param value
     *            - value to be added to this tree
     * @return {@code true} if this tree did not already contain the specified value
     */
    public abstract boolean insert(T value);

    /**
     * Returns the node of this tree containing the <b>smallest</b> value according to the natural ordering of all the values of this tree.
     * <p>
     * This method <b>guarantees <i>O(this.height())</i></b> runtime complexity!
     * </p>
     *
     * @return the node containing the <b>smallest</b> value
     */
    public abstract AbstractBinTreeNode<T> getMinNode();

    /**
     * Returns the node of this tree containing the <b>biggest</b> value according to the natural ordering of all the values of this tree.
     * <p>
     * This method <b>guarantees <i>O(this.height())</i></b> runtime complexity!
     * </p>
     *
     * @return the node containing the <b>biggest</b> value
     */
    public abstract AbstractBinTreeNode<T> getMaxNode();

    /**
     * Removes this node from the current tree and <b>restores</b> the binary search tree property for the entire subtree whose root was represented by this node.
     * <p>
     * More formally, if this node is a leaf then this method removes it from the tree and returns {@code null} (since the new subtree has no root after this operation).
     *
     * If this node has two children, the <b>smallest</b> value of the <b>right</b> child is swapped into this node using the {@link #swap(AbstractBinTreeNode)} method, the corresponding other node is subsequently deleted using this method and this node is returned as the old and new root.
     *
     * Otherwise, the single <b>child becomes the new root</b> of this tree and this method rearranges the necessary references between parent and child, finally returnig this single child being the new root of this tree.
     * </p>
     * <p>
     * This method <b>guarantees <i>O(this.height())</i></b> runtime complexity!
     * </p>
     *
     * @return the new root of the current subtree (i.e. the node taking the place of this node)
     */
    public abstract AbstractBinTreeNode<T> deleteNode();

    /**
     * Deletes the node containig {@code value} (if any) from this tree using the {@link #deleteNode()} method of this class and eventually restores the binary search tree property. Otherwise, this tree remains <b>unchanged</b>.
     *
     * @param value
     *            - the value to remove from this tree
     * @return {@code true} if {@code value} has been succesfully removed from this tree
     */
    public abstract boolean delete(T value);

    /**
     * Visits the nodes of this tree in the given {@link TreeTraversalOrderType} and returns a {@link LinkedList} containing their values collected during this traversal.
     *
     * @param traversalOrder
     * @return a {@link LinkedList} containing all the values of this subtree in the given order of traversal
     */
    public abstract LinkedList<T> traverse(TreeTraversalOrderType traversalOrder);

    /**
     * Searches the <b>entire</b> tree to which this node belongs to (i.e. <b>also traversing in parent direction!</b>) for the node containing the <b>smallest</b> value <b>greater than</b> the value of the current node.
     *
     * @return the node containing the <b>smallest</b> value <b>greater than the value of this</b> node if any, {@code null} otherwise
     */
    public abstract AbstractBinTreeNode<T> nextNode();

    @Override
    public final String toString() {
        return "(" + (left == null ? "" : left) + value + (right == null ? "" : right) + ")";
        // use me instead of the above for more detailed info about references:
        // return "(Id=" + this.hashCode() + "[pId=" + (parent == null ? null : parent.hashCode()) + "]" + ": " + left + ", " + value + ", " + right + ")";
    }
}