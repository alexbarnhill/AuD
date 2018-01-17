import java.util.LinkedList;

public class BinTreeNode<T extends Comparable<? super T>> extends AbstractBinTreeNode {

    public BinTreeNode(Comparable value) {
        super(value);
        super.left = null;
        super.parent = null;
        super.right = null;
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
        } else if (value.compareTo(this.value) < 0) {
            if(this.left != null && value.compareTo(this.left.value) == 0) {
                return this.left;
            }
            if(this.parent != null && value.compareTo(this.parent.value) == 0) {
                return this.parent;
            }
            if(this.parent == null) {
                if(this.left != null) {
                    return this.left.findNode(value);
                } else {
                    return this;
                }
            } else {
                if(this.value.compareTo(this.parent.value) < 0) {
                    if(this.left == null) {
                        return this;
                    } else {
                        return this.left.findNode(value);
                    }
                } else {
                    if(value.compareTo(this.parent.value) < 0) {
                        return this.parent.findNode(value);
                    } else if (value.compareTo(this.parent.value) > 0) {
                        if(this.left == null) {
                            return this;
                        } else {
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
        if(this.right == null && this.left == null && this.parent == null) {
            return null;
        }
        if(this.right == null && this.left == null) {
            if(this.isLeftChild()) {
                this.parent.left = null;
            } else if(this.isRightChild()) {
                this.parent.right = null;
            }
            return null;
        }

        if(this.right != null) {
            AbstractBinTreeNode min = this.right.getMinNode();
            this.swap(min);
            if(min == this.right) {

                if(min.right != null) {
                    min.right.parent = this;
                    this.right = min.right;
                } else {
                    this.right = null;
                }


            } else {
                if(min.right != null) {
                    this.right.left = min.right;
                    min.right.parent = this.right.left;
                }
            }

            return this;
        } else {
            this.swap(this.left);
            this.left.parent = this.parent;
            this.parent.left = this.left;
            return this.left;
        }

    }

    @Override
    public boolean delete(Comparable value) {
        AbstractBinTreeNode toDelete = findNode(value);
        if(toDelete.value.compareTo(value) == 0) {
            toDelete.deleteNode();
            return true;
        }

        return false;

    }

    @Override
    public LinkedList traverse(TreeTraversalOrderType traversalOrder) {
        LinkedList<Comparable> l = new LinkedList<>();
        if(traversalOrder == TreeTraversalOrderType.PRE) {
            l.add(this.value);
            if(this.left != null) {
                LinkedList<Comparable> left = this.left.traverse(TreeTraversalOrderType.PRE);
                for(Comparable item : left) {
                    l.add(item);
                }
            }

            if (this.right != null) {
                LinkedList<Comparable> right = this.right.traverse(TreeTraversalOrderType.PRE);
                for(Comparable item : right) {
                    l.add(item);
                }

            }
        } else  if (traversalOrder == TreeTraversalOrderType.IN) {
            if(this.left != null) {
                LinkedList<Comparable> left = this.left.traverse(TreeTraversalOrderType.IN);
                for(Comparable item : left) {
                    l.add(item);
                }
            }

            l.add(this.value);

            if(this.right != null) {
                LinkedList<Comparable> right = this.right.traverse(TreeTraversalOrderType.IN);
                for(Comparable item : right) {
                    l.add(item);
                }
            }

        } else {
            if(this.left != null) {
                LinkedList<Comparable> left = this.left.traverse(TreeTraversalOrderType.POST);
                for(Comparable item : left) {
                    l.add(item);
                }
            }

            if(this.right != null) {
                LinkedList<Comparable> right = this.right.traverse(TreeTraversalOrderType.POST);
                for(Comparable item : right) {
                    l.add(item);
                }
            }

            l.add(this.value);
        }

        return l;
    }

    @Override
    public AbstractBinTreeNode nextNode() {
        if(this.isLeftChild()) {
            if(this.right == null) {
                return this.parent;
            } else {
                return this.right;
            }

        }

        if(this.right != null) {
            return this.right;
        } else if(this.parent == null) {
            return null;
        } else if((this.value.compareTo(this.parent.value) > 0) && this.isRightChild()) {
            AbstractBinTreeNode p = this.parent;
            while(p != null && p.value.compareTo(this.value) < 0) {
                p = p.parent;
            }

            return p;

        }
        return null;
    }


}
