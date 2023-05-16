package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Node;

public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, because it will now
	 * whether there is a next element.
	 */
	ListElement first;

	@Override
	// size wurde implementiert
	// Die Elemente in der Liste werden gezählt und die while Schleife durchläuft
	// die Implementierung
	// Am ende wird mit reutn count, size repräsentiert
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

	// Hier wird überprüft, ob o in der Liste vorhanden ist, dafür wird jedes
	// Element in der Liste
	// mit o verglichen
	// Bei Übereinstimmung wird true ausgegeben und bei keiner Übereinstimmung
	// wird false ausgegeben
	@Override
	public boolean contains(Object o) {
		ListElement current = first;

		while (current != null) {
			if (o == null ? current.value == null : o.equals(current.value)) {
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

	// Array mit der Größe der Liste wird erstellt und anschließend werden die
	// Elemente
	// in das Array eingefügt
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		int index = 0;
		ListElement current = first;

		while (current != null) {
			array[index++] = current.value;
			current = current.next;
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

	// Hier wird die Suche nach o mit remove implementiert
	// Wenn o sich am Anfang der Liste befindet, dann wird darauf verwiesen mit
	// first
	// Wenn sich o in der Mitte oder am Ende der Liste befindet, dann wird ebenfalls
	// darauf verwiesen mit next
	// true gibt an, ob o entfernt wurde
	// false gibt an, wenn o nicht gefunden oder entfernt werden konnte
	// Leider konnte diese Implementierung auch nicht ausgefühtt werden und mir ist
	// es leider nicht
	// gelungen, den Fehler zu beheben
	@Override
	public boolean remove(Object o) {
		if (first == null) {
			return false; // Element kann nicht entfernt werden, weil die Liste leer ist

			/*
			 * if (first.value.equals(o)) { first = first.next; // Erstes Element wird
			 * entfernt return true; }
			 * 
			 * ListElement previous = first; ListElement current = first.next;
			 * 
			 * while (current != null) { if (current.value.equals(o)) { previous.next =
			 * current.next; // Element, dass entfernt werden soll wurde gefunden return
			 * true; } previous = current; current = current.next; }
			 * 
			 */
		}
		return false; // Element, dass entfernt werden soll wurde nicht gefunden
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c)
			this.add(t);
		return true;
	}

	// Sammlung c wird durchlaufen und ein neues ListElement wird gesetzt
	// Neues Element wird an der Stelle von index eingefügt
	// Bei dem Wert null wird index an den Anfang gesetzt
	// Bei dem Wert size() wird die Liste an das Ende gesetzt
	// true zeigt an, wenn eine Veränderung stattgefunden hat
	// IndexOutOfBoundsException wird angezeigt, wenn der index sich außerhalb des
	// gültigen Bereiches befindet
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}

		boolean modified = false;
		ListElement previous = null;
		ListElement current = first;

		for (T element : c) {
			ListElement newListElement = new ListElement(element);

			if (previous == null) {
				// Element wird an den Anfang der Liste eingefügt
				newListElement.next = current;
				first = newListElement;
			} else {
				// Element wird in die angegebene Position eingefügt
				newListElement.next = current;
				previous.next = newListElement;
			}

			previous = newListElement;
			modified = true;
		}

		return modified;
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

	// Hier wird index gefunden und gespeichert und die Liste wird aktualisert
	@Override
	public T set(int index, T element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		ListElement current = getElement(index);
		T oldValue = current.value;
		current.value = element;

		return oldValue;
	}

	// Ein neues List Element wird erstellt
	// Bei dem wert 0 befindet sich das neue Element am Anfang der Liste ansonsten
	// befindet es sich
	// An der Stelle vom Index
	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			ListElement newElement = new ListElement(element);
			newElement.next = first;
			first = newElement;
		} else {
			ListElement previous = getElement(index - 1);
			ListElement newElement = new ListElement(element);
			newElement.next = previous.next;
			previous.next = newElement;
		}
	}

	// Bei der Implementierung von int index nicht sicher gewesen, deswegen ist sie
	// nicht implementiert
	// Ich hatte vor, den index mithilfe eines Knotens zu entfernen aber leider habe
	// ich nur
	// Fehlermeldungen bekommen, weshalb ich remove(int index) nur kommentiert habe
	// Der kommentierte Code ist nicht vollständig
	@Override
	public T remove(int index) {
		/*
		 * Node<T> removedNode = getNode(index);
		 * 
		 * if (index == 0) { firstNode = removedNode.next; } else if (index == size - 1)
		 * { lastNode = removedNode.prev; } else { removedNode.prev.next =
		 * removedNode.next; removedNode.next.prev = removedNode.prev; }
		 */
		return null;
	}

	// Hier habe ich veruscht aufzuzeigen, an welcher Stelle o das erste Mal
	// vorkommt
	// Das Element soll auf seinen Wert überprüft werden, indem die Werte vom
	// Anfang als erstes untersucht werden
	// Wenn eine Übereinstimmung stattfindet, soll der Index zurückgegeben werden
	@Override
	public int indexOf(Object o) {
		/*
		 * int index = 0; if (o == null) { for (Node<T> node = firstNode; node != null;
		 * node = node.next) { if (node.item == null) { return index; } index++; } }
		 * else { for (Node<T> node = firstNode; node != null; node = node.next) { if
		 * (o.equals(node.item)) { return index; } index++; } }
		 */
		return 0;
	}

	// Hier wollte ich wie bei indexOf vorgehen, aber leider war der Code auch
	// fehlerhaft
	// Hier habe ich versucht die Suche von hinten zu starten, doch leider hat das
	// nicht geklappt
	@Override
	public int lastIndexOf(Object o) {
		/*
		 * int index = size() - 1; if (o == null) { for (Node<T> node = lastNode; node
		 * != null; node = node.prev) { if (node.item == null) { return index; }
		 * index--; } } else { for (Node<T> node = lastNode; node != null; node =
		 * node.prev) { if (o.equals(node.item)) { return index; } index--; } }
		 */
		return 0;
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
				return index + 1;
			}

			@Override
			public int previousIndex() {
				return index - 1;
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
	 * Internal method that iterates over the list, returning the last element
	 * (i.e., the one whose next field is null)
	 * 
	 * @return
	 */
	private ListElement last() {
		if (first == null)
			return null;
		ListElement current = first;

		while (current.next != null) {
			current = current.next;
		}
		return current;
	}

	/**
	 * Internal method to get the list element (not the value) of the list at the
	 * specified index position.
	 * 
	 * @param index
	 * @return
	 */
	private ListElement getElement(int index) {
		if (isEmpty())
			return null;
		ListElement current = first;
		while (current != null) {
			if (index == 0)
				return current;
			index--;
			current = current.next;
		}
		return null;
	}

	// Die Ausgabe der Liste wurde abgeändert, um die erarbeiteten Implementierungen auf der Konsole anzuzeigen
	public static void main(String[] args) {

		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		System.out.println("Element at index 0: " + ll.get(0)); //Zeigt an, welches Element sich an der Stelle 0 befindet 
		System.out.println("Size of the list: " + ll.size()); //Zeigt die Größe der Liste an 
		System.out.println("Is the list empty? " + ll.isEmpty()); //Kontrolliert, ob sich Elemente in der Liste befinden 
		System.out.println("Does the list contain 'Hallo'? " + ll.contains("Hallo")); //Prüft, ob die Liste Hello enthält 

		// Iterating over the list
		System.out.println("Elements in the list:"); //Zeigt die Elemente in der Liste
		for (String s : ll) {
			System.out.println(s);
		}

		// Removing an element
		ll.remove("Welt"); //Versucht das Element Welt zu entfernen. Leider hat die Entfernung nicht korrekt funktioniert 
		System.out.println("After removing 'Welt':");
		for (String s : ll) {
			System.out.println(s);
		}
	}
}
