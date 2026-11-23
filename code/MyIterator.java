import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<T> {
    private MyArrayList<T> list;
    private int currentIndex;

    public MyIterator(MyArrayList<T> list) {
        this.list = list;
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex < list.size()) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        return list.get(currentIndex - 1);
    }
}
