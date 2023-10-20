package com.yedam.compani.file.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// 파일 업로드 다운로드 테스트용
	@GetMapping("FileTest")
	public String FileTest() {
		return "FileTest";
	}

}
