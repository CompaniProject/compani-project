package com.yedam.compani.file.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.config.DriveFileUtils;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

@Controller
public class ProjectFileController {

	@Autowired
	FileService fileservice;
	
	@Autowired
	BusinessService businessservice;
	
	@Autowired
	DriveFileUtils dfu;

	// 파일 리스트 + 업무 리스트
	@GetMapping("project/drive/{prjtNo}")
	public String fileList(@PathVariable int prjtNo, String pType, String pKeyword, FileVO fileVO, Model model) throws Exception {
		List<FileVO> file = new ArrayList<> (fileservice.fileList(pType, pKeyword, prjtNo));
		List<FileVO> vo = fileservice.fileList(pType, pKeyword, prjtNo);
		List<BusinessVO> bussVO = businessservice.businessdriveNameList(prjtNo); 

		model.addAttribute("file", file);
		model.addAttribute("pFileList", vo);
		model.addAttribute("search", pType);
		model.addAttribute("driveBussName", bussVO);

		return "project/project-drive";
	}

	// 페이징 및 검색 AJAX
	@GetMapping("project/driveAjax/{prjtNo}")
	@ResponseBody
	public Map<String, Object> fileList(@PathVariable int prjtNo, String pType, String pKeyword) {
		List<FileVO> file = new ArrayList<> (fileservice.fileList(pType, pKeyword, prjtNo));
		Map<String, Object> map = new HashMap<>();

		map.put("file", file);
		map.put("files", fileservice.fileList(pType, pKeyword, prjtNo));
		map.put("search", pType);
		return map;
	}
	
	// 업로드
	@PostMapping("project/driveInsertAjax/save")
	@ResponseBody
	public Map<String, Object> fileListInsert(MultipartFile[] drivefile, FileVO fileVO, int prjtNo, int bussNo, String membId) {
		
		// 파일 업로드, 파일 DB에 저장
		List<FileVO> uploadedFiles = new ArrayList<>();
		if (drivefile != null && drivefile.length > 0) {
				 uploadedFiles = dfu.uploadFiles(Arrays.asList(drivefile)); // 배열을  리스트로 변환하는 메서드. MultipartFile[] files -> List<MultipartFile>
				 fileservice.driveFileInsert(uploadedFiles, prjtNo, bussNo, membId);
		}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("uploadedFiles", uploadedFiles);
		map.put("driveVO", fileVO);
		
		return map;
	}

	// 확장자 필터링 AJAX
		@GetMapping("project/driveFilterAjax/{prjtNo}")
		@ResponseBody
		public Map<String, Object> fileList(@PathVariable int prjtNo, String filter){
			List<FileVO> file = new ArrayList<> (fileservice.fileList(filter, prjtNo));
			Map<String, Object> map = new HashMap<>();
			
			map.put("file", file);
			map.put("files", fileservice.fileList(filter, prjtNo));
			
			return map;
		}

}