package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement first;
	
	
	@Override
	public int size() {
		int i = 0;
		ListElement current = first;
		
		if (current==null)
			return i;
		
		else while(current != null) {
			i++;
			current = current.next;
			
		}
		return i;
	}
	

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		ListElement current = first;
		boolean b = false;
		while (current.next != null) {
			if (current.value == o) {
				b = true;
			}
			current = current.next;
		}
		return b;
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
		Object[] arr = new Object [size()];
		ListElement current = first;
		int i = 0;
		
		while (current != null) {
			arr [i] = current.value;
			current = current.next;
			i++;
		}
		return arr;
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
		ListElement current = first;
		boolean b = false;
		
		while (current != null && b == false) { // Schleife wird abgebrochen sobald o gefunden und gelöscht wird oder wenn es o nicht gibt
			if (current.value == o) {
				current.value = null;
				b = true;
			} else {
				current = current.next;
			}
		}
		return b;
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
		boolean b = false;
		
		for (T t : c) {
			this.set(index, t);
		index++;
		b = true;
	}
		return b;
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
		if(get(index) != null) {
			T b4 = get(index);
			add(index, element);
			return b4;
		} else {
			add(index, element);
			return null;
		}
	}

	@Override
	public void add(int index, T element) {
		
		ListElement newE = new ListElement(element);
		ListElement previousE = this.getElement(index - 1);
		ListElement current = this.getElement(index);
		
		if(previousE == null) {
			this.first.value = newE.value;
			newE.value = current.value;
		}
		else {
			previousE.next.value = newE.value;
			newE.value = current.value;
		}
	}

	@Override
	public T remove(int index) {
		
		ListElement previousE = this.getElement(index - 1);
		ListElement removedE = this.getElement(index);
		ListElement nextE = this.getElement(index + 1);
		
		if(previousE == null) {
			this.first = nextE;
		}
		else {
			previousE.next = nextE;
		}
		
		return removedE.value;
	}

	@Override
	public int indexOf(Object o) {
		int r = -1;
		int counter = 0;
		ListElement current = first;
		boolean b= false;
		
		while (current != null && b == false) {
			if (current.value == o) {
				b = true; 
				r = counter;
			} else {
				current = current.next;
				counter++;
			}
		}
		return r;
	}

	@Override
	public int lastIndexOf(Object o) {
		int r = -1;
		int counter = 0;
		ListElement current = first;
		
		
		while (current != null) {
			if (current.value == o) {
				r = counter;
				current = current.next;
				counter++;
			} else {
				current = current.next;
				counter++;
			}
		}
		return r;
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
