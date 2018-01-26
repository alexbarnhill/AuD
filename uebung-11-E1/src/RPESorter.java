import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class RPESorter<T> implements RPESorterInterface<T>{
    public Comparator<T> comparator;

    public RPESorter(Comparator<T> c) {
        comparator = c;
    }

    @Override
    public LinkedList<T> extract(LinkedList<T> in) {
        LinkedList<T> list = new LinkedList<>();
        list.add(in.get(0));

        T first = (T) in.remove(0);
        int counter = 0;

        for(Iterator i = in.iterator(); i.hasNext();) {
            T next = (T) i.next();
            if(comparator.compare(next, list.get(counter)) >= 0) {
                list.add(next);
                counter++;
            }
        }

        in.add(0, first);

        return list;
    }

    @Override
    public LinkedList<T> merge(LinkedList<T> a, LinkedList<T> b) {
        int counter = 0;
        if(b.size() == 0) {
            b.addAll(a);
            return b;
        }
        while(a.size() > 0) {
            T aEl = a.get(0);
            for(int i = 0; i < b.size(); i++) {
                if(comparator.compare(aEl, b.get(i)) <= 0) {
                    b.add(i, aEl);
                    a.remove(0);
                    break;
                }
            }
        }
        return b;
    }

    @Override
    public LinkedList<T> sort(LinkedList<T> in) {
        LinkedList<T> result = new LinkedList<>();
        int inSize = in.size();
        while(result.size() < inSize) {
            LinkedList<T> extract = extract(in);
            in.removeAll(extract);
            result = merge(extract, result);

        }

        return result;
    }
}
