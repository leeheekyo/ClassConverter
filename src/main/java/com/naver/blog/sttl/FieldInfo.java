package com.naver.blog.sttl;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Custom Annotation
 * @author kyoworld
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.FIELD)
public @interface FieldInfo {

	String fieldName();
	
	int fieldLen();
	
	boolean nullAble();
}
