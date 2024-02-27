package com.ljaeh.book.demotest.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.ljaeh.book.demotest.exception.TestException;

@Service
public class TestService {
	
	public Map<String,String> getTest(){
		Map<String,String> res = new HashMap<>();
		res.put("testword", "helloe");
		return res;
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path targetLocation = Paths.get("C:\\Users\\user\\Desktop".toString()).resolve(fileName);
			Resource resource = new UrlResource(targetLocation.toUri());
 
			if (resource.exists()) {
				return resource;
			} else {
				throw new TestException("File not found " + fileName);
			}
		} catch (Exception ex) {
			throw new TestException("File not found " + fileName, ex);
		}
	}
}
