import java.util.Iterator;

public class SkipIterator<T> implements Iterator<T> {
    private Iterator<T> baseIterator;
    private int elementsToSkip;
    private int skippedElements;

    public SkipIterator(Iterator<T> baseIterator, int elementsToSkip) {
        this.baseIterator = baseIterator;
        this.elementsToSkip = elementsToSkip;
        this.skippedElements = 0;
        skipElements();
    }

    private void skipElements() {
        while (skippedElements < elementsToSkip && baseIterator.hasNext()) {
            baseIterator.next();
            skippedElements++;
        }
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public T next() {
        T nextElement = baseIterator.next();
        skipElements();
        return nextElement;
    }

    public static void main(String[] args) {
        Document d = Document.readFromFile(new File("data/dracula.txt"));
        Iterator<String> documentIterator = d.iterator();

        SkipIterator<String> skipIterator = new SkipIterator<>(documentIterator, 2);

        while (skipIterator.hasNext()) {
            String token = skipIterator.next();
            System.out.println(token);
        }
    }
}
