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
}
