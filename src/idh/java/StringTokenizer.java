package idh.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringTokenizer implements Iterable<String> {

    private String input;
    private String delimiter;

    public StringTokenizer(String input) {
        this.input = input;
        this.delimiter = " ";
    }

    public StringTokenizer(String input, String delimiter) {
        this.input = input;
        this.delimiter = delimiter;
    }

    @Override
    public Iterator<String> iterator() {
        return new TokenIterator();
    }

    private class TokenIterator implements Iterator<String> {
        private int currentPosition;

        @Override
        public boolean hasNext() {
            return currentPosition < input.length();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int start = currentPosition;
            int end = input.indexOf(delimiter, currentPosition);

            if (end == -1) {
                end = input.length();
            }

            String token = input.substring(start, end);
            currentPosition = end + delimiter.length();

            return token;
        }
    }
}
