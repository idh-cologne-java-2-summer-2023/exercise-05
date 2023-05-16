package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class Document implements Iterable<String> {
    private String documentText;

    public static Document readFromFile(File f) throws IOException {
        FileReader fileReader = new FileReader(f);
        int ch;
        StringBuilder b = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
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
        Document document = Document.readFromFile(new File("dracula.txt"));

        for (String token : document) {
            System.out.println(token);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new DocumentIterator(documentText);
    }

}
