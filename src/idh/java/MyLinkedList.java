package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

    /**
     * We only need to store the very first element of our list,
     * because it will know whether there is a next element.
     */
    ListElement first;

    // ...

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        int i = index;
        for (T element : c) {
            add(i, element);
            i++;
        }

        return !c.isEmpty();
    }

    // ...

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        return new ListIterator<T>() {
            ListElement previous = null;
            ListElement next = getElement(index);
            int currentIndex = index;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                previous = next;
                T ret = next.value;
                next = next.next;
                currentIndex++;
                return ret;
            }

            @Override
            public boolean hasPrevious() {
                return previous != null;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                next = previous;
                T ret = previous.value;
                previous = getElement(currentIndex - 2);
                currentIndex--;
                return ret;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (previous == null) {
                    throw new IllegalStateException("remove() can only be called after next() or previous()");
                }
                if (currentIndex == index) {
                    // Removing the element at the specified index
                    MyLinkedList.this.remove(index);
                    currentIndex--;
                } else {
                    // Removing the last element returned by next() or previous()
                    previous.next = next;
                }
                previous = null;
            }

            @Override
            public void set(T e) {
                if (previous == null) {
                    throw new IllegalStateException("set() can only be called after next() or previous()");
                }
                next.value = e;
            }

            @Override
            public void add(T e) {
                ListElement newElement = new ListElement(e);
                if (previous == null) {
                    // Adding at the beginning of the list
                    newElement.next = first;
                    first = newElement;
                } else {
                    // Adding in the middle of the list
                    newElement.next = next;
                    previous.next = newElement;
                }
                previous = newElement;
                currentIndex++;
            }
        };
    }

    // ...

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Invalid index range: " + fromIndex + " to " + toIndex);
        }

        MyLinkedList<T> sublist = new MyLinkedList<>();
        ListElement current = getElement(fromIndex);
        while (current != null && fromIndex < toIndex) {
        	sublist.add(current.value);
        	current = current.next;
        	fromIndex++;
        	}
        return sublist;
    }

    // ...
}
