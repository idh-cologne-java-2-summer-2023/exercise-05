package idh.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/*
 * 
 * Die TODO Markierungen wurden absichtlich drinnen gelassen.
 * 
 */

public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will know whether there is a next element.
	 */
	ListElement first;
	int numberOfElements = 0;
	
	@Override
	public int size() {
		// TODO Implement!
		
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Implement!
		boolean inList = false;
		for (int i =0; i < this.size(); i++) {
			//System.out.println(this.get(i));
			if(this.get(i).equals(o)) {
				inList = true;
			}
		}	
		return inList;
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
		// TODO Implement!
		Object[] listAsArray = new Object[this.size()];
		
		for (int i =0; i < this.size(); i++) {
			listAsArray[i] = this.get(i);
		}			
		return listAsArray;
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
		if (first == null) {
			first = newListElement;
		numberOfElements = 1;
	}
		else {
		last().next = newListElement;
		numberOfElements++;
		}
		return true;
		
	}

	@Override
	public boolean remove(Object o) {
		// TODO: Implement
		for (int i =0; i < this.size(); i++) {
			if(this.get(i).equals(o)) {
				this.getElement(i).value = null;
				this.getElement(i-1).next = this.getElement(i).next;
				numberOfElements--;
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
		// TODO Implement!
		for (T t : c) { 
				this.add(index, t);
				index++;
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
		// TODO: Implement
		this.getElement(index).value = element;
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO: Implement
		ListElement neuesElement = new ListElement(element);
		
		if (index == 0) {
			neuesElement.next = this.getElement(0);
			this.first = neuesElement;	
		}
		
		else if (index == (this.size() - 1)) {
			this.getElement(index - 1).next = neuesElement;
			neuesElement.next = null;	
		}
		
		else {
			neuesElement.next = this.getElement(index);
			this.getElement(index - 1).next = neuesElement;	
		}
		this.numberOfElements++;
	}

	@Override
	public T remove(int index) {
		// TODO: Implement
		if (index == 0) {
			this.first = this.getElement(index + 1);
			this.getElement(index).value = null;
		}
		
		else if (index == (this.size() - 1)) {
			this.getElement(index).value = null;
			this.getElement(index - 1).next = null;	
		}
		
		else {
			this.getElement(index).value = null;
			this.getElement(index - 1).next = this.getElement(index + 1);	
		}
		
		this.numberOfElements--;
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO: Implement
		int index = 0;
		for (int i =0; i < this.size(); i++) {
			if(this.get(i).equals(o)) {
				index = i;
				break;
			}
			else {
				index = -1;
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO: Implement
		int index = 0;
		for (int i =0; i < this.size(); i++) {
			if(this.get(i).equals(o)) {
				index = i;
			}
			else {
				index = -1;
			}
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
		ll.add("lol");
		ll.add("rofl");
		ll.get(0);
			
		//Test für size()
		System.out.println("SIZE-TEST-----------------------------------------");
		System.out.println(ll.size());
		System.out.println();
		
		//Test für contains()
		System.out.println("CONTAINS-TEST-----------------------------------------");
		System.out.println(ll.contains("Hallo"));	//true
		System.out.println(ll.contains("rofl"));  	//true
		System.out.println(ll.contains("jitetnit"));  //false
		System.out.println();
		
		//Test für toArray()
		System.out.println("TOARRAY-TEST-----------------------------------------");
		Object[] test = ll.toArray();
		System.out.println(test[0]);
		System.out.println(test[ll.size()-1]);
		System.out.println();
		
		//Test für remove(Object o)
		System.out.println("REMOVE-TEST-----------------------------------------");
		ll.remove("rofl");
		System.out.println(ll.get(ll.size()-1));	//lol
		System.out.println(ll.size());	//3
		ll.add("rofl");
		System.out.println(ll.get(ll.size()-1));	//rofl
		System.out.println(ll.size());	//4
		System.out.println();
		
		//Test für Add(index, element), lastIndexOf, indexOf 
		System.out.println("INDEX-UND-ADD-TEST-----------------------------------------");
		ll.add("lol");
		for (String s : ll) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("Index lol hinten: " + ll.lastIndexOf("lol"));
		ll.add(1, "lol");
		System.out.println("Index lol vorne: " + ll.indexOf("lol"));
		
		System.out.println();
		for (String s : ll) {
			System.out.println(s);
		}
		System.out.println("Aktuelle size ll: " + ll.size());
		System.out.println();
		
		//Test für addAll(index, collection)
		System.out.println("ADDALL-INDEX-COLLECTION-TEST-----------------------------------------");
		ArrayList<String> testList = new ArrayList<>();
		testList.add("was-geht");
		testList.add("denn-so-ab?");
		ll.addAll(2, testList);
		for (String s : ll) {
			System.out.println(s);
		}
		
		
	}
}
