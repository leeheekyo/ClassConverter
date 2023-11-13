package com.naver.blog;

import java.math.BigInteger;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.blog.sttl.SampleVo;
import com.naver.blog.sttl.ToStringDecodeUtil;
import com.naver.blog.sttl.ToStringEncodeUtil;

@SpringBootApplication
public class ClassConverterApplication {

	public static void main(String[] args) {
		
		SampleVo sampleVo = new SampleVo();
		sampleVo.setId(new BigInteger("1"));
		sampleVo.setName("text");
		sampleVo.setBody("body");
		
		System.out.println("sampleVo : " + sampleVo);
		
		String encodeSampleVo = ToStringEncodeUtil.encode(sampleVo);
		System.out.println("encodeSampleVo : " + encodeSampleVo);
		
		SampleVo decodeSampleVo = ToStringDecodeUtil.decode(encodeSampleVo, SampleVo.class);
		System.out.println("decodeSampleVo : " + decodeSampleVo);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map mapObj = objectMapper.convertValue(sampleVo, Map.class);
		
		System.out.println("mapObj : " + mapObj.get("name"));
		
//		System.out.println("out : " + " ".matches("[A-Za-z0-9]+$"));
		
	}

}
