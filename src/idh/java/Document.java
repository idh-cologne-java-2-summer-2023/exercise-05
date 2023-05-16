package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		String text = d.getDocumentText();
		StringTokenizer st = new StringTokenizer(text);
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
	//Merkw�rdigerweise wird nicht der gesamte Text ausgegeben. 
	//Vermutlich ist an irgeneiner Stelle nicht genug Speicherplatz.
	}
	
}
