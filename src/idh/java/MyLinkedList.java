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
		int counter = 1;
		ListElement current = first;
		
		while(current.next != null) {
			counter++;
			current = current.next;
		}return counter;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		boolean bool =false;
		ListElement current = first;
		
		while(current.next != null) {
			if(current.value.equals(o)) {
				bool =true;
				break;
			}
			current = current.next;
		}return bool;
		
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
		ListElement current = first;
		int counter1 = 0;
		
		while(current.next != null) {
			counter1++;
			current = current.next;
		}
			Object[] neuesArray = new Object[counter1];
			for (int i = 0; i < counter1; i++) {
				neuesArray[i] = current.value;
			}

		return neuesArray;
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
		
		while(current.next != null) {
			current = current.next;
			if(current.value.equals(o)) {
				current = current.next;
				return true;
			}
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
		for (T t : c) 
			this.set(index, t);
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
	public T set(int index, T element) {
		int zw = 0;
		ListElement current = first;
		try {
		while(current.next != null) {
			zw++;
			if(index == zw) {
				current.value = element;
			}
			current = current.next;
		}} catch(Exception e) {
			System.out.println("The Index is out of range.");
		}
		
		return element;
	}

	@Override
	public void add(int index, T element) {
		try {
			this.remove(index);
			this.add(element);
		}catch(Exception e) {
			System.out.println("The Index is out of range.");
		}
		
	
	}

	@Override
	public T remove(int index) {
		T current = (T) first;
		for (int i = 0; i < this.size(); i++) {
			while(current != null) {
				if(i==index) {
					this.get(i);
					this.set(i--, null);
				}
			}			
		}
		return current;

	}

	@Override
	public int indexOf(Object o) {
		int zw = 0;
		int nichtgefunden = -1;
		ListElement current = first;
		
		while(current != null) {
			if(current.value.equals(o)) {
				return zw;
			} 
			zw++;
			current = current.next;
		}return nichtgefunden;
	}

	@Override
	public int lastIndexOf(Object o) {
		int zw = 0;
		int meingrosser = -1;
		ListElement current = first;
		
		while(current != null) {
			if(current.value.equals(o)) {
				meingrosser = zw;
			}
		}
		
		return meingrosser;
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
		
		for (String s : ll) {
			System.out.println(s);
		}
		System.out.println(ll.indexOf("Hallo"));
	}
