package com.yedam.compani.file.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.compani.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	FileService Fileservice;
	
	@GetMapping("projectFile")
	public String prjtF() {
		return "projectFile";
	}
	
	@GetMapping("workFile")
	public String workF() {
		return "workFile";
	}
	
	@GetMapping("modal")
	public String moD() {
		return "modal";
	}
	
	
}
