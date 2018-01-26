import java.util.LinkedList;

public interface RPESorterInterface<T> {
    public LinkedList<T> extract(LinkedList<T> in);

    public LinkedList<T> merge(LinkedList<T> a, LinkedList<T> b);

    LinkedList<T> sort(LinkedList<T> in);
}