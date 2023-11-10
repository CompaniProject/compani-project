package com.yedam.compani.file.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// 파일 삭제 - 하나삭제 - AJAX
		@GetMapping("fileOneDelete")
		@ResponseBody // AJAX 사용
		public int fileOneDelete(Integer fileNo) {
			return fileservice.fileDelete(fileNo);
		}

	// 파일 삭제 - 체크박스 - 전체선택 || 선택삭제 - AJAX
		@PostMapping("fileSelectDel")
		@ResponseBody
		public int fileSelectDel(@RequestParam List<Integer> files) {
			return fileservice.fileSelDel(files);
		}

}
