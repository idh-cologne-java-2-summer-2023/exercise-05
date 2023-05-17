package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document  {
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
		
		Iterator<Object> iterator = d.iterator();
		
		d.skipWords();
		//d.printAll();
		
	}


	private Iterator<Object> iterator() {
		StringTokenizer stringtokenizer = new StringTokenizer(this.documentText);
		
		return stringtokenizer.asIterator();
	}
	
	private void printAll() {
		Iterator iterator = iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	private void skipWords() {
		SkipIterator<String> skipiterator = new SkipIterator(iterator(), 4);
		
		
		while(skipiterator.hasNext()) {
			System.out.println(skipiterator.next());
		}
		
	}
	
}
