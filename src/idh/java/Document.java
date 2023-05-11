package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    private String documentText;

    public static Document loadFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        int ch;
        StringBuilder stringBuilder = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
            stringBuilder.append((char) ch);
        }
        fileReader.close();
        Document doc = new Document();
        doc.documentText = stringBuilder.toString();

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
            return tokenizer.nextToken();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }

    public static void main(String[] args) throws IOException {
        Document document = Document.loadFromFile(new File("data/dracula.txt"));

        Iterator<String> baseIterator = document.iterator();
        int elementsToSkip = 2;

        SkipIterator<String> skipIterator = new SkipIterator<>(baseIterator, elementsToSkip);
        while (skipIterator.hasNext()) {
            String token = skipIterator.next();
            System.out.println(token);
        }
    }
}
