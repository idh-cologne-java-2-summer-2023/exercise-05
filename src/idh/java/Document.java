package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<Object>{
	public  String documentText;
	

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
		public static String text;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iterator'");

		//What is the f***ing variable with the text in it?!
		//Why is this unreacheable code?!
		StringTokenizer st = new StringTokenizer(  documentText , ",");
		ArrayList <String> tokens = new ArrayList <String> ();
		Iterator<String> it = tokens.iterator();
		while (it.hasNext()) {
			tokens.add (st.nextToken());
			int tokenAmount = st.countTokens();
		//	System.out.println(st.nextToken());
	
	        
		}
	
}
}