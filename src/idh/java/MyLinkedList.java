package idh.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


//toArray and LastIndexof Method not working because of 

public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement first;
	
	
	@Override
	public int size() {
		int length = 1;
        while(first.next != null) {
        	length++;
        	first = first.next;
        }
		return length;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		while(first.next != null) {
			if(first.value.equals(o)) {
				return true;
			}else {
				first = first.next;
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
		int size = this.size();
		
		Object[] array = new Object[size];
		
		ListIterator<T> li = this.listIterator();
		int i = 0;
		while(li.hasNext()) {
			array[i] = li.next();
			i++;
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
		while(first.next != null) {	
			if(first.value.equals(o)) {
				first.value = null;
				return true;
			}else {
				first = first.next;
			}
		}
		System.out.println("Kein Objekt gefunden.");
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
		boolean istrue = false;
		for (T t : c) {
			this.set(index, t);
		index++;
		istrue = true;
	}
	return istrue;
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
		T previous = get(index);
		add(index, element);
		return previous;
		}else {
		add(index, element);
		return null;
		
		}
	}

	@Override
	public void add(int index, T element) {
		ListElement previous = this.getElement(index-1);
		ListElement currentelement = this.getElement(index);
		ListElement newelement = new ListElement(element);

		if(previous == null) {
			this.first.value = newelement.value;
			newelement.next.value = currentelement.value;
		}else {
			previous.next.value = newelement.value;
			newelement.next.value = currentelement.value;
		}
	          
		}		
	
	@Override
	public T remove(int index) {
		ListElement previous = this.getElement(index-1);
		ListElement deletedelement = this.getElement(index);
		ListElement nextelement = this.getElement(index+1);

		if(previous == null) {
			this.first = nextelement;
		}else {
			previous.next = nextelement;
		}
		return deletedelement.value;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		while(first.next != null) {	
			if(first.value.equals(o)) {
				return index;
			}else {
				first = first.next;
				index++;
			}
		}
		if(index == this.size()) {
			return -1;
		}
		return index;
		
	}

	@Override
	public int lastIndexOf(Object o) {
//        for(T t : this) {
//        	if(t == o) {
//        		
//        	}
		
		int s = this.size();
		int i = s;
		while(s > 0) {
			if(get(i) == o) {
				return i;
			}else {
				i--;
			}
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
		ll.add("Universum");

        
		for (String s : ll) {
			System.out.println(s);
		}
		
	}
}