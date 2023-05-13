package idh.java;	//Start

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
	
	class StringIterator implements Iterator<String>{
		int currentPosition = -1;
		
		@Override
		public boolean hasNext() {
			return currentPosition < tokens.size()	-1;
		}

		@Override
		public String next() {
			currentPosition++;
			return tokens.get(currentPosition);
		}
		
	}
	
	String documentText;
	ArrayList<String> tokens = new ArrayList<String>();		//Aus documentText werden hier die tokens reingeschrieben
	
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

	//Zur Tokenisierung von documentText
	public void  tokenize() {
		StringTokenizer st = new StringTokenizer(this.documentText);
		while (st.hasMoreTokens()) {
	         this.tokens.add(st.nextToken());
	     }
	}
	
	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		//documentText tokenisieren lassen
		d.tokenize();
		//Über die ArrayList<String> im aktuellen Document Objekt (d) iterieren
		for (String string : d) {
			System.out.println(string);
		}
		
	}	
	

	@Override
	public Iterator<String> iterator() {
		
	return new StringIterator();
	}
	
}
