import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
    private T[] data;
    private int size;

    public MyArrayList() {
        data = (T[]) new Object[500];
        size = 0;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 500) {
            throw new IllegalStateException();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    public void add(T element) {
        if (size == 500) {
            throw new IllegalStateException();
        }
        data[size] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T result = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        return containsHelper(element, 0);
    }

    private boolean containsHelper(T element, int index) {
        if (index >= size) {
            return false;
        }
        if (data[index].equals(element)) {
            return true;
        }
        return containsHelper(element, index + 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this);
    }
}
