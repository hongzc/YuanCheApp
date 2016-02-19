package com.thinkgem.lbst.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.thinkgem.jeesite.lbst.entities.AppUser;

public class TestJson {

	public static void main(String[] args) {
		
		AppUser ap = new AppUser();
		ap.setUsername("hzc");
		ap.setId(15);
		ap.setPhoneNo("15071468991");
		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
			try {
				mapper.writeValue(stringWriter, ap);
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
			System.out.println(str);
	}

}
