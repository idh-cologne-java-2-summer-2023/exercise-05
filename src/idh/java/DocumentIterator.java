package idh.java;

import java.util.Iterator;
import java.util.StringTokenizer;


	public class DocumentIterator implements Iterator<String> {
        private StringTokenizer tokenizerForDracula;

        public DocumentIterator(String documentText) {
            tokenizerForDracula = new StringTokenizer(documentText);
        }

        @Override
        public boolean hasNext() {
            return tokenizerForDracula.hasMoreTokens();
        }

        @Override
        public String next() {
            return tokenizerForDracula.nextToken();
        }
    }

