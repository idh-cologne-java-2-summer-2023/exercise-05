package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
	int currentpos = 0;
	String documentText;
	StringTokenizer st;

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
		String documentText = d.getDocumentText();
		Iterator<String> it  = d.iterator();
		
		//Versuch von Aufgabe 2

		
		
		String [] words = documentText.split("\\s+");
		SkipIterator<String> si = new SkipIterator<String>(3, it, words.length);
		while(si.hasNext()) {
		documentText = si.next();
		System.out.println(documentText);
	}

		
		//Aufgabe 1
//		while(it.hasNext()) {
//			documentText = it.next();
//			System.out.println(documentText);
//		}
//		
//		System.out.println();
//		System.out.println("Ende");
	}

	public Iterator<String> iterator() {
            
		StringTokenizer st = new StringTokenizer(documentText);
		return new Iterator<String>() {

			@Override
			public boolean hasNext() {
            return st.hasMoreTokens();
			}

			@Override
			public String next() {
				return st.nextToken();
			}
	};
		
	}
}