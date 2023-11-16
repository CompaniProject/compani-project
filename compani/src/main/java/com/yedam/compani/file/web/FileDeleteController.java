package com.yedam.compani.file.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.file.service.FileService;

@Controller
public class FileDeleteController {
	
	@Autowired
	FileService fileservice;
	
	// 드라이브 - 하나삭제 - AJAX
		@GetMapping("fileOneDelete")
		@ResponseBody // AJAX 사용
		public int fileOneDelete(Integer fileNo, String filePath) {
			
			return fileservice.fileDelete(fileNo);
		}

	// 드라이브 - 전체선택 || 선택삭제 - AJAX
		@PostMapping("fileSelectDel")
		@ResponseBody
		public int fileSelectDel(@RequestParam List<Integer> files, String filePath) {
			
			return fileservice.fileSelDel(files);
		}
		
	// 모달 - 하나삭제 - AJAX
		@GetMapping("fileModalDel")
		@ResponseBody // AJAX 사용
		public int fileModalDel(Integer fileNo, String filePath) {
			
			return fileservice.fileDelete(fileNo);
		}

	// 모달 - 체크박스 - 전체선택 || 선택삭제 - AJAX
		@PostMapping("fileModalSeldel")
		@ResponseBody
		public int fileModalSeldel(@RequestParam List<Integer> files, String filePath) {
				
			return fileservice.fileSelDel(files);
		}

}
