package deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] dequeRepresentation;
    private int nextFirst, nextLast;
    private double minUsageFactor;
    private int size;

    private class DequeIterator implements Iterator<T> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public T next() {
            return dequeRepresentation[i++];
        }
    }

    public ArrayDeque() {
        dequeRepresentation = (T[]) new Object[8];
        nextFirst = (dequeRepresentation.length / 2) - 1;
        nextLast = dequeRepresentation.length / 2;
    }

    @Override
    public void addFirst(T item) {
        if (nextFirst < 0) {
            resize((int) (dequeRepresentation.length * 1.5));
        }
        dequeRepresentation[nextFirst--] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (nextLast >= dequeRepresentation.length) {
            resize((int) (dequeRepresentation.length * 1.5));
        }
        dequeRepresentation[nextLast++] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = nextFirst + 1; i < nextLast; i++) {
            System.out.print(dequeRepresentation[i] + (i == nextLast - 1 ? "\n" : " "));
        }
    }

    @Override
    public T get(int index) {
        int i = (dequeRepresentation.length - size) / 2 - 1 + index;
        return index >= size ? null : dequeRepresentation[i];
    }

    @Override
    public T removeLast() {
        var last = dequeRepresentation[--nextLast];
        dequeRepresentation[nextLast] = null;
        size--;
        double usageFactor = (float) size / dequeRepresentation.length;
        if (usageFactor < minUsageFactor) {
            resize((int) (dequeRepresentation.length / 1.5));
        }
        return last;
    }

    @Override
    public T removeFirst() {
        var first = dequeRepresentation[++nextFirst];
        dequeRepresentation[nextFirst] = null;
        size--;
        double usageFactor = (float) size / dequeRepresentation.length;
        if (usageFactor < minUsageFactor) {
            resize((int) (dequeRepresentation.length / 1.5));
        }
        return first;
    }

    private void resize(int newLength) {
        T[] newArr = (T[]) new Object[newLength];
        int newFirst = (newLength - size) / 2 - 1;
        int newLast = size + (newLength - size) / 2;
        if (newLength <= 8) {
            minUsageFactor = 0.0;
        } else if (newLength < 16) {
            minUsageFactor = 0.125;
        } else {
            minUsageFactor = 0.25;
        }
        System.arraycopy(dequeRepresentation, 0, newArr, newFirst, size);
        dequeRepresentation = newArr;
        nextFirst = newFirst;
        nextLast = newLast;
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> x = (ArrayDeque<T>) o;
            if (size() == x.size()) {
                for (int i = 0; i < size(); i++) {
                    if (!x.get(i).equals(get(i))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
