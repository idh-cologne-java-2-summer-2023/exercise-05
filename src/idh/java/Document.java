package idh.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    private String documentText;

    private Document(String documentText) {
        this.documentText = documentText;
    }

    public static Document loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return new Document(sb.toString());
    }

    @Override
    public Iterator<String> iterator() {
        return new TokenIterator();
    }

    private class TokenIterator implements Iterator<String> {
        private StringTokenizer tokenizer;

        public TokenIterator() {
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
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) throws IOException {
        Document document = Document.loadFromFile("data/dracula.txt");
        for (String token : document) {
            System.out.println(token);
        }
    }
}
