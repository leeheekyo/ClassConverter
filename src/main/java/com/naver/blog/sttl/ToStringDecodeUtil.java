package com.naver.blog.sttl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Flat(using toString annotation -> Flat) to class decoder
 * @author kyoworld
 */
public class ToStringDecodeUtil {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * decode string to class
	 * @param encodeSampleVo : source string
	 * @param <T>clazz : target class
	 * @return
	 */
	public static <T>T decode(String encodeSampleVo, Class<T> clazz) {
		
		TreeMap targetMap = new TreeMap();
		
		Field[] fields = clazz.getDeclaredFields();
		
		int startIdx=0;
		
		try {
			for(Field field : fields) {
				FieldInfo annotation = field.getAnnotation(FieldInfo.class);
				
				String fieldName = annotation.fieldName();
				int fieldLen = annotation.fieldLen();
				String fieldType = field.getType().toString();
				String fieldValue = encodeSampleVo.substring(startIdx, startIdx + fieldLen);
				
				startIdx += fieldLen;
				
				targetMap.put(fieldName, fieldValue);
			}
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		T resultObj = objectMapper.convertValue(targetMap, clazz);
		
		return resultObj;
	}
	

}
