package com.yedam.compani.issue.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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

@Controller
public class IssueController {
	@Autowired
	IssueService issueService;
	@Autowired
	IssueFileService issueFileService;
	@Autowired
	IssueHashtagService issueHashtagService;
	@Autowired
	FileUtils fileUtils;

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

		map.put("issu", issues);
		map.put("issues", issueService.getIssueList(pageNum, search));
		map.put("search", search);

		return map;
	}

	// 모달에서 이슈 등록
	@PostMapping("/ModalAjaxIssueInsert")
	public void modalIssueInsert(final IssueVO issueVO) {
		int issuNo = issueService.modalInsertIssue(issueVO);

		List<IssueFileVO> files = fileUtils.uploadFiles(issueVO.getFiles());
		issueFileService.modalInsertIssueFile(issuNo, files);		
	}
	
}
