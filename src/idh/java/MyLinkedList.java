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
		// TODO Implement!
		//return 0;
		int i = 0;
		while(this.getElement(i) != null) {
			i++;
		}
		return i;
	}

	@Override
	public boolean isEmpty() {
		//return first == null;
		if(this.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		// TODO Implement!
		//return false;
		int i;
		for(i=0; i<this.size(); i++) {
			if(this.getElement(i) == o) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ListElement next = first;
			//Die folgenden beiden Methoden müssten eigentlich so funktionieren und müssen nicht ergänzt werden...
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
		//return null;
		Object[] arr = new Object[this.size()];
		int i = 0;
		while(this.isEmpty() == false) {
			arr[i] = this.first;
			//Wie bekomme ich hier Zugang zum nächsten Element?
			//So bekomme ich einfach nur ein Array mit dem ersten Element an jeder Stelle...
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
		// TODO: Implement
		//return false;
		if(this.contains(o)) {
			int i;
			for(i=0;i<this.size(); i++) {
				if(this.getElement(i == o)) {
					//Der Pointer des vorherigen Elements müsste statt auf dieses Element auf das nächste zeigen.
				}
			}
		}
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
		//return false;
		//über die Collection müsste iteriert werden und nacheinander die einzelnen Elemnte zu der 
		// linkedList geadded werden.
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
		//return null;
		getElement(index).value = element;
	}

	@Override
	public void add(int index, T element) {
		// TODO: Implement
		//Strategie: bis zum entsprechenden index die liste durchlaufen, den rest kopieren und temporär
		// in einer Variablen speichern, dann das element mit der "normalen" add methode anhängen und /
		// dann den Rest der Liste wieder addden.
	}

	@Override
	public T remove(int index) {
		// TODO: Implement
		return null;
		//Strategie: 
		if(this.contains(o)) {
			int i;
			for(i=0;i<this.size(); i++) {
				if(this.getElement(i == o)) {
					//Der Pointer des vorherigen Elements müsste statt auf dieses Element auf das nächste zeigen.
				}
			}
		}
	}

	@Override
	public int indexOf(Object o) {
		// TODO: Implement
		//return 0;
		if(this.contains(o)) {
			//Strategie: ein element nach dem anderen mit o abgleichen und mit einer int-variablen mitzählen
			//am Ende die int-Variable zurückgeben.
		} else {
			return -1;
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO: Implement
		//return 0;
		if(this.contains(o)) {
			//Strategie: ein element nach dem anderen mit o abgleichen,
			//bei einem "Treffer" die position in einer int-variablen speichern,
			//wenn ein weiterer "Treffer" diese Position in der int-variablen speichern.
			//am Ende die int-Variable zurückgeben.
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
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
	}
}
