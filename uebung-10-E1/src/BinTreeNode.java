import java.util.LinkedList;

public class BinTreeNode<E> extends AbstractBinTreeNode {

    public BinTreeNode(Comparable value) {
        super(value);
    }

    public BinTreeNode(Comparable value, AbstractBinTreeNode left, AbstractBinTreeNode right) {
        super(value, left, right);
    }

    @Override
    public void addLeft(Comparable value) {
        this.left = new BinTreeNode(value);
        this.left.parent = this;
    }

    @Override
    public void addRight(Comparable value) {
        this.right = new BinTreeNode(value);
        this.right.parent = this;
    }

    @Override
    public boolean isLeftChild() {
        if(this.parent != null && this.parent.left != null) {
            return this.parent.left == this;
        }
        return false;

    }

    @Override
    public boolean isRightChild() {
        if(this.parent != null && this.parent.right != null) {
            return this.parent.right == this;
        }
        return false;
    }

    @Override
    public void swap(AbstractBinTreeNode other) {
        Comparable v = this.value;
        this.value = other.value;
        other.value = v;

    }

    @Override
    public int getHeight() {
        if(this.left == null && this.right == null) {
            return 0;
        } else {

            if(this.left != null && this.right == null) {
                return 1 + this.left.getHeight();
            } else if(this.left == null && this.right != null) {
                return 1 + this.right.getHeight();
            } else {
                if(this.left.getHeight() > this.right.getHeight()) {
                    return 1 + this.left.getHeight();
                }
                return 1 + this.right.getHeight();
            }
        }

    }

    @Override
    protected AbstractBinTreeNode findNode(Comparable value) {
        if (value.compareTo(this.value) == 0) {
            return this;
            // If the value is smaller
        } else if (value.compareTo(this.value) < 0) {
            // if it is the left node
            if(this.left != null && value.compareTo(this.left.value) == 0) {
                return this.left;
            }
            // if it is the parent node
            if(this.parent != null && value.compareTo(this.parent.value) == 0) {
                return this.parent;
            }
            // If there is no parent (at the root)
            if(this.parent == null) {
                if(this.left != null) {
                    return this.left.findNode(value);
                } else {
                    return this;
                }
                // If there is a parent
            } else {
                // If the this node is smaller (and the value is smaller than this;
                if(this.value.compareTo(this.parent.value) < 0) {
                    if(this.left == null) {
                        return this;
                    } else {
                        return this.left.findNode(value);
                    }
                    // if this node is larger than the parent ( and the value is smaller than this)
                } else {
                    // if the value is smaller than the parent
                    if(value.compareTo(this.parent.value) < 0) {
                        // go up one node
                        return this.parent.findNode(value);
                        // if it is larger than the parent but still smaller than this
                    } else if (value.compareTo(this.parent.value) > 0) {
                        // if there is not a left node, return this
                        if(this.left == null) {
                            return this;
                        } else {
                            // return the left node
                            return this.left.findNode(value);
                        }
                    }
                }
            }


        } else {
            if(this.right != null && value.compareTo(this.right.value) == 0) {
                return this.right;
            } else if(this.right != null ){
                return this.right.findNode(value);
            } else {
                return this;
            }
        }

        return this;
    }

    @Override
    public boolean contains(Comparable value) {
        if(this.value.compareTo(value) == 0) {
            return true;
        }

        AbstractBinTreeNode a = this.findNode(value);
        if(value.compareTo(a.value) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Comparable value) {

        AbstractBinTreeNode a = this.findNode(value);
        if(value.compareTo(a.value) == 0) {
            return false;
        } else if (value.compareTo(a.value) < 0) {
            a.addLeft(value);
            return true;
        } else {
            a.addRight(value);
            return true;
        }
    }

    @Override
    public AbstractBinTreeNode getMinNode() {
        if(this.left == null) {
            return this;
        } else {
            return this.left.getMinNode();
        }

    }

    @Override
    public AbstractBinTreeNode getMaxNode() {
        if(this.right == null) {
            return this;
        } else {
            return this.right.getMaxNode();
        }
    }

    @Override
    public AbstractBinTreeNode deleteNode() {

        // Simplest situation -- Node is a leaf;
        if(this.right == null && this.left == null) {
            if(this.isLeftChild()) {
                this.parent.left = null;
            } else if(this.isRightChild()) {
                this.parent.right = null;
            }
            return null;
        }
        // now it gets weird...
        // Lets go right
        if(this.right != null) {
            AbstractBinTreeNode min = this.right.getMinNode();
            min.parent = null;
            this.right.parent = min;
            if(this.left != null) {
                this.left.parent = min;
            }

            return min;
        }

        return this;
    }

    @Override
    public boolean delete(Comparable value) {
        return false;
    }

    @Override
    public LinkedList traverse(TreeTraversalOrderType traversalOrder) {
        return null;
    }

    @Override
    public AbstractBinTreeNode nextNode() {
        return null;
    }

}
