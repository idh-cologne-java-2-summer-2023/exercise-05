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
		int s = 0;
		if (first == null)
			return s;

		ListElement current = first;
		while(current.next != null) {
			s++;
			current = current.next;
		}
		return s + 1;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		boolean r = false;
		ListElement current = first;
		while(current.next != null) {
			if (current.value == o) {
				r = true;
				break;
			}

			current = current.next;
		}
		return r;
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
		int s = this.size();
		Object[] o = new Object[s];
		int c = 0;
		for(T t : this) {
			if (c < s) {
				o[c] = t;
				c++;
			}
		}
		return o;
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
		if (this.contains(o)) {
			int i = this.indexOf(o);
			ListElement e1 = getElement(i - 1);
			ListElement e2 = getElement(i + 1);
			e1.next = e2;
			return true;
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
		// This is a good looking solution for this function
		// A probably more performant version would be
		// creating a list for the new collection
		// and then changing the "pointer" for the first element
		// to the first element on the list and the last element
		// of the list to the first element of the cut out list
		// I like this version more because it looks IMO better
		int e = 0;
		boolean lc = false;
		for (T t : c) {
			add(index + e, t);
			e++;
			lc = true;
		}
		return lc;
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
		T o = e.value;
		e.value = element;
		return o;
	}

	@Override
	public void add(int index, T element) {
		ListElement f = this.getElement(index - 1);
		ListElement l = this.getElement(index);
		ListElement t = new ListElement(element);
		if(f == null) {
			this.first = t;
			t.next = l;
		} else {
			f.next = t;
			t.next = l;
		}
	}

	@Override
	public T remove(int index) {
		ListElement f = getElement(index - 1);
		ListElement t = getElement(index);
		ListElement l = getElement(index + 1);
		if(f == null) {
			this.first = l;
		} else {
			f.next = l;
		}
		return t.value;
	}

	@Override
	public int indexOf(Object o) {
		int c = 0;
		for(T t : this) {
			if (t == o)
				return c;
			c++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int r = -1;
		int c = 0;
		for(T t : this) {
			if (t == o)
				r = c;
			c++;
		}
		return r > 0 ? r : -1;
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
		ll.add("Erde");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
		System.out.println("Test Size: ");
		int s = ll.size();
		System.out.println("Return: " + s);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test contains: (Hallo)");
		boolean c = ll.contains("Hallo");
		System.out.println("Return: " + c);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test toArray: ");
		Object[] a = ll.toArray();
		for(Object o : a) {
			System.out.println(o);
		}
		System.out.println("Test remove: (Welt)");
		boolean r = ll.remove("Welt");
		System.out.println("Return: " + r);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test addAll: (0 => ll)");
		ll.addAll(0, ll);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test set: (0 => Hi)");
		String set = ll.set(0, "Hi");
		System.out.println("Return: " + set);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test add: (0 => Goodby)");
		ll.add(0, "Goodby");
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test remove: (1)");
		String re = ll.remove(1);
		System.out.println("Return: " + re);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test indexOf: (Erde)");
		int i = ll.indexOf("Erde");
		System.out.println("Return: " + i);
		for (String st : ll) {
			System.out.println(st);
		}
		System.out.println("Test lastIndexOf: (Erde)");
		int li = ll.lastIndexOf("Erde");
		System.out.println("Return: " + li);
		for (String st : ll) {
			System.out.println(st);
		}
	}
}
