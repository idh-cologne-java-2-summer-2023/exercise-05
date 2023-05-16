package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    private String documentText;

    public static Document loadFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        int ch;
        StringBuilder builder = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
            builder.append((char) ch);
        }
        fileReader.close();

        Document doc = new Document();
        doc.documentText = builder.toString();
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
        StringTokenizer tokenizer = new StringTokenizer(documentText);
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return tokenizer.hasMoreTokens();
            }

            @Override
            public String next() {
                return tokenizer.nextToken();
            }
        };
    }

    public static void main(String[] args) throws IOException {
        Document document = Document.loadFromFile(new File("data/dracula.txt"));
        for (String token : document) {
            System.out.println(token);
        }
    }
}
