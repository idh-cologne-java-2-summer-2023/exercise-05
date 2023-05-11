package idh.java;

import java.util.Iterator;

public class SkipIterator<T> implements Iterator<T> {
    private Iterator<T> baseIterator;
    private int elementsToSkip;
    private boolean skipped;
    
    public SkipIterator(Iterator<T> baseIterator, int elementsToSkip) {
        this.baseIterator = baseIterator;
        this.elementsToSkip = elementsToSkip;
        this.skipped = false;
    }

    @Override
    public boolean hasNext() {
        if (!skipped) {
            skipElements();
        }
        return baseIterator.hasNext();
    }

    @Override
    public T next() {
        if (!skipped) {
            skipElements();
        }
        return baseIterator.next();
    }

    private void skipElements() {
        for (int i = 0; i < elementsToSkip && baseIterator.hasNext(); i++) {
            baseIterator.next();
        }
        skipped = true;
    }
}
