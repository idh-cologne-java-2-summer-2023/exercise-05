package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String>{
//Aufgabe 1. ziemlich unschön gelöst glaub ich.	
	
	String documentText;

//hierüber wird bei Aufgabe 1 iteriert	
	String[] documentTextTokens;

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

//	definiert documentTextTokens für Aufgabe 1
	public void setDocumentTextTokens(String[] documentTextTokens) {
		this.documentTextTokens = documentTextTokens;
	}
	
	public static final void main(String[] args) throws IOException {
// dracula wird eingelesen und ist jetzt d.documentText		
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		
	}

// wandelt documentText in Token-Array
	public static String[] tokenizer(String documentText) {
		String[] tokenArray = new String[0];
		List<String> arrayList = new ArrayList<String>(Arrays.asList(tokenArray));
		StringTokenizer st = new StringTokenizer(documentText);
		while (st.hasMoreTokens()) {			
			arrayList.add(st.nextToken());
	     }	
		tokenArray = arrayList.toArray(tokenArray);
		return tokenArray;
	}
	
	@Override
	public Iterator<String> iterator() {
		return new TokenIterator(this.documentText);
	}
	
}
