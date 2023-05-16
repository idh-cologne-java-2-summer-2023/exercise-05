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
// dracula wird eingelesen und ist jetzt d.documentText		
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
// Zu Aufgabe 1:
// Fehlermeldung "Cannot invoke "String.length()" because "str" is null". Verschiedene Sachen versucht,
// aber der StringTokenizer st in der TokenIterator Klasse erhält d.documentText nicht ordentlich und 
//funktioniert daher nicht. Letzte Versuche unten.
		
//		TokenIterator tokenIter = new TokenIterator(d.documentText);
//		
//		while (tokenIter.hasNext()) {
//			System.out.println(tokenIter.next());
//		}
//		
//		for (String documentText : d) {
//			System.out.println(documentText);
//		}
		
	}
	
	@Override
	public Iterator<String> iterator() {
		return new TokenIterator(documentText);
	}
	
}
