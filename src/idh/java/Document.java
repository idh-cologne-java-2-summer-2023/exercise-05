package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String>{
	String documentText;

	public class DocumentIterator implements Iterator<String>{
		StringTokenizer sTokenizer;
		
		public DocumentIterator() {
			this.sTokenizer = new StringTokenizer(documentText);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return sTokenizer.hasMoreTokens();
		}

		@Override
		public String next() {
			// TODO Auto-generated method stub
			return sTokenizer.nextToken();
		}
		
		
	}
	
	public static Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) {
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
	
	public static final void main(String[] args) throws IOException {
		Document document = Document.readFromFile(new File("data/dracula.txt"));

		for (String test : document) {
			System.out.println(test);
		}
	}

	@Override
	public Iterator<String> iterator() {
		
		return new DocumentIterator();
	}
	
}