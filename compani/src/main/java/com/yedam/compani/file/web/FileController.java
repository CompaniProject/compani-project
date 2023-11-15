package com.yedam.compani.file.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

/*
작성자 : 문기환
작성일자 : 
파일 관리 : 프로젝트 자료함 확인, 업무 파일함 파일리스트, 업무 파일함 검색
*/

@Controller
public class FileController {

	@Autowired
	FileService fileservice;
	
	@Autowired
	BusinessService business;
	
	// 파일 리스트
	@GetMapping("searchFile/{bussNo}")
	public String fileList(@PathVariable int bussNo, 
			                String type, 
			                String keywordFile, 
			                FileVO fileVO,
			                @RequestParam(required = false, defaultValue = "1") int pageNum,
			                Model model) throws Exception {
		
		PageInfo<FileVO> file = new PageInfo<>(fileservice.fileList(pageNum, type, keywordFile, bussNo), 5);
		
		model.addAttribute("file", file);
		model.addAttribute("bVO", business.businessSelect(bussNo));
 
		return "modal/modal-file";
	}
	
	// 페이징 및 검색 AJAX
	@GetMapping("searchAjax/{bussNo}")
	@ResponseBody
	public Map<String, Object> fileList(@PathVariable int bussNo, 
										String type, 
										String keywordFile,
										@RequestParam(required = false, defaultValue = "1") int pageNum){
		PageInfo<FileVO> file = new PageInfo<>(fileservice.fileList(pageNum, type, keywordFile, bussNo), 5);
		Map<String, Object> map = new HashMap<>();
		map.put("file", file);
		return map;
	}
	
}