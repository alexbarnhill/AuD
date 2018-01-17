import java.util.Collection;

public class SkipList<E extends Comparable<? super E>> extends AbstractSkipList {

    @Override
    protected int getRandomLevel() {
        int level = 0;
        while(!super.nextBoolean() && level < super.MAX_LEVELS) {
            level++;
        }

        return level;
    }

    @Override
    public int size() {
        int size = 0;
        if(head.next[0] == null) {
            return size;
        } else {
            size = head.level;

        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        if(head.next[0] == null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        Comparable toAdd = (Comparable) o;
        SkipListNode first = head.next[super.MAX_LEVELS - 1];

    }


    @Override
    public boolean add(Object o) {
        Comparable toAdd = (Comparable) o;
        if(contains(o)) {
            return false;
        }

        int maxLevel = getRandomLevel();
        if(head.next[maxLevel] == null) {
            SkipListNode skipListNode = new SkipListNode(o, maxLevel);
            skipListNode.next[maxLevel] = null;
        }


        return true;
    }

    @Override
    public boolean remove(Object o) {

        if(contains(o)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for(Object o : c) {
            if(!add(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for(int i = 0; i < super.MAX_LEVELS; i++) {
            head.next[0] = null;
        }
    }

    @Override
    public boolean removeAll(Collection c) {
        for(Object o : c) {
            if (!remove(0)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        for(Object o : c) {
            if(!contains(o)) {
                return false;
            }
        }

        return true;
    }
}
