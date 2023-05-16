package idh.java;

import java.util.Iterator;

public class SkipIterator<T> implements Iterator<T> {
	
	Iterator<T> baseit;
	int number;
	
	boolean executedOnce = false;
	
	public SkipIterator(Iterator<T> it, int n) {
		baseit = it;
		number = n;
	}

	@Override
	public boolean hasNext() {
		if (!executedOnce) {
			executedOnce = true;
			return true;
		}
		for (int i = 0; i < number; i++) {
			if (baseit.hasNext())
				baseit.next();
			else return false;
		}
		return baseit.hasNext();
	}

	@Override
	public T next() {
		
		return baseit.next();
	}

}
