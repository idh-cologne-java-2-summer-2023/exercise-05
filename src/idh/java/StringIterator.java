package idh.java;

import java.util.Iterator;
import java.util.StringTokenizer;

public class StringIterator implements Iterator<String> {

	String documentText;
	StringTokenizer st = new StringTokenizer(documentText);
	
	public StringIterator(String documentText) {
		this.documentText = documentText;
		
	}
	
	@Override
	public boolean hasNext() {
		if(st.hasMoreTokens()) {
		return true;
		}
		else return false;
	}

	@Override
	public String next() {
		st.nextToken();
		return null;
	}

}

//	StringTokenizer st = new StringTokenizer(documentText);
//	while (st.hasMoreTokens()) {
//		st.nextToken();
//	 }