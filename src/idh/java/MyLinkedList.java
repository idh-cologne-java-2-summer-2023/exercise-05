package idh.java;

import java.util.*;


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
		ListElement current = first;

		while (current != null) {
			if (current.value.equals(o)) {
			return true;
			}
			current = current.next;
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
		Object[] array = new Object[size()];

		for (int i = 0; i < size(); i++) {
			array[i] = get(i);
		}

		StringBuilder sb = new StringBuilder();
		for (Object element : array) {
			sb.append(element).append(" ");
		}
		System.out.println(sb.toString().trim());

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
		ListElement current = first;
		ListElement previous = null;
		while (current != null) {
			if (current.value.equals(o)) {
				if (previous == null) { //dann handelt es sich um das erste Element was auf 0.equals
					first = current.next; //dann first = das nächste Element. Das erste wird also übersprungen und somit entfernt, da das nächste zum ersten wird
				} else { //previous != null, das gesuchte Element ist also nicht das erste.
					previous.next = current.next; //dann wird Zeiger auf das nächste Element gesetzt
				}
				return true;
			}
			previous = current; //previous uaf current und current auf current.next damit die Schleife weiterläuft
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
		int size = size();
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}

		Object[] elementsToAdd = c.toArray();
		int numElementsToAdd = elementsToAdd.length;

		if (numElementsToAdd == 0) {
			return false;
		}

		ListElement previous = getElement(index - 1);
		ListElement current = previous != null ? previous.next : first; //wenn previous != null ist dann ist current = previous.next, ansonsten ist es first

		for (Object element : elementsToAdd) { //für jedes Element in elementsToAdd
			ListElement newElement = new ListElement((T) element); //neues Element erstellen

			if (previous != null) {
				previous.next = newElement; //wenn previous != 0 dann ist es nicht first und dementsprechend ist das aktuelle also previous.next das newElement, wo wir anfangen wollen
			} else {
				first = newElement; //ansonsten muss new Element = first sein also das Erste Element in der Liste
			}

			newElement.next = current; //für jedes Element das durchlaufen wird gilt newElement.next = current
			previous = newElement; //das vorherige wird dann zu newElement
			size++; //nur für den Zähler der Elemente innerhalb der Liste zuständig (vergrößert die Liste nicht, das passiert automatisch)
			System.out.println("element added to size. Now there are " + size + " elements in the new list");
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
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Ungültiger Index: " + index);
		}
		ListElement current = getElement(index);

		if (current != null) {
			T previousValue = current.value;
			current.value = element;
			return previousValue;
		}
		return null;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Ungültiger Index: " + index);
		}

		if (index == size()-1) {
			add(element);
		} else {
			ListElement newElement = new ListElement(element);
			if (index == 0) {
				newElement.next = first; //next-Zeiger des neuen Elements auf das first Element -> first Element kommt also nach dem newElement
				first = newElement; //newElement zum first Element machen
			} else {
				ListElement previous = getElement(index - 1); //Element vor Index(3)
				newElement.next = previous.next;
				//TODO: Element nach New Element wird zu Element, das mal nach dem vorherigen kam
				//TODO: und das was mal nach dem vorherigen kam, ist jetzt das NewElement
				previous.next = newElement;
			}
		}
	}


	@Override
	public T remove(int index) {

		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}

		ListElement current = first;
		int currentIndex = 0;
		while (current != null) {
			if (currentIndex == index)
				return (T) current;
			current = current.next;
			currentIndex++;
		}
		return null;
	}

	@Override
	public int indexOf(Object o) {
		ListElement current = first;
		int index = 0;

		while (current != null) {
			if (current.value.equals(o)) {
				return index;
			}
			current = current.next;
			index++;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		ListElement current = first;
		int lastIndex = -1;
		int index = 0;

		while (current != null) {
			if (current.value.equals(o)) {
				lastIndex = index;
			}
			current = current.next;
			index++;
		}
		return lastIndex; //letzter Index-Wert wird zurückgegeben, da while-Schleife komplett durchlaufen konnte und immer dann lastIndex aktualisiert hat, wenn o gefunden wurde
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
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		ll.add("5");
		ll.add("6");
		ll.add("Hallo");
		ll.get(0);

		for (String s : ll) {
			System.out.println(s);
		}
		int size = ll.size();
		System.out.println("Size: " + size);

		int index = 1;
		String p = "Moin";
		boolean contains = ll.contains(p);
		System.out.println("Contains " + "'" + p + "'" + " is: " + contains);

		//Object[] arrayObj = ll.toArray(); 	//Elemenete werden als jeweils neue Objects gespeichert und können nun bearbeitet werden, sowie verschiedene Datentypen können genutzt werden
		ll.toArray();
		//String[] array = ll.toArray(new String[ll.size()]); //Als String, da man weiß, dass es sich aktuell ausschließlich um Strings handelt

		//remove Element das p also "Welt" entspricht
		boolean remove = ll.remove(p);
		System.out.println("Remove " + "'" + p + "'" + " is " + remove);
		ll.toArray();

		//remove Element an Stelle von Index
		ll.remove(index);
		System.out.println("Index removed is " + remove);
		ll.toArray();

		//gib mir Index vom Element wo es als erstes vorkommt
		int indexOf = ll.indexOf("Hallo");
		System.out.println("Index: " + indexOf);

		//gib mir Index vom Element wo es als letztes vorkommt
		int lastIndex = ll.lastIndexOf("Hallo");
		System.out.println("Last index: " + lastIndex);

		//gib Element an der Stelle von Index
		String element = ll.getElement(index).value;
		System.out.println("Element: " + element);
		ll.toArray();

		//ersetze Element an der Stelle von Index gegen T Element
		//ll.set(index, "Moin");
		String previousValue = ll.set(index, p);
		System.out.println("Vorheriger Wert: " + previousValue + " wurde ersetzt durch neuen Wert " + p);
		ll.toArray();

		//add Element an Stelle von Index aber hinzufügen und nicht ersetzen
		System.out.println(p + " an Stelle von Index " + index + " eingefügt");
		ll.add(index, p);
		ll.toArray();

		//add all
		List<String> c = new ArrayList<>();
		c.add("GUTEN");
		c.add("TAG");
		ll.addAll(index, c);
		ll.toArray();
	}
}
