package idh.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class TokenIterator implements Iterator<String> {
	
	String[] documentTextTokens;
	int currentPosition = -1;
	
	public TokenIterator(String documentText) {
		String[] tokenArray = new String[0];
		List<String> arrayList = new ArrayList<String>(Arrays.asList(tokenArray));
		StringTokenizer st = new StringTokenizer(documentText);
		while (st.hasMoreTokens()) {			
			arrayList.add(st.nextToken());
	     }	
		tokenArray = arrayList.toArray(tokenArray);
		tokenArray = this.documentTextTokens;
	}
	
	@Override
	public boolean hasNext() {
		return currentPosition < documentTextTokens.length;
	}

	@Override
	public String next() {
		currentPosition++;
		return documentTextTokens[currentPosition];
	}

}
