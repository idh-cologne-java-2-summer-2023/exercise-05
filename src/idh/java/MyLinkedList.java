package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, because it will now
	 * whether there is a next element..
	 */
	ListElement first;

	@Override
	public int size() {
		int counter = 0;
		ListElement current = this.first;
		while (current != null) {
			counter++;
			current = current.next;
		}
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return this.first == null;
	}

	@Override
	public boolean contains(Object o) {
		ListElement current = this.first;
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
			ListElement next = MyLinkedList.this.first;

			@Override
			public boolean hasNext() {
				return this.next != null;
			}

			@Override
			public T next() {
				T ret = this.next.value;
				this.next = this.next.next;
				return ret;
			}

		};
	}

	@Override
	public Object[] toArray() {
		int size = this.size();
		Object[] array = new Object[size];
		ListIterator<T> listIter = this.listIterator();
		int i = 0;
		while (listIter.hasNext()) {
			array[i] = listIter.next();
			i++;
		}
		return array;
	}

	@Override
	public <E> E[] toArray(E[] a) {
		if (a.length < this.size()) {
			a = (E[]) new Object[this.size()];
		}
		int i = 0;
		for (T t : this) {
			a[i++] = (E) t;
		}
		return a;
	}

	@Override
	public boolean add(T e) {
		ListElement newListElement = new ListElement(e);
		if (this.first == null) {
			this.first = newListElement;
		} else {
			this.last().next = newListElement;
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		ListElement current = this.first;
		ListElement prev = null;
		while (current != null) {
			if (current.value.equals(o)) {
				if (prev == null) {
					this.first = current.next;
				} else {
					prev.next = current.next;
				}
				return true;
			}
			prev = current;
			current = current.next;
		}
		return false;

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!this.contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c) {
			this.add(t);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		for (T t : c) {
			this.add(t);
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean r = true;
		for (Object o : c) {
			r = r || this.remove(o);
		}
		return r;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		this.first = null;
	}

	@Override
	public T get(int index) {
		return this.getElement(index).value;
	}

	@Override
	public T set(int index, T element) {
		ListElement e = this.getElement(index);
		T object = e.value;
		e.value = element;
		return object;
	}

	@Override
	public void add(int index, T element) {
		ListElement newElement = new ListElement(element);
		if (index == 0) {
			newElement.next = this.first;
			this.first = newElement;
		} else {
			ListElement prev = this.getElement(index - 1);
			newElement.next = prev.next;
			prev.next = newElement;
		}
	}

	@Override
	public T remove(int index) {
		ListElement prev = this.getElement(index - 1);
		ListElement current = this.getElement(index);
		ListElement nextElement = this.getElement(index + 1);

		if (prev == null) {
			this.first = nextElement;
		} else {
			prev.next = nextElement;
		}
		return current.value;
	}

	@Override
	public int indexOf(Object o) {
		ListElement current = this.first;
		int index = 0;

		while (current != null) {
			if (current.value.equals(o)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		ListElement current = this.first;
		int lastIndex = -1;
		int index = 0;
		while (current != null) {
			if (current.value.equals(o)) {
				lastIndex = index;
			}
			current = current.next;
			index++;
		}
		return lastIndex;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new ListIterator<T>() {

			ListElement previous = null;
			ListElement next = MyLinkedList.this.first;
			int index;

			@Override
			public boolean hasNext() {
				return this.next != null;
			}

			@Override
			public T next() {
				this.previous = this.next;
				T ret = this.next.value;
				this.next = this.next.next;
				this.index++;
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
				return this.index + 1;
			}

			@Override
			public int previousIndex() {
				return this.index - 1;
			}

			@Override
			public void remove() {
				this.previous.next = this.next.next;
			}

			@Override
			public void set(T e) {
				this.next.value = e;
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
		if (this.first == null) {
			return null;
		}
		ListElement current = this.first;

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
		if (this.isEmpty()) {
			return null;
		}
		ListElement current = this.first;
		while (current != null) {
			if (index == 0) {
				return current;
			}
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
