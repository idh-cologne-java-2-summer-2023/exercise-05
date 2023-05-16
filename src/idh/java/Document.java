package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String>{
	static String documentText;
	//musste auf static geändert werden, damit Main-Methode zugriff hat

	public static Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) {
			b.append((char) ch);
			//System.out.println(b);
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
	 
	     
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
			  StringTokenizer st = new StringTokenizer(documentText);
				{
			     while (st.hasMoreTokens()) {
			         System.out.println(st.nextToken());
			     }
				
			}
	}


	}

