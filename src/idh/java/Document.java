package idh.java;

import java.io.File;
	
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Document {
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
	
	class StIterator implements Iterator<String>{
		StringTokenizer tokens;
		
		public StIterator() {
			this.tokens = new StringTokenizer(documentText);
		}
		
		@Override
		public boolean hasNext() {
			return tokens.hasMoreElements();
		}
		
		@Override
		public String next(){
			return tokens.nextToken();
		}
	}
	
	public String getDocumentText() {
		return documentText;
	}
	
	public Iterator<String> iterator() {
		return new StIterator();
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		Iterator<String> iter = d.iterator();
		
		while(iter.hasNext() == true) {
			System.out.println(iter.next());
		}
		
	}
	
}
