package idh.java;

import java.util.Iterator;
import java.util.StringTokenizer;

public class TokenIterator implements Iterator<String>{
	
	Document doc;
	StringTokenizer st;
	
	public TokenIterator(Document doc) {
		this.doc = doc;
		st = new StringTokenizer (doc.documentText);
	}

	@Override
	public boolean hasNext() {
		return st.hasMoreTokens();
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return st.nextToken();
	}

}
