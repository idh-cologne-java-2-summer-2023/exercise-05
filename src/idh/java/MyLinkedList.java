import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

	private ListElement first;
	private ListElement last;
	private int size;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
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
		Object[] array = new Object[size];
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
		if (a.length < size) {
			a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		int index = 0;
		for (T t : this) {
			a[index++] = (E) t;
		}
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	@Override
	public boolean add(T e) {
		ListElement newElement = new ListElement(e);
		if (isEmpty()) {
			first = newElement;
			last = newElement;
		} else {
			last.next = newElement;
			last = newElement;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (isEmpty()) {
			return false;
		}

		if (first.value.equals(o)) {
			first = first.next;
			if (first == null) {
				last = null;
			}
			size--;
			return true;
		}

		ListElement previous = first;
		ListElement current = first.next;
		while (current != null) {
			if (current.value.equals(o)) {
				previous.next = current.next;
				if (current == last) {
					last = previous;
				}
				size--;
				return true;
			}
			previous = current;
			current = current.next;
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T element : c) {
			add(element);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (c.isEmpty()) {
			return false;
		}

		ListElement newElementsFirst = null;
		ListElement newElementsLast = null;
		for (T element : c) {
			ListElement newElement = new ListElement(element);
			if (newElementsFirst == null) {
				newElementsFirst = newElement;
				newElementsLast = newElement;
			} else {
				newElementsLast.next = newElement;
				newElementsLast = newElement;
			}
		}

		if (index == size) {
			if (isEmpty()) {
				first = newElementsFirst;
			} else {
				last.next = newElementsFirst;
			}
			last = newElementsLast;
		} else if (index == 0) {
			newElementsLast.next = first;
			first = newElementsFirst;
		} else {
			ListElement current = getElement(index - 1);
			ListElement next = current.next;
			current.next = newElementsFirst;
			newElementsLast.next = next;
		}

		size += c.size();
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		Iterator<?> iterator = c.iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			while (remove(element)) {
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		ListIterator<T> iterator = listIterator();
		while (iterator.hasNext()) {
			if (!c.contains(iterator.next())) {
				iterator.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public T get(int index) {
		return getElement(index).value;
	}

	@Override
	public T set(int index, T element) {
		ListElement current = getElement(index);
		T oldValue = current.value;
		current.value = element;
		return oldValue;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == size) {
			add(element);
		} else if (index == 0) {
			ListElement newElement = new ListElement(element);
			newElement.next = first;
			first = newElement;
			size++;
		} else {
			ListElement current = getElement(index - 1);
			ListElement next = current.next;
			ListElement newElement = new ListElement(element);
			current.next = newElement;
			newElement.next = next;
			size++;
		}
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		ListElement removedElement;
		if (index == 0) {
			removedElement = first;
			first = first.next;
			if (first == null) {
				last = null;
			}
		} else {
			ListElement previous = getElement(index - 1);
			removedElement = previous.next;
			previous.next = removedElement.next;
			if (removedElement == last) {
				last = previous;
			}
		}

		size--;
		return removedElement.value;
	}

	@Override
	public int indexOf(Object o) {
		ListElement current = first;
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
		ListElement current = first;
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
			ListElement current = null;
			ListElement previous = null;
			int currentIndex = 0;
			int expectedModCount = size;

			@Override
			public boolean hasNext() {
				return currentIndex < size;
			}

			@Override
			public T next() {
				checkForComodification();
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				previous = current;
				current = (current == null) ? first : current.next;
				currentIndex++;
				return current.value;
			}

			@Override
			public boolean hasPrevious() {
				return currentIndex > 0;
			}

			@Override
			public T previous() {
				checkForComodification();
				if (!hasPrevious()) {
					throw new NoSuchElementException();
				}
				previous = (current == null) ? last : previous.previous;
				current = (current == null) ? last : current.previous;
				currentIndex--;
				return previous.value;
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
				checkForComodification();
				if (current == null) {
					throw new IllegalStateException();
				}
				ListElement next = current.next;
				MyLinkedList.this.remove(current.value);
				if (previous == current) {
					previous = null;
				}
				current = previous;
				currentIndex--;
				expectedModCount = size;
			}

			@Override
			public void set(T e) {
				checkForComodification();
				if (previous == null) {
					throw new IllegalStateException();
				}
				previous.value = e;
			}

			@Override
			public void add(T e) {
				checkForComodification();
				if (current == null) {
					add(e);
				} else {
					MyLinkedList.this.add(currentIndex, e);
					previous = null;
				}
				currentIndex++;
				expectedModCount = size;
			}

			final void checkForComodification() {
				if (expectedModCount != size) {
					throw new ConcurrentModificationException();
				}
			}
		};
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		ListElement start = getElement(fromIndex);
		ListElement end = getElement(toIndex - 1);

		MyLinkedList<T> subList = new MyLinkedList<>();
		subList.first = start;
		subList.last = end;
		subList.size = toIndex - fromIndex;

		return subList;
	}

	private ListElement getElement(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListElement current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private class ListElement {
		T value;
		ListElement next;

		ListElement(T value) {
			this.value = value;
			this.next = null;
		}
	}
}
