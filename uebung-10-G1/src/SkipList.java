import java.util.Collection;

public class SkipList<E extends Comparable<? super E>> extends AbstractSkipList {
    private int size = 0;


    @Override
    public int getRandomLevel() {
        int level = 0;
        while(!super.nextBoolean() && level < super.MAX_LEVELS) {
            level++;
        }

        return level;
    }

    @Override
    public int size() {
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
        E toFind = (E) o;
        SkipListNode el = head.next[MAX_LEVELS - 1];
        int currentIndex = 0;
        for(int i = MAX_LEVELS - 1; i >= 0 && currentIndex < size; i--) {
            el = head.next[i];
            // Go through the elements in the row
            for(int j = 0; j < size; j++) {
                // If the node was found,
                if(el != null && toFind.compareTo((E) el.value) == 0) {
                    return true;
                    // if the searched-for element is larger than the current value
                } else if(el != null && toFind.compareTo((E) el.value) > 0 ) {
                    // if the next element exists
                    if(el.next != null) {
                        el = el.next[i];
                        continue;
                        // if not, go to the next level;
                    } else {
                        break;
                    }
                }
            }

        }
        return false;
    }


    @Override
    public boolean add(Object o) {
        boolean added = false;
        E toAdd = (E) o;
        if(contains(o)) {
            return added;
        }

        int maxLevel = getRandomLevel();
        // First element in this level
        SkipListNode el = null;
        // All Levels


        // The list is empty
        if(size() == 0) {
            for(int i = maxLevel; i >= 0; i--) {
                SkipListNode newNode = new SkipListNode(toAdd, i);
                this.head.next[i] = newNode;
                added = true;
            }
        } else {
            // for each level below / including the max level of the element
            for(int i = maxLevel; i >= 0; i--) {
                SkipListNode first = head.next[i];

                // for each element in this row
                for(int j = 0; j <= size; j++) {
                    // if this level is empty
                    if(first == null) {
                        SkipListNode newNode = new SkipListNode(toAdd, i);
                        head.next[i] = newNode;
                        added = true;
                        break;
                        // if there is at least one element at this level
                    } else {
                        el = j == 0 ? first : el;
                        // If the element to add is larger than the current element
                        if(toAdd.compareTo((E) el.value) > 0) {
                            // if there is a next element and it is larger than the element to insert
                            if(el.next[i] != null && toAdd.compareTo((E) el.next[i].value) < 0) {
                                SkipListNode newNode = new SkipListNode(toAdd, i);
                                SkipListNode next = el.next[i];
                                newNode.next[i] = next;
                                el.next[i] = newNode;
                                added = true;

                            } else if(el.next[i] == null) {
                                SkipListNode newNode = new SkipListNode(toAdd, i);
                                el.next[i] = newNode;
                                added = true;
                                // if there is a next node and it is smaller than the node to add
                            } else if(el.next[i] != null){
                                el = el.next[i];
                                continue;
                            }

                         // if the element is smaller than the head
                        } else if(toAdd.compareTo((E) first.value) < 0){
                            SkipListNode newNode = new SkipListNode(toAdd, i);
                            newNode.next[i] = el;
                            head.next[i] = newNode;
                            added = true;
                            first = newNode;
                            break;
                        }


                    }
                }
            }
        }
        size++;
        return added;
    }

    @Override
    public boolean remove(Object o) {

        if(contains(o)) {
            return false;
        }

        size--;
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for(Object o : c) {
            if(!add(o)) {
                return false;
            }
        }
        size += c.size();
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

        size -= c.size();
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
