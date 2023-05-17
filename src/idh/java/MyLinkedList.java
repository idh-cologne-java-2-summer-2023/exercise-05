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
//		ListElement current = first;
//		int i = 0;
//		while(current != null) {
//			current = current.next;
//			i++;
//		}
		return indexOf(last());
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		
	for (Object ob : this) {
		if (ob.equals(o)) {
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
		Iterator <T> iter = this.iterator();
		Object [] obarr = new Object [this.size()];
		
		for (int i = 0; i < obarr.length; i++){
			obarr[i] = iter.next();
		}
		return null;
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
			if (current.value.equals(o)) {
				if (previous == null) {
					first = current.next;
				}
				else {
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
		
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("invalid index: " + index);
		}
		
		ListElement previous = getElement(index -1);
		ListElement current = previous != null ? previous.next : first;
		
		for (T element : c) {
			ListElement newEl = new ListElement(element);
			if (previous == null) {
				newEl.next = first;
				first = newEl;
			}
			else {
				newEl.next = current;
				current = newEl;
			}
		//	add(index, element);
			 return true;
		}
		
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
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("invalid index: " + index);
		}
		
		T oldValue = getElement(index).value;
		getElement(index).value = element;
		return oldValue ;
	}

	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("invalid index: " + index);
		}
		
		ListElement previous = getElement(index -1);
		ListElement current = previous != null ? previous.next : first;
		
		ListElement newEl = new ListElement(element);
		if (previous == null) {
			newEl.next = first;
			first = newEl;
		}
		else {
			newEl.next = current;
			current = newEl;
		}
		
		
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("invalid index: " + index);
		}
		
		T oldValue = getElement(index).value;
		remove(getElement(index));
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		Iterator<T> iter = this.iterator();
		int i = 0;
		while (iter.hasNext()) {
			if (getElement(i).value.equals(o)) {
				return i;
			}
		
			i++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		Iterator<T> iter = this.iterator();
		int i = size();
		while (iter.hasNext()) {
			if (getElement(i).value.equals(o)) {
				return i;
			}
		
			i--;
		}
		
		return -1;
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
