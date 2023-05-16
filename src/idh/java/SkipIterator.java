package idh.java;

import java.util.Iterator;
import java.util.StringTokenizer;

public class SkipIterator<T> implements Iterator<T> {
	int n;
	Iterator<T> it;
	int currentpos;
	T e;
	int length;
	
	public SkipIterator( int n, Iterator it, int length) {
		this.n = n;
		this.it = it;
        this.length = length;
	}

	@Override
	public boolean hasNext() {
        return (currentpos + n) < length;
	}
	@Override
	public T next() {
		currentpos = currentpos + n;
		for(int i = currentpos; i > 0; i--) {
			e = it.next();
		}
		

		return e;
}
}

