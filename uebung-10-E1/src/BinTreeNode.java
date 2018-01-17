import java.util.LinkedList;

public class BinTreeNode<T extends Comparable<? super T>> extends AbstractBinTreeNode<T> {

    public BinTreeNode(T value) {
        super(value);
    }

    public BinTreeNode(T value, AbstractBinTreeNode left, AbstractBinTreeNode right) {
        super(value, left, right);
    }

    @Override
    public void addLeft(T value) {
        this.left = new BinTreeNode<>(value);
        this.left.parent = this;
    }

    @Override
    public void addRight(T value) {
        this.right = new BinTreeNode<>(value);
        this.right.parent = this;
    }

    @Override
    public boolean isLeftChild() {
        if(this.getParent() != null && this.getParent().getLeft() != null) {
            return this.getParent().getLeft() == this;
        }
        return false;

    }

    @Override
    public boolean isRightChild() {
        if(this.getParent() != null && this.getParent().getRight() != null) {
            return this.getParent().getRight() == this;
        }
        return false;
    }

    @Override
    public void swap(AbstractBinTreeNode other) {
        T v = this.getValue();
        this.value = (T) other.getValue();
        other.value = v;

    }

    @Override
    public int getHeight() {
        if(this.left == null && this.right == null) {
            return 0;
        } else {

            if(this.getLeft() != null && this.getRight() == null) {
                return 1 + this.left.getHeight();
            } else if(this.getLeft() == null && this.getRight() != null) {
                return 1 + this.getRight().getHeight();
            } else {
                if(this.getLeft().getHeight() > this.getRight().getHeight()) {
                    return 1 + this.getLeft().getHeight();
                }
                return 1 + this.getRight().getHeight();
            }
        }

    }

    @Override
    public AbstractBinTreeNode<T> findNode(T value) {
        if (value.compareTo(this.getValue()) == 0) {
            return this;
        } else if (value.compareTo(this.getValue()) < 0) {
            if(this.getLeft() != null && value.compareTo(this.getLeft().getValue()) == 0) {
                return this.getLeft();
            }
            if(this.getParent() != null && value.compareTo(this.getParent().getValue()) == 0) {
                return this.getParent();
            }
            if(this.getParent() == null) {
                if(this.getLeft() != null) {
                    return this.getLeft().findNode(value);
                } else {
                    return this;
                }
            } else {
                if(this.getValue().compareTo(this.getParent().getValue()) < 0) {
                    if(this.getLeft() == null) {
                        return this;
                    } else {
                        return this.getLeft().findNode(value);
                    }
                } else {
                    if(value.compareTo(this.getParent().getValue()) < 0) {
                        return this.getParent().findNode(value);
                    } else if (value.compareTo(this.getParent().getValue()) > 0) {
                        if(this.getLeft() == null) {
                            return this;
                        } else {
                            return this.getLeft().findNode(value);
                        }
                    }
                }
            }


        } else {
            if(this.getRight() != null && value.compareTo(this.getRight().getValue()) == 0) {
                return this.getRight();
            } else if(this.getRight() != null ){
                return this.getRight().findNode(value);
            } else {
                return this;
            }
        }

        return this;
    }

    @Override
    public boolean contains(T value) {
        if(this.getValue().compareTo(value) == 0) {
            return true;
        }

        AbstractBinTreeNode<T> a = this.findNode(value);
        return value.compareTo(a.getValue()) == 0;
    }

    @Override
    public boolean insert(T value) {

        AbstractBinTreeNode<T> a = this.findNode(value);
        if(value.compareTo(a.getValue()) == 0) {
            return false;
        } else if (value.compareTo(a.getValue()) < 0) {
            a.addLeft(value);
            return true;
        } else {
            a.addRight(value);
            return true;
        }
    }

    @Override
    public AbstractBinTreeNode<T> getMinNode() {
        if(this.getLeft() == null) {
            return this;
        } else {
            return this.getLeft().getMinNode();
        }

    }

    @Override
    public AbstractBinTreeNode<T> getMaxNode() {
        if(this.getRight() == null) {
            return this;
        } else {
            return this.getRight().getMaxNode();
        }
    }

    @Override
    public AbstractBinTreeNode<T> deleteNode() {
        if(this.getRight() == null && this.getLeft() == null && this.getParent() == null) {
            return null;
        }
        if(this.getRight() == null && this.getLeft() == null) {
            if(this.isLeftChild()) {
                this.getParent().left = null;
            } else if(this.isRightChild()) {
                this.getParent().right = null;
            }
            return null;
        }

        if(this.getRight() != null) {
            AbstractBinTreeNode<T> min = this.getRight().getMinNode();
            this.swap(min);
            if(min == this.getRight()) {

                if(min.getRight() != null) {
                    min.getRight().parent = this;
                    this.right = min.getRight();
                } else {
                    this.right = null;
                }


            } else {
                if(min.getRight() != null) {
                    this.getRight().left = min.getRight();
                    min.getRight().parent = this.getRight().getLeft();
                }
            }

            return this;
        } else {
            this.swap(this.getLeft());
            this.getLeft().parent = this.getParent();
            this.getParent().left = this.getLeft();
            return this.getLeft();
        }

    }

    @Override
    public boolean delete(T value) {
        AbstractBinTreeNode<T> toDelete = findNode(value);
        if(toDelete.getValue().compareTo(value) == 0) {
            toDelete.deleteNode();
            return true;
        }

        return false;

    }

    @Override
    public LinkedList<T> traverse(TreeTraversalOrderType traversalOrder) {
        LinkedList<T> l = new LinkedList<>();
        if(traversalOrder == TreeTraversalOrderType.PRE) {
            l.add(this.getValue());
            if(this.getLeft() != null) {
                LinkedList<T> left = this.getLeft().traverse(TreeTraversalOrderType.PRE);
                l.addAll(left);
            }

            if (this.getRight() != null) {
                LinkedList<T> right = this.getRight().traverse(TreeTraversalOrderType.PRE);
                l.addAll(right);

            }
        } else  if (traversalOrder == TreeTraversalOrderType.IN) {
            if(this.getLeft() != null) {
                LinkedList<T> left = this.getLeft().traverse(TreeTraversalOrderType.IN);
                l.addAll(left);
            }

            l.add(this.getValue());

            if(this.getRight() != null) {
                LinkedList<T> right = this.getRight().traverse(TreeTraversalOrderType.IN);
                l.addAll(right);
            }

        } else {
            if(this.getLeft() != null) {
                LinkedList<T> left = this.getLeft().traverse(TreeTraversalOrderType.POST);
                l.addAll(left);
            }

            if(this.getRight() != null) {
                LinkedList<T> right = this.getRight().traverse(TreeTraversalOrderType.POST);
                l.addAll(right);
            }

            l.add(this.getValue());
        }

        return l;
    }

    @Override
    public AbstractBinTreeNode<T> nextNode() {
        if(this.isLeftChild()) {
            if(this.getRight() == null) {
                return this.getParent();
            } else {
                return this.getRight();
            }

        }

        if(this.getRight() != null) {
            return this.getRight();
        } else if(this.getParent() == null) {
            return null;
        } else if((this.getValue().compareTo(this.getParent().getValue()) > 0) && this.isRightChild()) {
            AbstractBinTreeNode<T> p = this.getParent();
            while(p != null && p.value.compareTo(this.getValue()) < 0) {
                p = p.getParent();
            }

            return p;

        }
        return null;
    }


}
