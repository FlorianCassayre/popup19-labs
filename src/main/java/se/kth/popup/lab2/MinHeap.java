package se.kth.popup.lab2;

import java.util.*;

public class MinHeap<E> {
    private final Comparator<E> comparator;

    private final List<E> list = new ArrayList<>();
    private final Map<E, Integer> positions = new HashMap<>();

    public MinHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i + 1) / 2 - 1;
    }

    public int size() {
        return list.size();
    }

    private void swap(int i, int j) {
        final E temp = list.get(i);

        positions.put(list.get(i), j);
        positions.put(list.get(j), i);

        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void maxHeapify(int i) {
        int smallest;
        while(true) {
            final int l = left(i), r = right(i);
            if(l < list.size() && comparator.compare(list.get(l), list.get(i)) < 0)
                smallest = l;
            else
                smallest = i;
            if(r < list.size() && comparator.compare(list.get(r), list.get(smallest)) < 0)
                smallest = r;
            if(smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else
                break;
        }
    }

    public E peek() {
        return list.get(0);
    }

    public void add(E e) {
        final int i = list.size();
        positions.put(e, i);
        list.add(e);
        decreaseKey(i);
    }

    public E poll() {
        if(list.isEmpty())
            throw new IllegalStateException();
        final E e = list.get(0);
        positions.remove(e);
        final E last = list.get(list.size() - 1);
        if(list.size() > 1) {
            list.set(0, last);
            positions.put(last, 0);
        }
        list.remove(list.size() - 1);
        maxHeapify(0);

        return e;
    }

    private void decreaseKey(int i) {
        while(i > 0 && comparator.compare(list.get(parent(i)), list.get(i)) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void decreaseKeyElement(E e) {
        final int index = positions.get(e);
        decreaseKey(index);
    }

    public void remove(E e) {
        final int index = positions.remove(e);
        final E last = list.remove(list.size() - 1);
        positions.put(last, index);
        if(index == list.size())
            list.add(last);
        else
            list.set(index, last);
        maxHeapify(index);
        decreaseKey(index);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(E e) {
        return positions.containsKey(e);
    }
}
