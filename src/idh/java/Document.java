package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Document<T> implements Iterable<T> {
    private T[] elements;

    public Document(T[] elements) {
        this.elements = elements;
    }

    public Document() {
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < elements.length;
        }

        @Override
        public T next() {
            T element = elements[currentIndex];
            currentIndex++;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }

    String documentText;

    public static Document<String> readFromFile(File f) throws IOException {
        FileReader fileReader = new FileReader(f);
        int ch;
        StringBuilder b = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
            b.append((char) ch);
        }
        fileReader.close();
        Document<String> doc = new Document<>();
        doc.documentText = b.toString();

        return doc;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Document<String> d = Document.readFromFile(new File("data/dracula.txt"));

        for (String token : d) {
            System.out.println(token);
        }
    }
}

/* es funktioniert nicht, weil die Main Klasse nicht gefunden werden kann
 * ich habe versucht eine eigene datei für die Main Klasse zu machen,
 * hat aber auch nicht funtioniert 
 * ich habe auch mit dem quick fix probiert die Klasse anders zu benennen, 
 * auch auf dem workspace 
 */
