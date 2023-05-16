package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document {
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

	// Hier wird document iterierbar gemacht mit Iterator<String> iterator

	public Iterator<String> iterator() {
		return new TokenIterator();
	}

	private class TokenIterator implements Iterator<String> {
		StringTokenizer tokenizer;

		public TokenIterator() {
			tokenizer = new StringTokenizer(documentText);
		}

		public boolean hasNext() {
			return tokenizer.hasMoreTokens();
		}

		public String next() {
			return tokenizer.nextToken();
		}
	}

	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));

		
	//Hier wird Iterator abgerufen und verarbeitet mithilfe einer while-Schleife
		 Iterator<String> iterator = d.iterator();
	        while (iterator.hasNext()) {
	            String token = iterator.next();
	            System.out.println(token);
	        }
	}

}
