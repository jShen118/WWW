/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

public class Support {
	//these three functions take a string and buffer it with a character until it is of length "length"
	//ie bufferSpace("test", 10) returns "      test"
	static public String bufferSpace(String toBuffer, int length) {
		while (toBuffer.length() < length) {
			toBuffer = " " + toBuffer;
		}
		return toBuffer;
	}
	static public String bufferZero(String toBuffer, int length) {
		while (toBuffer.length() < length) {
			toBuffer = "0" + toBuffer;
		}
		return toBuffer;
	}
	static public String bufferCustom(String toBuffer, int length, String bufferWith) {
		while (toBuffer.length() < length) {
			toBuffer = bufferWith + toBuffer;
		}
		return toBuffer;
	}
	static public String[] splitStringIntoParts(String s) {	//splits string by space
		return s.split("\\s+");
	}
	static public String multilineComment(String comment, int buffer, int cutoffLength) {	//turns "Lorem ipsum dolor sit amet, consectetur adipiscing elit." into
		String mc = "";																//"Lorem ipsum dolor sit amet,
		String[] splitComment = Support.splitStringIntoParts(comment);				//consectetur adipiscing elit"
		int rowLength = 0;
		String stringBuffer = "\n　";
		for (int i = 0; i < buffer; ++i) {
			stringBuffer += " ";
		}
		for (int i = 0; i < splitComment.length; ++i) {
			rowLength += splitComment[i].length() + 1;
			mc += splitComment[i] + " ";
			if (rowLength >= cutoffLength) {
				rowLength = 0;
				mc += stringBuffer;
			}
		}
		return mc;
	}
	static public int min3(int a, int b, int c) {	//returns the smallest of 3 values
		return Math.min(a, Math.min(b, c));
	}
	//levenshtein distance calculates the distance between two strings. 
	//"kitten" and "mitten" would have a distance of 1 because of the 1 char difference.
	//I would love to say I wrote this myself based on the mathematical definition, but
	//this is just a direct translation of the C code on wikipedia
	static private int lev(String a, int sizea, String b, int sizeb) {
		int cost;
		if (sizea == 0) {
			return sizeb;
		}
		if (sizeb == 0) {
			return sizea;
		}
		if (a.charAt(sizea - 1) == b.charAt(sizeb - 1)) {
			cost = 0;
		} else {
			cost = 1;
		}
		return min3(
				lev(a, sizea - 1, b, sizeb		) + 1,
				lev(a, sizea	, b, sizeb - 1	) + 1,
				lev(a, sizea - 1, b, sizeb - 1	) + cost
			);
	}
	static public int levenshtein(String a, String b) {
		return lev(a, a.length(), b, b.length());
	}
}
