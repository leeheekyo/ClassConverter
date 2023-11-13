package com.naver.blog.sttl;

import java.lang.reflect.Field;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * toString annotation to Flat
 * @author kyoworld
 */
public class ToStringEncodeUtil {

	/**
	 * class encode to flat data
	 * @param item : input Class with ToString annotation
	 * @return : String
	 */
	public static String encode(Object item) { // sample : "SampleVo(id=1, name=text, body=body)"
//		Logger logger = LoggerFactory.getLogger(ToStringEncodeUtil.class);
		Class itemClass = item.getClass();
		
		String itemStr = itemClass.toString();
		int fieldIndex;
		
		// extract fiedl item
		itemStr = itemStr.substring(itemStr.indexOf("(")+1, itemStr.length()-1); // remove class ()
		
		fieldIndex = itemStr.indexOf("=");
		
		while(fieldIndex >= 0) {
			String nextItemStr = itemStr.substring(fieldIndex + 1);
			
			String fieldValue = getNextFieldFromToString(nextItemStr);
			
			fieldIndex += fieldValue.length() + 1;
			
//			break;
		}

		
		System.out.println("itemStr : " + itemStr);
		
		Field[] fields = itemClass.getDeclaredFields();
		
		String resultStr = "";
		
		try {
			for(Field field : fields) {
				FieldInfo annotation = field.getAnnotation(FieldInfo.class);
				String fieldName = field.getName();
				int fieldLen = annotation.fieldLen();
				String fieldType = field.getType().toString().toUpperCase();
				String fieldValue = field.get(item).toString();
				
				if(fieldType.contains("STR")) {
					resultStr += rightPadding(fieldValue, fieldLen, " ");
				}
				if(fieldType.contains("INT") || fieldType.contains("LONG") ) {
					resultStr += leftPadding(fieldValue, fieldLen, "0");
				}
				else {
					resultStr += fieldValue;
				}
			}
		}
		catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return resultStr;
	}
	
	/**
	 * toString get next field info
	 * @param nextItemStr : input string _ ex) 1, name=text
	 * @return : next field info _ ex) 1
	 */
	private static String getNextFieldFromToString(String nextItemStr) { // sample : "1, name=text, body=body"
		String resultString = "";
		int nextFieldIndex = nextItemStr.indexOf("=");
		
		while(nextFieldIndex>=0) {
			String tergetString = nextItemStr.substring(0, nextFieldIndex);
			int fieldEndIndex = tergetString.indexOf(", ");
			String prefFieldString = tergetString.substring(0, fieldEndIndex);
			String postFieldString = tergetString.substring(fieldEndIndex+2);
			
			if( postFieldString.matches("[A-Za-z0-9]+$") ) {
				resultString = prefFieldString;
				break;
			}
			
		}
		
		return resultString;
	}
	
	/**
	 * string left padding
	 * @param input : input string
	 * @param paddingSize : result size
	 * @param paddingStr : padding string
	 * @return : padding result string
	 */
	public static String leftPadding(String input, int paddingSize, String paddingStr) {
		int inputLen = paddingSize - input.length();
		String resultStr = "";
	    while (resultStr.length() < inputLen) {
	    	resultStr += paddingStr;
	    }
	    resultStr += input;
	    
	    return resultStr;
	}

	/**
	 * string right padding
	 * @param input : input string
	 * @param paddingSize : result size
	 * @param paddingStr : padding string
	 * @return : padding result string
	 */
	public static String rightPadding(String input, int paddingSize, String paddingStr) {
//		int resultLen = paddingSize - input.length();
		String resultStr = "";
		resultStr += input;
	    while (resultStr.length() < paddingSize) {
	    	resultStr += paddingStr;
	    }
	    
	    return resultStr;
	}

}
