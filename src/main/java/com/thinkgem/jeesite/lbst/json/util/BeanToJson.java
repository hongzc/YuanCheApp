package com.thinkgem.jeesite.lbst.json.util;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 把一个对象转为json串的工具方法
 * @author hcc
 * 上午11:50:58
 */
public class BeanToJson {

	public static<T> String getJsonGenerator(T t){
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
			try {
				mapper.writeValue(stringWriter, t);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String str = stringWriter.toString();
			return str;
	}
}
