package com.naver.blog.sttl;

import java.math.BigInteger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * sample VO
 * @author kyoworld
 */
@Getter @Setter
@NoArgsConstructor
@ToString
public class SampleVo {
	@FieldInfo(fieldName="id", fieldLen=4, nullAble=false)
	BigInteger id;
	
	@FieldInfo(fieldName="name", fieldLen=10, nullAble=false)
	String name;
	
	@FieldInfo(fieldName="body", fieldLen=100, nullAble=false)
	String body;

}
