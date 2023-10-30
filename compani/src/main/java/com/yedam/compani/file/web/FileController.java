package com.yedam.compani.file.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.config.BusinessFileUtils;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

@Controller
public class FileController {

	@Autowired
	FileService fileservice;
	BusinessFileUtils businessfileutils;

	// 프로젝트 자료함 전체조회
	@GetMapping("projectFile")
	public String prjtF() {
		return "projectFile";
	}

	// 업무 파일함 전체조회
	@GetMapping("workFile")
	public String workFile(Model model) {
		List<FileVO> lFile = fileservice.fileList();
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

	/*
	 * // 첨부 파일 다운로드
	 * 
	 * @GetMapping("/workFile/{FileNo}/download") public ResponseEntity<Resource>
	 * downloadFile(@PathVariable final int FileNo) { FileVO file =
	 * fileservice.fileInfo(FileNo); Resource resource =
	 * businessfileutils.readFileAsResource(file); try { String filename =
	 * URLEncoder.encode(file.getFileNm(), "UTF-8"); return
	 * ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename
	 * + "\";") .header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() +
	 * "").body(resource);
	 * 
	 * } catch (UnsupportedEncodingException e) { throw new
	 * RuntimeException("filename encoding failed : " + file.getFileNm()); } }
	 */

}