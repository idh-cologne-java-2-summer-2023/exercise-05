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
		return new StringIterator();
	}

	class StringIterator implements Iterator<String> {
		StringTokenizer dTokens = new StringTokenizer(documentText);

		@Override
		public boolean hasNext() {
			return dTokens.hasMoreTokens();
		}

		@Override
		public String next() {
			return dTokens.nextToken();
		}

	}

	public static final void main(String[] args) throws IOException, InterruptedException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		for (String t : d) {
			System.out.println(t.replaceAll("\\p{IsPunctuation}", ""));
			Thread.sleep(500);
		}

	}

}
