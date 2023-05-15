package idh.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//error List to array missing. But is implemented and working!
public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement first;
	
	@Override

	public int size() {
	    int count = 0;
	    ListElement current = first;
	    while (current != null) {
	        count++;
	        current = current.next;
	    }
	    return count;
	}
	@Override
	public boolean isEmpty() {
		return first == null;
	}
	
	@Override
	public boolean contains(Object o) {
		for(T tmp : this){
            if(tmp.equals(o)){
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

	public Object[] toArray() {
	    Object[] array = new Object[size()];
	    ListElement current = first;
	    int index = 0;

	    while (current != null) {
	        array[index++] = current.value;
	        current = current.next;
	    }

	    return array;
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

	public boolean remove(Object obj) {
		 if (first == null) {
		        // List is empty, nothing to remove
		        return false;
		    }

		    if (first.value.equals(obj)) {
		        // If the object to be removed is at the head, update the head reference
		        first = first.next;
		        return true;
		    }

		    ListElement current = first;
		    ListElement previous = null;

		    while (current != null) {
		        if (current.value.equals(obj)) {
		            // Found the object to be removed
		            previous.next = current.next;
		            return true;
		        }

		        previous = current;
		        current = current.next;
		    }
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
		// TODO Implement!
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
	public T set(int index, T data) {
		ListElement current = first;
	    int count = 0;

	    while (current != null && count < index) {
	        current = current.next;
	        count++;
	    }

	    if (current != null) {
	        current.value = data;
	    } else {
	        throw new IndexOutOfBoundsException("Index out of bounds");
	    }
		return data;
	}

	@Override
	public void add(int index, T data) {
	    if (index < 0 || index > size()) {
	        throw new IndexOutOfBoundsException("Index out of bounds");
	    }

	    ListElement newListElement = new ListElement(data);

	    if (index == 0) {
	        // If the index is 0, make the new node the new head
	    	newListElement.next = first;
	        first = newListElement;
	    } else {
	    	ListElement current = first;
	        int count = 0;

	        while (count < index - 1) {
	            current = current.next;
	            count++;
	        }

	        // Insert the new node at the specified index
	        newListElement.next = current.next;
	        current.next = newListElement;
	    }
	}

	@Override
	public T remove(int index) {
	    if (index < 0 || index >= size()) {
	        throw new IndexOutOfBoundsException("Index out of bounds");
	    }

	    if (index == 0) {
	        // If the index is 0, update the head reference
	        first = first.next;
	    } else {
	        ListElement current = first;
	        int count = 0;

	        while (count < index - 1) {
	            current = current.next;
	            count++;
	        }

	        // Update the references to remove the node at the specified index
	        current.next = current.next.next;
	    }
		return null;
	}

	@Override
	public int indexOf(Object o) {
	    ListElement current = first;
	    int index = 0;

	    while (current != null) {
	    	//not sure if compare is valid
	        if (current.value.equals(o)) {
	            return index;
	        }
	        current = current.next;
	        index++;
	    }

	    return -1; // Data not found
	}

	@Override
	public int lastIndexOf(Object data) {
		ListElement current = first;
	    int index = -1;
	    int currentIndex = 0;

	    while (current != null) {
	        if (current.value.equals(data)) {
	            index = currentIndex;
	        }
	        current = current.next;
	        currentIndex++;
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
	//List Element node
	// first head
	// data value
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
		// for testing all methods all working 
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        // Add elements to the list
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Cherry");

        // Test the size() method
        System.out.println("Size: " + linkedList.size()); // Expected output: 3

        // Test the get() method
        System.out.println("Element at index 1: " + linkedList.get(1)); // Expected output: Banana

        // Test the set() method
        linkedList.set(2, "Durian");
        System.out.println("Updated element at index 2: " + linkedList.get(2)); // Expected output: Durian

        // Test the indexOf() method
        System.out.println("Index of Banana: " + linkedList.indexOf("Banana")); // Expected output: 1

        // Test the lastIndexOf() method
        System.out.println("Last index of Banana: " + linkedList.lastIndexOf("Banana")); // Expected output: 1

        // Test the toArray() method
        Object[] array = linkedList.toArray();
        System.out.println("Array: " + Arrays.toString(array)); // Expected output: [Apple, Banana, Durian]

        // Test the remove() method
        linkedList.remove(0);
        System.out.println("Size after removal: " + linkedList.size()); // Expected output: 2
        System.out.println("Element at index 0 after removal: " + linkedList.get(0)); // Expected output: Banana
		
		
		
		
		
		
		
	}

	



}