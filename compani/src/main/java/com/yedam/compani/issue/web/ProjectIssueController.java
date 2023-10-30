package com.yedam.compani.issue.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.paging.SearchDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectIssueController {

	private final IssueService issueService;
	
	@GetMapping("/project/issues/{prjtNo}")
	public String projectIssueList(@PathVariable int prjtNo, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getProjectIssueList(pageNum, search, keyword, prjtNo), 8);
		Page<IssueVO> vo = issueService.getProjectIssueList(pageNum, search, keyword, prjtNo);
		
		model.addAttribute("projectIssue", issues);
		model.addAttribute("prjoectIssueList", vo);
		model.addAttribute("search", search);
		
		return "project/project-issue";
	}
	
	// 프로젝트 내 이슈 리스트 조회 (Ajax)
	@GetMapping("/project/aissues/{prjtNo}")
	@ResponseBody
	public Map<String, Object> projectIssue(@PathVariable int prjtNo, String search, String keyword, 
			@RequestParam(required = false, defaultValue = "1") int pageNum) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getProjectIssueList(pageNum, search, keyword, prjtNo), 8);
		Page<IssueVO> vo = issueService.getProjectIssueList(pageNum, search, keyword, prjtNo);
		Map<String, Object> map = new HashMap<>();
		
		map.put("pissue", issues);
		map.put("projectIssueList", vo);

		return map;
	}
}
