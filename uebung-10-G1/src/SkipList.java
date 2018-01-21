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
        SkipListNode el = null;
        SkipListNode prev = null;
        for(int i = MAX_LEVELS - 1; i >= 0; i--) {
            SkipListNode first = head.next[i];
            if(first != null) {
                // Go through the elements in the row
                for(int j = 0; j < size; j++) {

                    if(el == null) {
                        el = first;
                        prev = head;
                    }

                    if(el != null && el.level != i) {
                        el = prev.next[i];
                    }

                    // If the node was found,
                    if(el != null && toFind.compareTo((E) el.value) == 0 ) {
                        return true;
                        // if the searched-for element is larger than the current value
                    } else if(el != null && toFind.compareTo((E) el.value) > 0 ) {
                        // if the next element exists
                        if(el.next[i] != null && toFind.compareTo((E) el.next[i].value) >= 0) {
                            prev = el;
                            el = el.next[i];
                            continue;
                            // if not, go to the next level;
                        } else {

                            break;
                        }
                        // if too big
                    } else {
                        el = null;
                        break;
                    }
                }
            }


        }
        return false;
    }


    @Override
    public boolean add(Object o) {
        E toAdd = (E) o;
        if(contains(o)) {
            return false;
        }


        int maxLevel = getRandomLevel();
        // First element in this level
        SkipListNode currentEl = null;
        SkipListNode previousNode = null;
        // All Levels
        SkipListNode newNode = new SkipListNode((E) toAdd, maxLevel);
        SkipListNode curr = null;


        // The list is empty
        if(size() == 0) {
            for(int i = maxLevel; i >= 0; i--) {
                this.head.next[i] = newNode;
            }
            size++;
            return true;
        }

        for(int level = MAX_LEVELS -1; level >= 0; level--) {
            if(head.next[level] == null && level > maxLevel) {
                continue;
            } else if (head.next[level] == null && level <= maxLevel) {
                head.next[level] = newNode;
                continue;
            }
            if(head.next[level] != null && toAdd.compareTo((E) head.next[level].value) < 0 && level > maxLevel) { continue;}

            if(curr == null) {curr = head.next[level];}
            if(head.next[level] != null && toAdd.compareTo((E) head.next[level].value) < 0 && level <= maxLevel) {
                head.next[level] = newNode;
                newNode.next[level] = curr;
                curr = null;
                continue;
            }

            for(int element = 0; element <= size / 2; element++) {
                // compare the first element to the element which is to be added;
                int comparison = toAdd.compareTo((E) curr.value);

                // if larger
                if(comparison > 0) {
                    // can skip?
                    if(curr.next[level] != null && toAdd.compareTo((E) curr.next[level].value) > 0) {
                        curr = curr.next[level];
                        continue;



                        // if cant skip
                    } else if(curr.next[level] == null || (curr.next[level] != null && toAdd.compareTo((E) curr.next[level].value) < 0)) {
                        if(level <= maxLevel) {
                            newNode.next[level] = curr.next[level];
                            curr.next[level] = newNode;
                            break;
                        } else {
                            break;
                        }
                    }

                    // if smaller
                } else if(comparison < 0){
                    // if it is smaller and the first element in the row
                    if(curr == head.next[level] && level <= maxLevel) {
                        newNode.next[level] = curr;
                        head.next[level] = newNode;
                        break;
                    } else {
                        curr = null;
                        break;
                    }
                } else {
                    curr = null;
                    break;

                }
            }
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        E toRemove = (E) o;
        if(!contains(toRemove)) {
            return false;
        }
        SkipListNode curr = null;
        if(toRemove.compareTo((E) new Integer(13518)) == 0) {
            System.out.println(" Element : 13518 with size = " + size());
        }
        for(int level = MAX_LEVELS - 1; level >= 0; level--) {
            if(head.next[level] == null) {continue;}
            boolean finished = false;
            for(int element = 0; element <= size / 2; element++) {
                if(curr == null) {
                    curr = head.next[level];
                }

                int comparison = toRemove.compareTo((E) curr.value);
                // if first node is the one to remove
                if(comparison == 0 && curr == head.next[level]) {
                    head.next[level] = curr.next[level];
                    removed = true;
                    curr = null;
                    break;
                    // if it's larger
                } else if(comparison > 0) {
                    // if it is the next element
                    if(curr.next[level] != null && toRemove.compareTo((E) curr.next[level].value) == 0) {
                        curr.next[level] = curr.next[level].next[level];
                        removed = true;
                        break;
                    } else if(curr.next[level] != null && toRemove.compareTo((E) curr.next[level].value) > 0) {
                        curr = curr.next[level];
                        continue;
                    } else {
                        break;
                    }
                } else if(comparison < 0) {
                    curr = null;
                    break;

                }

            }
        }

        if(removed) {size--;}
        return removed;
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
            head.next[i] = null;
        }
        size = 0;
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
