package idh.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class TokenIterator implements Iterator<String> {
		
	String documentText;
	
	StringTokenizer st = new StringTokenizer(this.documentText); 
	
	public TokenIterator(String documentText) {
		documentText = this.documentText;
	}
	
	@Override
	public boolean hasNext() {
		return st.hasMoreTokens();

	}

	@Override
	public String next() {
		return st.nextToken();

	}

}
