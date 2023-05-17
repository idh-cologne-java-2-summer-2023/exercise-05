package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    String documentText;

    public static Document readFromFile(File f) throws IOException {
        FileReader fileReader = new FileReader(f);
        int ch;
        StringBuilder b = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
            b.append((char) ch);
        }
        fileReader.close();
        Document doc = new Document();
        doc.documentText = b.toString();

        return doc;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    @Override
    public Iterator<String> iterator() {
        return new DocumentIterator();
    }

    private class DocumentIterator implements Iterator<String> {
        private StringTokenizer tokenizer;

        public DocumentIterator() {
            tokenizer = new StringTokenizer(documentText);
        }

        @Override
        public boolean hasNext() {
            return tokenizer.hasMoreTokens();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return tokenizer.nextToken();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) throws IOException {
        Document d = Document.readFromFile(new File("data/dracula.txt"));
        Iterator<String> iterator = d.iterator();
        while (iterator.hasNext()) {
            String token = iterator.next();
            System.out.println(token);
        }
    }

}
