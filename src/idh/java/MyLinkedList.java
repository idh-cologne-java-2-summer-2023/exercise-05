package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, because it will now
	 * whether there is a next element.
	 */
	ListElement first;

	@Override
	// edited
	public int size() {
		int size = 0;
		ListElement current = first;
		while (current != null) {
			size++;
			current = current.next;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	// edited
	public boolean contains(Object o) {
		ListElement current = first;
		while (current != null) {
			if (current.value.equals(o)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ListElement next = first;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				T ret = next.value;
				next = next.next;
				return ret;
			}

		};
	}

	@Override
	// edited
	public Object[] toArray() {
		Object[] array = new Object[size()];
		int index = 0;
		ListElement current = first;
		while (current != null) {
			array[index++] = current.value;
			current = current.next;
		}
		return array;
	}

	@Override
	public <E> E[] toArray(E[] a) {
		if (a.length < size()) {
			E[] es = (E[]) new Object[size()];
			a = es;
		}
		int i = 0;
		for (T t : this) {
			E t2 = (E) t;
			a[i++] = t2;
		}
		return a;
	}

	@Override
	public boolean add(T e) {
		ListElement newListElement = new ListElement(e);
		if (first == null)
			first = newListElement;
		else
			last().next = newListElement;
		return true;
	}

	@Override
	// edited
	public boolean remove(Object o) {
		if (first == null) {
			return false;
		}
		if (first.value.equals(o)) {
			first = first.next;
			return true;
		}
		ListElement current = first;
		while (current.next != null) {
			if (current.next.value.equals(o)) {
				current.next = current.next.next;
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c)
			this.add(t);
		return true;
	}

	@Override
	// edited
	public boolean addAll(int index, Collection<? extends T> c) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		int i = index;
		for (T element : c) {
			add(i++, element);
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean r = true;
		for (Object o : c)
			r = r || this.remove(o);
		return r;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		first = null;
	}

	@Override
	public T get(int index) {
		return getElement(index).value;
	}

	@Override
	// edited
	public T set(int index, T element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListElement current = getElement(index);
		T oldValue = current.value;
		current.value = element;
		return oldValue;
	}

	@Override
	// edited
	public void add(int index, T element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			ListElement newElement = new ListElement(element);
			newElement.next = first;
			first = newElement;
		} else {
			ListElement previous = getElement(index - 1);
			ListElement newElement = new ListElement(element);
			newElement.next = previous.next;
			previous.next = newElement;
		}
	}

	@Override
	// edited
	public T remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			T value = first.value;
			first = first.next;
			return value;
		} else {
			ListElement previous = getElement(index - 1);
			ListElement current = previous.next;
			T value = current.value;
			previous.next = current.next;
			return value;
		}
	}

	@Override
	// edited
	public int indexOf(Object o) {
		int index = 0;
		ListElement current = first;
		while (current != null) {
			if (current.value.equals(o)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	@Override
	// edited
	public int lastIndexOf(Object o) {
		int index = 0;
		int lastIndex = -1;
		ListElement current = first;
		while (current != null) {
			if (current.value.equals(o)) {
				lastIndex = index;
			}
			index++;
			current = current.next;
		}
		return lastIndex;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new ListIterator<T>() {

			ListElement previous = null;
			ListElement next = first;
			int index;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				previous = next;
				T ret = next.value;
				next = next.next;
				index++;
				return ret;
			}

			@Override
			public boolean hasPrevious() {
				return false;
			}

			@Override
			public T previous() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextIndex() {
				return index + 1;
			}

			@Override
			public int previousIndex() {
				return index - 1;
			}

			@Override
			public void remove() {
				previous.next = next.next;
			}

			@Override
			public void set(T e) {
				next.value = e;
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();
			}

		};
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	private class ListElement {
		T value;
		ListElement next;

		ListElement(T value) {
			this.value = value;
		}
	}

	/**
	 * Internal method that iterates over the list, returning the last element
	 * (i.e., the one whose next field is null)
	 * 
	 * @return
	 */
	private ListElement last() {
		if (first == null)
			return null;
		ListElement current = first;

		while (current.next != null) {
			current = current.next;
		}
		return current;
	}

	/**
	 * Internal method to get the list element (not the value) of the list at the
	 * specified index position.
	 * 
	 * @param index
	 * @return
	 */
	private ListElement getElement(int index) {
		if (isEmpty())
			return null;
		ListElement current = first;
		while (current != null) {
			if (index == 0)
				return current;
			index--;
			current = current.next;
		}
		return null;
	}

	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
	}
}
