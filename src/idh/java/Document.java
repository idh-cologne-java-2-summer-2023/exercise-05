package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
	String documentText;
	
	List<String> tokens = new ArrayList<>();

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
		StringTokenizer st = new StringTokenizer(d.documentText);
		while (st.hasMoreTokens()) {
			d.tokens.add(st.nextToken());
		}
		
		SkipIterator<String> sit = new SkipIterator<>(d.iterator(), 2);
		int i = 0;
		while (sit.hasNext() && i < 20) {
			System.out.println(sit.next());
			i++;
		}
		
	}

	@Override
	public Iterator<String> iterator() {
		
		return this.tokens.iterator();
	}
	
}
