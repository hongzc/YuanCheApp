package com.thinkgem.lbst.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.thinkgem.jeesite.lbst.entities.AppUser;

public class TestListToJson {

	public static void main(String[] args) {
		
		AppUser ap1 = new AppUser(1,"hzc01","123456");
		AppUser ap2 = new AppUser(2,"hzc03","567890");
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter strwriter = new StringWriter();
		
		List<AppUser> list = new ArrayList<AppUser>();
		list.add(ap1);
		list.add(ap2);
		
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
		System.out.println(str);
	}
}
