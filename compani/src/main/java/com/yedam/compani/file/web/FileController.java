package com.yedam.compani.file.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileController {
	
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
