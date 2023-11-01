package com.yedam.compani.file.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.config.BusinessFileUtils;
import com.yedam.compani.file.service.FileSearchDTO;
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
	BusinessFileUtils businessfileutils;

	// 프로젝트 자료함 확인
	@GetMapping("/projectFile")
	public String prjtF() {
		return "projectFile";
	}
	
	// 파일 리스트
	@GetMapping("/searchFile")
	public String fileList(@ModelAttribute FileSearchDTO search, FileVO fileVO,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
		PageInfo<FileVO> file = new PageInfo<>(fileservice.fileList(pageNum, search), 5);
		Page<FileVO> vo = fileservice.fileList(pageNum, search);
		
		model.addAttribute("file", file);
		model.addAttribute("fileList", vo);
		model.addAttribute("search", search);
		System.out.println(file);
		System.out.println(vo);
		
		return "modal/modal-file";
	}
	

	
	// 페이징 및 검색 AJAX
	@GetMapping("/searchAjax")
	@ResponseBody
	public Map<String, Object> fileList(@ModelAttribute FileSearchDTO search,
			@RequestParam(required = false, defaultValue = "1") int pageNum){
		PageInfo<FileVO> file = new PageInfo<>(fileservice.fileList(pageNum, search), 5);
		Map<String, Object> map = new HashMap<>();
		
		map.put("file", file);
		map.put("files", fileservice.fileList(pageNum, search));
		map.put("search", search);
		
		return map;
	}

	// 모달 확인용
	@GetMapping("/modal")
	public String moD() {
		return "modal";
	}

	// 등록 모달창 확인용
	@GetMapping("/insertmodal")
	public String insertmodal() {
		return "insertmodal";
	}

	// 파일 업로드 다운로드 테스트용
	@GetMapping("/FileTest")
	public String FileTest() {
		return "FileTest";
	}



	// 첨부 파일 다운로드

	@GetMapping("/workFile/{FileNo}/download")
	public ResponseEntity<Resource> downloadFile(@PathVariable final int FileNo) {
		FileVO file = fileservice.fileInfo(FileNo);
		Resource resource = new FileSystemResource("c:\\Temp\\" + file.getFileNm());
		try {
			String filename = URLEncoder.encode(file.getFileNm(), "UTF-8");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
					.header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() + "").body(resource);

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("filename encoding failed : " + file.getFileNm());
		}
	}

}