package idh.java;

import java.util.Iterator;

public class SkipIterator<T> implements Iterator<T>{
	
	private Iterator<T> iterator = null;
	int n = 0;
	
	public SkipIterator(Iterator<T> iterator, int n) {
		this.iterator= iterator;
		this.n = n;
	}
	
	public boolean hasNext() {
		
		return iterator.hasNext();
	}

	public T next() {
		
		
		for(int i = 0;  i< n -1 ; i++) {
			
			if(hasNext()) iterator.next();
			else return null;
			
		}
			
		
		return iterator.next();
	}

}
