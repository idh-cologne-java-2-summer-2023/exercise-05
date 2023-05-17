package idh.java;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.IOException;

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
	
	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		d.print();
	}
	
	private Iterator<Object> iterator(){
		StringTokenizer strtoken = new StringTokenizer(this.documentText);
		
		return strtoken.asIterator();
	}
	
	private void print() {
		Iterator<?> iterator = iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}
		
	}
	
}
