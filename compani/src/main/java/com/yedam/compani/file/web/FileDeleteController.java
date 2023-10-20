package com.yedam.compani.file.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.file.service.FileService;

@Controller
public class FileDeleteController {
	
	@Autowired
	FileService fileservice;
	
	// 파일 삭제 - 하나삭제 - AJAX
		@GetMapping("getDelAjax")
		@ResponseBody // AJAX 사용
		public Map<String, Object> fileDelete(@RequestParam Integer fileNo) {
			List<Integer> list = new ArrayList<>(); // 리스트에 몇개가 있든 상관없이 담음
			list.add(fileNo); // list에다 파일번호 삽입
			return fileservice.getFileDelete(list);
		}

		/*
		 * // 파일 삭제 - 선택삭제 - AJAX
		 * 
		 * @PostMapping("jsonDelAjax")
		 * 
		 * @ResponseBody // JSON 사용 public boolean empListDelete(@RequestBody
		 * List<Integer> workFile) { Map<String, Object> map = new HashMap<>();
		 * map.put("result", true); map.put("data", workFile); return (boolean) // 성공 여부
		 * 리턴 }
		 */
}
