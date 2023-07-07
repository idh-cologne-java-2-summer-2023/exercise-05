package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

public class Document implements Iterable<String> {
	String documentText;
	ArrayList<String> eineListe = new ArrayList<String>();

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
		
		//for-Schleife
		for (Object words : d) {
			System.out.println(words);
		}
	}
	
	public String[] getTheWords() {
		StringTokenizer st = new StringTokenizer(documentText);
		
		try {
			while (st.hasMoreTokens()) {
				eineListe.add(st.nextToken());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
	    	 System.out.println("Nein");
	    }
		
		String[] words = new String[eineListe.size()];
		
		for(int i = 0; i < eineListe.size(); i++) {
			words[i] = eineListe.get(i);
		}
		
		return words;
	}

	@Override
	public java.util.Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return new DocumentIterator(getTheWords());
	}
	
}