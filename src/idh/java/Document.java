package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Document implements Iterable<String>{
	//read gets saved in documentText
 static String documentText; 
 
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
	
	class MyItr implements Iterator<String>{
		StringTokenizer temp;
		
		public MyItr() {
			//star Stringtokenzier with documentText saved in temp while working
			this.temp = new StringTokenizer(documentText);
		}
		
		@Override
		public boolean hasNext() {
			return temp.hasMoreElements();
		}
		
		@Override
		public String next(){
			return temp.nextToken();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		Iterator<String> iter = d.iterator();
		//print tokens
		while(iter.hasNext() == true) {
			System.out.println(iter.next());
		}
		
	}
	
	public Iterator<String> iterator() {
		return new MyItr();
	}
	
	public static String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
}