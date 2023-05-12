package idh.java;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
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
	
	public static void main(String[] args) throws IOException {
		Document document = Document.readFromFile(new File("data/dracula.txt"));

		Iterator<String>  docIter = document.iterator();
		while (docIter.hasNext()){
			String Token = docIter.next();
			System.out.println(Token);
		}



	}

	@Override
	public Iterator<String> iterator() {
		return new DocIterator();
	}

	private class DocIterator implements Iterator<String> {

		StringTokenizer toToken;
		public DocIterator(){
			toToken = new StringTokenizer(documentText);
		}
		@Override
		public boolean hasNext() {
			return toToken.hasMoreTokens();
		}

		@Override
		public String next() {
			return toToken.nextToken();
		}
	}

}
