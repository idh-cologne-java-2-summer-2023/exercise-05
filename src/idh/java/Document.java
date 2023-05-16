package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document {
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
	
	public static String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public static void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		Iterator<String> itr = new StringIterator(); {
			while(itr.hasNext()) {
				String st = itr.next();
				System.out.print(st); 
			}
		}
	}
	
	//Iterator
	
	static class StringIterator implements Iterator<String> {

		int pos = -1;
		
		@Override
		public boolean hasNext() {
			return pos < documentText.length();
		}

		@Override
		public String next() {
			pos++;
			return documentText;
		}
		
	}
	
//	public static StringTokenizer stringTokenizer(String str) {
//		StringTokenizer st = new StringTokenizer(str);
////		while (st.hasMoreTokens()) {
////			System.out.println(st.nextToken());
////		}
////		return documentText;
//		return st;
//	}
	
}
