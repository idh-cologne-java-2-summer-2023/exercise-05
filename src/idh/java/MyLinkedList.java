package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;


public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement first;
	
	
	@Override
	public int size() {
		int counter = 0;
		for(T element : this) {
			counter++;
		}
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		for (T element : this) {
			if(element.equals(o))
				return true;
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
		Object[] arr = new Object[this.size()];
		for (T element : this) {
			arr[this.indexOf(element)] = element;
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
		int cIndex = indexOf(o);
		if(cIndex == -1)
			return false;
		
		getElement(cIndex - 1).next = getElement(cIndex).next;
		return true;
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
		if(c.isEmpty() || index < 0)
			return false;
		else if(index > this.size()) {
			this.addAll(c);
			return true;
		}
		
		int counter = 0;
		for (T t : c) {
			this.add(counter+index, t);
			counter++;
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
	public T set(int index, T element) {
		T temp = getElement(index).value;
		getElement(index).value = element;
		return temp;
	}

	@Override
	public void add(int index, T element) {
		ListElement addedE = new ListElement(element);
		if(index == 0) {
			ListElement temp = first;
			first = addedE;
			first.next = temp;
		}
		else if(index>0 && index<this.size()){
			ListElement temp = getElement(index);
			getElement(index - 1).next = addedE;
			addedE.next = temp;
		}
		else {
			this.add(element);
		}
		
	}

	@Override
	public T remove(int index) {
		T temp = getElement(index).value;
		getElement(index -1).next = getElement(index).next;
		return temp;
	}

	@Override
	public int indexOf(Object o) {
		int counter = 0;
		for (T element : this) {
			if(element.equals(o))
				return counter;
			
			counter++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int counter = 0;
		int res = -1;
		for (T element : this) {
			if(element.equals(o))
				res = counter;
			
			counter++;
		}
		return res;
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
		MyLinkedList<String> ll2 = new MyLinkedList<String>();

		ll2.add("Hallo");
		ll2.add("Welt");
		ll2.add("testd");
		ll.addAll(6,ll2);
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
	}
}
