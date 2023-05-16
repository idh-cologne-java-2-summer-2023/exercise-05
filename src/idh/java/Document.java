package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Iterator;


public class Document/*<T> implements Iterator<T> */ {
	
	/*	private Document<T> source = null;
    private int index = 0;
 
    public String ListIterator(Document<T> source){
        this.source = source;
    } 

	
	public boolean hasNext() {
        return this.index < this.source.size();
    }
	

	public T next() {
        return this.source.get(this.index++);
    }
	*/
	
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
	
	
	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	
	
	public static final void main(String[] args) throws IOException {
		Document Dracula = Document.readFromFile(new File("data/dracula.txt"));
		
		
		String[] text = documentText.split("\\s");
	     for (int i = 0; i < text.length; i++)
	         System.out.println(text[i]);
		   
	   }
	
    /*      Iterator<String> iterator = Document.iterator();
          while(iterator.hasNext()) {
              System.out.println( iterator.next() );
          } */
	
}
//Mit Iterator hab ich es nicht hinbekommen. Werde ich noch einmal versuchen.
	
