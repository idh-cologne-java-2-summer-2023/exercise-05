package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Document implements Iterable<String> {
	String documentText;

	public static Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while ((ch = fileReader.read()) != -1) {
			b.append((char) ch);
		}
		fileReader.close();
		String documentText = b.toString();

		return new Document(documentText);
	}

	private Document(String documentText) {
		this.documentText = documentText;
	}

	@Override
	public Iterator<String> iterator() {
		return new StringTokenizer(documentText).iterator();
	}

	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}

	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("H:/Meine Ablage/_Uni-Drive/4. Semester/Java/Seminar/exercise-05/data/dracula.txt"));
		String input = d.getDocumentText();
		StringTokenizer tokenizer = new StringTokenizer(input, " ");
		System.getProperty("user.dir");
		for (String token : tokenizer) {
			System.out.println(token);
		}
	}
}
