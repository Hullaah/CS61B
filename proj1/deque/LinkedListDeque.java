package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T element;
        Node next;
        Node prev;
        
        Node(T element, Node prev, Node next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        T get(int index) {
            if (index == 0) {
                return this.element;
            } else {
                return this.next == sentinel ? null : this.next.get(index - 1);
            }
        }
    }

    private class DequeIterator implements Iterator<T> {
        Node nextNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return nextNode != sentinel;
        }

        @Override
        public T next() {
            var node = nextNode;
            nextNode = nextNode.next;
            return  node.element;
        }
    }

    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        var node = new Node(item, null, null);
        node.next = sentinel.next;
        node.prev = sentinel;
        node.next.prev = node;
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        var node = new Node(item, null, null);
        node.prev = sentinel.prev;
        node.next = sentinel;
        node.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (var x = sentinel.next; x != sentinel; x = x.next) {
            System.out.print(x.next == sentinel ? x.element + "\n" : x.element + " ");
        }
    }

    @Override
    public T get(int index) {
        var ptr = sentinel.next;
        int i = 0;
        while (i < index && ptr != sentinel) {
            i++;
            ptr = ptr.next;
        }
        return ptr == sentinel ? null : ptr.element;
    }

    @Override
    public T removeLast() {
        var last = sentinel.prev;
        T lastElement = last.element;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        last = null;
        size--;
        return lastElement;
    }

    @Override
    public T removeFirst() {
        var first = sentinel.next;
        T firstElement = first.element;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first = null;
        size--;
        return firstElement;
    }
    
    public T getRecursive(int index) {
        return  sentinel.next.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

//    public static void main(String[] args) {
//        LinkedListDeque
//    }
}
