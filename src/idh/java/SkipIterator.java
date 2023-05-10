package idh.java;

import java.util.Iterator;

// A possible intrgration for "Aufgabe 2"
// Another possibility would be like the hack in 
// "Aufgabe 1"
public class SkipIterator<T> implements Iterator<T> {
	T[] array;
	int pos = 0;
	int steps = 1;

	SkipIterator(T[] array, int steps) {
		this.array = array;
		this.steps = steps;
	}

	@Override
	public boolean hasNext() {
		return pos + (steps * pos) < array.length;
	}

	@Override
	public T next() {
		pos += steps;
		return array[pos - steps];
	}
	
}
