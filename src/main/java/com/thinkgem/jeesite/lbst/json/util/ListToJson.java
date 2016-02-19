package com.thinkgem.jeesite.lbst.json.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.thinkgem.jeesite.lbst.entities.AppUser;

/**
 * List集合转化为json数据
 * @author hcc
 * 下午1:21:23
 */
public class ListToJson {

	public static String ListToJson(List list){
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter strwriter = new StringWriter();
		
		try {
			mapper.writeValue(strwriter, list);
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
		
		String str = strwriter.toString();
		return str;
	}
}
