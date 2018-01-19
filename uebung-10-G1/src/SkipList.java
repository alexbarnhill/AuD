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
            SkipListNode currentEl = head.next[MAX_LEVELS - 1];
            for(int i = MAX_LEVELS - 1; i >= 0; i--) {
                SkipListNode first = head.next[i];

                // Checkout the elements at this level
                for(int j = 0; j < size(); j++) {
                    // get the first element at this level
                    // Is this level empty and should it be inserted?
                    if(first == null && i > maxLevel) {
                        break;

                        //Else if this level isn't empty
                    } else if (first != null) {


                        // Is the currentNode node to insert smaller?
                        if(toAdd.compareTo((E) currentEl.value) < 0 && i <= maxLevel) {
                            SkipListNode newEl = new SkipListNode(toAdd, i);
                            head.next[i] = newEl;
                            newEl.next[i] = currentEl;
                            added = true;

                            // else if its smaller and we're too high
                        } else if(toAdd.compareTo((E) currentEl.value) < 0 && i > maxLevel) {
                            break;

                            // else if its bigger than the current element
                        } else{
                            // is it smaller than the next?
                            if(currentEl.next[i] != null && toAdd.compareTo((E) currentEl.next[i].value) < 0 && i <= maxLevel) {
                                // If we're on a proper level
                                    SkipListNode newEl = new SkipListNode(toAdd, i);
                                    SkipListNode newNext = currentEl.next[i];
                                    currentEl.next[i] =  newEl;
                                    newEl.next[i] = newNext;
                                    added = true;
                                // else if the spot is correct, but the level is too high
                            } else if(currentEl.next[i] != null && toAdd.compareTo((E) currentEl.next[i].value) < 0 && i > maxLevel) {
                                break;
                                // to next lower level;

                                // else if it is larger than the current Element and the next element
                            } else if (currentEl.next[i] != null && toAdd.compareTo((E) currentEl.next[i].value) > 0 ){
                                currentEl = currentEl.next[i];
                                continue;
                            } else  if(currentEl.next[i] == null && i <= maxLevel) {
                                SkipListNode newEl = new SkipListNode(toAdd, i);
                                currentEl.next[i] = newEl;
                                added = true;
                                // Right spot, no next, still to high
                            } else if (currentEl.next[i] == null && toAdd.compareTo((E) currentEl.value) > 0 && i > maxLevel) {
                                break;
                            }


                        }

                        // else if this row is empty and within max level range
                    } else if(first == null && i <= maxLevel) {
                        SkipListNode newEl = new SkipListNode(toAdd, i);
                        head.next[i] = newEl;
                        added = true;

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
