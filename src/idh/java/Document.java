package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
	
	class DocumentIterator implements Iterator<String> {
		Document docText;
		int currentPosition = -1;
		StringTokenizer st;
		
		public DocumentIterator(Document document) {
			this.docText = document;
			this.st = new StringTokenizer(docText.documentText);
		}
	
		public boolean hasNext() {
			return currentPosition < st.countTokens();
		}
	
		public String next() {
			currentPosition++;
			return (String) st.nextElement();
		}
			
	}
	
	String documentText;

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
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		for (String docText : d) {
			System.out.println(docText);
			
		}
	
	}
	


	public Iterator<String> iterator() {
		return new DocumentIterator(this);
	}
	
}