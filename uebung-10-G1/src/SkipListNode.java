public class SkipListNode<E> {
    public final E value;
    public final int level;
    public final SkipListNode<E>[] next;

    @SuppressWarnings("unchecked")
    public SkipListNode(E val, int level) {
        this.value = val;
        this.level = level;
        next = new SkipListNode[SkipList.MAX_LEVELS];
    }

    @Override
    public String toString() {
        return value.toString() + "(" + level + ")";
    }
}