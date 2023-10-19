package com.yedam.compani.file.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

@Controller
public class FileController {
	
	@Autowired
	FileService fileservice;
	
	// 전체조회
	@GetMapping("projectFile")
	public String prjtF() {
		return "projectFile";
	}
	
	// 전체조회
	@GetMapping("workFile")
	public String workFile(Model model) {
		List<FileVO> lFile = fileservice.getFileList();
		model.addAttribute("fileList", lFile);
		return "workFile";
	}
	
	// 모달 확인용
	@GetMapping("modal")
	public String moD() {
		return "modal";
	}
	
	// 등록 모달창 확인용
	@GetMapping("insertmodal")
	public String insertmodal() {
		return "insertmodal";
	}
	
}
