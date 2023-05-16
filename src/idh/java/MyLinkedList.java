package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {
	
//Verstehe nicht wie ich den Teil machen soll :/
	/**
	 * We only need to store the very first element of our list,
	 * because it will now whether there is a next element.
	 */
	ListElement first;


	@Override
	public int size() {
		int size = 0;
		for (@SuppressWarnings("unused") T t : this) {
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		for (T t : this) {
			if (t.equals(o)) {
				return true;
			}
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
	public Object[] toArray() {
		Object[] array = new Object[size()];
		int i = 0;
		for (T t : this) {
			array[i++] = t;
		}
		return array;
	}

	@Override
	public <E> E[] toArray(E[] a) {
		if (a.length < size()) {
			a = (E[]) new Object[size()];
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
		if (first == null)
			first = newListElement;
		else
			last().next = newListElement;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		ListElement previous = null;
		ListElement current = first;
		while(current != null) {
			if (o.equals(current.value)) {
				if (previous == null) {
					first = current.next;
				} else {
					previous.next = current.next;
				}
				return true;
			}
			previous = current;
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (! contains(o))
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
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Implement!
		return false;
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
	public T set(int index, T element) {
		ListElement e = getElement(index);
		T oldValue = e.value;
		e.value = element;
		return oldValue;
	}

	@Override
	public void add(int index, T element) {
		ListElement previous = getElement(index - 1);
		ListElement current = previous.next;
		ListElement newListElement = new ListElement(element);
		previous.next = newListElement;
		newListElement.next = current;
	}

	@Override
	public T remove(int index) {
		ListElement previous = getElement(index - 1);
		ListElement current = previous.next;
		previous.next = current.next;
		return current.value;
	}

	@Override
	public int indexOf(Object o) {
		ListElement current = first;
		int index = 0;
		while (current != null) {
			if (o.equals(current.value)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		ListElement current = first;
		int index = -1;
		int counter = 0;
		while (current != null) {
			if (o.equals(current.value)) {
				index = counter;
			}
			counter++;
			current = current.next;
		}
		return index;
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
				return index+1;
			}

			@Override
			public int previousIndex() {
				return index-1;
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
	 * Internal method that iterates over the list, returning the last element (i.e., the one whose next field is null)
	 * @return
	 */
	private ListElement last() {
		if (first == null)
			return null;
		ListElement current = first;

		while(current.next != null) {
			current = current.next;
		}
		return current;
	}

	/**
	 * Internal method to get the list element (not the value) of the list at the specified index position.
	 * @param index
	 * @return
	 */
	private ListElement getElement(int index) {
		if (isEmpty())
			return null;
		ListElement current = first;
		while(current != null) {
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
