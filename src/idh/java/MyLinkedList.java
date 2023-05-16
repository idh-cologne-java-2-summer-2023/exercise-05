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
	
	/**
	 * Returns the number of elements in this list.
	 */
	@Override
	public int size() {
		int counter = 0;
		if (isEmpty()) 
			return 0;
		ListElement current = first;
		while(current != null) {
			counter++;
			current = current.next;
		}
		return counter;
	}

	/**
	 * Returns true if this list contains no elements.
	 */
	@Override 
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns true if this list contains the specified element. 
	 */
	@Override
	public boolean contains(Object o) {
		if (isEmpty()) 
			return false;
		ListElement current = first;
		while(current != null) {
			if(current == o) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
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

	/**
	 * Returns an array containing all of the elements in this list in proper sequence (From first to last element).
	 * The returned array will be safe in that no references to it are maintained by this list
	 */
	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size()];
		ListElement current = first;
		int counter = 0;
		while(current != null) {
			arr[counter] = current;
			counter++;
			current = current.next;
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

	/**
	 * Appends the specified element to the end of this list.
	 */
	@Override
	public boolean add(T e) {
		ListElement newListElement = new ListElement(e);
		if (first == null)
			first = newListElement;
		else
			last().next = newListElement;
		return true;
	}

	/**
	 * Removes the first occurence of the specified element from this list, if it is present.
	 * If this list does not contain the element, it is unchanged.
	 * Returns true if this list contained the specified element.
	 */
	@Override
	public boolean remove(Object o) {
		if(first == null) 
			return false;
		ListElement current = first;
		ListElement prev = first;
		while(current != null) {
			if(current == o) {
				prev.next = current.next;
				return true;
			}
			prev = current;
			current = current.next;
		}
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified collection
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (! contains(o))
				return false;
		return true;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of the list. 
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c) 
			this.add(t);
		return true;
	}

	/**
	 * Inserts all of the elements in the specified collection into this list at the specified position.
	 * Shifts the element currently at that position and any subsequent elements to the right.
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if(first == null) 
			for (T t : c) 
				this.add(t);
		ListElement current = first;
		ListElement prev = first;
		int counter = 0;
		
		while(current != null) {
			if(index == counter) {
				for(T t: c) {
					ListElement newListElement = new ListElement(t);
					prev.next = newListElement;						
				}
				index += c.size();
				getElement(index-1).next = current;
				return true;
			}
			counter++;
			prev = current;
			current = current.next;
		}
		return false;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean r = true;
		for (Object o : c) 
			r = r || this.remove(o);
		return r;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes all of the elements from this list
	 */
	@Override
	public void clear() {
		first = null;
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	@Override
	public T get(int index) {
		return getElement(index).value;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element
	 */
	@Override
	public T set(int index, T element) {
		ListElement current = first;
		ListElement prev = first;
		int counter = 0;
		while(current != null) {
			if(index == counter) {
				ListElement newListElement = new ListElement(element);
				prev.next = newListElement;	
				newListElement.next = current.next;
				return element;
			}
			counter++;
			prev = current;
			current = current.next;
		}
		return element;
	}

	/**
	 * Inserts the specified element at the specified position in this list
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right
	 */
	@Override
	public void add(int index, T element) {
		ListElement current = first;
		ListElement prev = first;
		int counter = 0;
		
		while(current != null) {
			if(index == counter) {
					ListElement newListElement = new ListElement(element);
					prev.next = newListElement;	
					newListElement.next = current;
			}
			counter++;
			prev = current;
			current = current.next;
		}
	}
	
	/**
	 * Removes the element at the specified position in this list
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@Override
	public T remove(int index) {
		ListElement current = first;
		ListElement prev = first;
		int counter = 0;
		
		while(current != null) {
			if(index == counter) {
					prev.next = current.next;
					return current.value;
			}
			counter++;
			prev = current;
			current = current.next;
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. 
	 */
	@Override
	public int indexOf(Object o) {
		ListElement current = first;
		int counter = 0;
		
		while(current != null) {
			if(o == current) {	
				return counter;
			}
			counter++;
			current = current.next;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		ListElement current = first;
		int counter = 0;
		int temp = 0;
		
		while(current != null) {
			if(o == current) {	
				temp = counter;
			}
			counter++;
			current = current.next;
		}
		
		if(temp >= 0) {
			return temp;
		} else {
			return -1;
		}
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
		ll.add(1, "Blurgh");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
		
	}
}
