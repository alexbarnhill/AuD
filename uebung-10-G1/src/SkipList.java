import java.util.Collection;

public class SkipList<E extends Comparable<? super E>> extends AbstractSkipList<E> {
    private int size = 0;

    @Override
    public int getRandomLevel() {
        int level = 0;
        while(!super.nextBoolean() && level < MAX_LEVELS) {
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
        return head.next[0] == null;

    }

    @Override
    public boolean contains(Object o) {
        E toFind = (E) o;
        SkipListNode<E> el = null;
        SkipListNode<E> prev = null;
        for(int i = MAX_LEVELS - 1; i >= 0; i--) {
            SkipListNode<E> first = head.next[i];
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
                    if(el != null && toFind.compareTo(el.value) == 0 ) {
                        return true;
                        // if the searched-for element is larger than the current value
                    } else if(el != null && toFind.compareTo(el.value) > 0 ) {
                        // if the next element exists
                        if(el.next[i] != null && toFind.compareTo( el.next[i].value) >= 0) {
                            prev = el;
                            el = el.next[i];

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
    public boolean add(E o) {
        if(contains(o)) {
            return false;
        }


        int maxLevel = getRandomLevel();

        SkipListNode<E> newNode = new SkipListNode<E>(o, maxLevel);
        SkipListNode<E> curr = null;


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
            if(head.next[level] != null && o.compareTo((E) head.next[level].value) < 0 && level > maxLevel) { continue;}

            if(curr == null) {curr = head.next[level];}
            if(head.next[level] != null && o.compareTo((E) head.next[level].value) < 0 && level <= maxLevel) {
                head.next[level] = newNode;
                newNode.next[level] = curr;
                curr = null;
                continue;
            }

            for(int element = 0; element <= size / 2; element++) {
                // compare the first element to the element which is to be added;
                int comparison = o.compareTo((E) curr.value);

                // if larger
                if(comparison > 0) {
                    // can skip?
                    if(curr.next[level] != null && o.compareTo((E) curr.next[level].value) > 0) {
                        curr = curr.next[level];




                        // if cant skip
                    } else if(curr.next[level] == null || (curr.next[level] != null && o.compareTo((E) curr.next[level].value) < 0)) {
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
        SkipListNode<E> curr = null;
        for(int level = MAX_LEVELS - 1; level >= 0; level--) {
            if(head.next[level] == null) {continue;}
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
                    if(curr.next[level] != null && toRemove.compareTo(curr.next[level].value) == 0) {
                        curr.next[level] = curr.next[level].next[level];
                        removed = true;
                        break;
                    } else if(curr.next[level] != null && toRemove.compareTo(curr.next[level].value) > 0) {
                        curr = curr.next[level];
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
    public boolean addAll(Collection<? extends E> c) {
        for(E item : c) {
            if(!add(item)){return false;}

        }

        size += c.size();
        return true;
    }

    @Override
    public void clear() {
        for(int i = 0; i < MAX_LEVELS; i++) {
            head.next[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] a = c.toArray();
        for (Object anA : a) {
            if (contains(anA) && !remove(anA)) {
                return false;
            }

        }

        size -= c.size();
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        Object[] a = c.toArray();
        for (Object anA : a) {
            if (!contains(anA)) {
                return false;
            }

        }

        return true;
    }
}
