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


        } else {

        }

        return this;
    }

    @Override
    public boolean contains(Comparable value) {
        if(this.value.compareTo(value) == 0) {
            return true;
        } else {
            BinTreeNode l = (BinTreeNode) this.left;
            BinTreeNode r = (BinTreeNode) this.right;
            if(l != null && l.contains(value)) {
                return true;
            } else if(r != null && r.contains(value)) {
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean insert(Comparable value) {
        return false;
    }

    @Override
    public AbstractBinTreeNode getMinNode() {
        AbstractBinTreeNode root = this.getTreeRoot();
        AbstractBinTreeNode l = root.left;
        if(root != null && l != null) {
            while(l.left != null) {
                l = l.left;
            }
        }


        return l == null ? this : l;
    }

    @Override
    public AbstractBinTreeNode getMaxNode() {
        AbstractBinTreeNode root = this.getTreeRoot();
        AbstractBinTreeNode r = root.right;
        if(root != null && r != null) {
            while(r.right != null) {
                r = r.right;
            }
        }


        return r == null ? this : r;
    }

    @Override
    public AbstractBinTreeNode deleteNode() {
        return null;
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

    private AbstractBinTreeNode getTreeRoot() {
        AbstractBinTreeNode root = this;
        AbstractBinTreeNode p = root.parent;
        while(p != null) {
            p = p.parent;
        }
        return p == null ? this : p;
    }
}
