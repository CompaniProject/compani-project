package com.yedam.compani.issue.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.config.FileUtils;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;
import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.paging.SearchDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IssueController {
	
	private final IssueService issueService;
	private final IssueFileService issueFileService;	
	private final IssueHashtagService issueHashtagService;
	private final FileUtils fileUtils;

	// 모달에서 이슈리스트 나오기
	@GetMapping("/ModalIssueList")
	public String modalIssueList(@ModelAttribute SearchDto search, IssueVO issueVO,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getIssueList(pageNum, search), 8);
		Page<IssueVO> vo = issueService.getIssueList(pageNum, search);

		model.addAttribute("issue", issues);
		model.addAttribute("issueList", vo);
		model.addAttribute("search", search);

		return "index";
	}

	// 페이징 또는 검색 시 ajax 처리
	@GetMapping("/ModalAjaxIssueList")
	@ResponseBody
	public Map<String, Object> modalIssueList(@ModelAttribute SearchDto search,
			@RequestParam(required = false, defaultValue = "1") int pageNum) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getIssueList(pageNum, search), 8);
		Map<String, Object> map = new HashMap<>();

		map.put("issue", issues);
		map.put("issues", issueService.getIssueList(pageNum, search));
		map.put("search", search);

		return map;
	}
	
	// 모달 이슈 단건 조회 + 해당 이슈에 대한 모든 파일 조회
	@RequestMapping("/ModalIssueInfo")
	@ResponseBody
	public Map<String, Object> modalIssueSelect(final int issuNo) {
			Map<String, Object> map = new HashMap<>();
			IssueVO vo = issueService.findIssueById(issuNo);
			map.put("issueInfo", vo);
			
			List<IssueFileVO> list = issueFileService.selectIssueFile(issuNo);
			
			map.put("issueFile", list);									
			return map;
	}
	
	
	// 모달에서 이슈 등록
	@PostMapping("/ModalAjaxIssueInsert")
	@ResponseBody
	public void modalIssueInsert(MultipartFile[] files, IssueVO issueVO) {
		// 이슈를 등록.
		int issuNo = issueService.modalInsertIssue(issueVO);
		
		// 파일 업로드, 파일 DB에 저장
		List<IssueFileVO> uploadedFiles = new ArrayList<>();
		if (files != null && files.length > 0) {
				uploadedFiles = fileUtils.uploadFiles(Arrays.asList(files)); // 배열을  리스트로 변환하는 메서드. MultipartFile[] files -> List<MultipartFile>
				 issueFileService.modalInsertIssueFile(issuNo, uploadedFiles);
		}
				
	}
	
	// 모달에서 이슈 수정
	@PostMapping("/ModalIssueUpdate")
	@ResponseBody
	public IssueVO modalIssueUpdate(@RequestBody final IssueVO params) {
		issueService.updateIssue(params);
		return issueService.findIssueById(params.getIssuNo());
	}
	
}
