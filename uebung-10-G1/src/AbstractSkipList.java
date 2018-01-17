import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractSkipList<E> implements Set<E> {
    public static final int MAX_LEVELS = 32;

    /**
     * The head element
     */
    protected SkipListNode<E> head = new SkipListNode<E>(null, MAX_LEVELS - 1);

    /**
     * Use this as a source for random numbers!
     */
    protected boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    private static final java.util.Random RANDOM = new java.util.Random(4711);

    /**
     * Determines a random level. Higher levels are increasingly unlikely, following these rules:
     * <ul>
     * <li>Level 0 is always reached.</li>
     * <li>Level 1 is reached 50% of the time</li>
     * <li>Level 2 is reached in 50% of the cases, where level 1 was reached</li>
     * <li>Level 3 is reached in 50% of the cases, where level 2 was reached</li>
     * <li>... and so on</li>
     * </ul>
     *
     * @return a number between 0 and MAX_LEVELS-1
     */
    protected abstract int getRandomLevel();

    /** Method not required for our implementation. */
    @Override
    public final boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /** Method not required for our implementation. */
    @Override
    public final Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /** Method not required for our implementation. */
    @Override
    public final <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    /** Method not required for our implementation. */
    @Override
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int l = MAX_LEVELS - 1; l >= 0; l--) {
            if (head.next[l] == null) {
                continue;
            }
            sb.append(String.format("%02d\t\t", l));
            SkipListNode<E> curr = head.next[l];
            boolean first = true;
            while (curr != null) {
                if (!first) {
                    sb.append(" -> ");
                }
                sb.append(curr.value.toString());
                first = false;
                curr = curr.next[l];
            }
            sb.append(String.format("%n", new Object[0]));
        }
        return sb.toString();
    }
}