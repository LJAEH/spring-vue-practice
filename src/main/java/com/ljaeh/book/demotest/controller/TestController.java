package com.ljaeh.book.demotest.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ljaeh.book.demotest.service.TestService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TestController {
	
	@Autowired
	private TestService service;
	
	@GetMapping("/")
	public String showVuePage() {
		return "index.html";
	}
	
	@GetMapping("/json")
	public Map<String,String> jsonTest(){
		Map<String,String> res = this.service.getTest();
		return res;
	}
	
	@PostMapping("/api/file/download")
	public ResponseEntity<Resource> download(HttpServletRequest request, @RequestParam String filename) {
 
		Resource resource = service.loadFileAsResource(filename);
 
		String contentType = null;
 
		try {
			filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
		}
 
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
 
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + filename).body(resource);
	}


}
