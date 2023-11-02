package com.yedam.compani.issue.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectIssueController {

	private final IssueService issueService;
	private final IssueFileService issueFileService;
	private final com.yedam.compani.config.FileUtils fileUtils;
	private final BusinessService businessService;

	@GetMapping("/project/issues/{prjtNo}")
	public String projectIssueList(@PathVariable int prjtNo, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getProjectIssueList(pageNum, search, keyword, prjtNo),
				8);
		Page<IssueVO> vo = issueService.getProjectIssueList(pageNum, search, keyword, prjtNo);

		List<BusinessVO> findBuss = businessService.bussinessNameList(prjtNo);

		model.addAttribute("bussNmList", findBuss);
		model.addAttribute("projectIssue", issues);
		model.addAttribute("projectIssueList", vo);
		model.addAttribute("search", search);

		return "project/project-issue";
	}

	// 프로젝트 내 이슈 리스트 조회 (Ajax)
	@GetMapping("/project/aissues/{prjtNo}")
	@ResponseBody
	public Map<String, Object> projectIssue(@PathVariable int prjtNo, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getProjectIssueList(pageNum, search, keyword, prjtNo),
				8);
		Page<IssueVO> vo = issueService.getProjectIssueList(pageNum, search, keyword, prjtNo);
		Map<String, Object> map = new HashMap<>();
		List<BusinessVO> findBuss = businessService.bussinessNameList(prjtNo);

		map.put("bussNmList", findBuss);
		map.put("pissue", issues);
		map.put("projectIssueList", vo);

		return map;
	}

	// 프로젝트 내 이슈 단건 조회 + 해당 이슈에 대한 모든 파일 조회
	@GetMapping("/project/issues/{prjtNo}/{issuNo}")
	public String projectIssueSelect(@PathVariable final int prjtNo, @PathVariable final int issuNo, Model model) {

		IssueVO vo = issueService.findIssueById(issuNo);
		System.out.println(vo);
		model.addAttribute("issueInfo", vo);

		List<IssueFileVO> list = issueFileService.findAllFileByIssuNo(issuNo);
		model.addAttribute("issueFile", list);

		return "project/project-issue-info";
	}

	// 프로젝트게시판 내 이슈 삭제
	@PostMapping("/project/issues/del")
	@ResponseBody
	public void projectIssueDelete(@RequestParam final int issuNo) {
		issueService.deleteIssue(issuNo);
	}
	
	// 프로젝트 게시판 내 이슈 등록
	@PostMapping("/project/issues/save")
	@ResponseBody
	public void projectIssueSave(MultipartFile[] savefiles, IssueVO issueVO) {
		
		// 이슈를 등록.
		int issuNo = issueService.modalInsertIssue(issueVO);
		
		// 파일 업로드, 파일 DB에 저장
		List<IssueFileVO> uploadedFiles = new ArrayList<>();
		if (savefiles != null && savefiles.length > 0) {
				 uploadedFiles = fileUtils.uploadFiles(Arrays.asList(savefiles)); // 배열을  리스트로 변환하는 메서드. MultipartFile[] files -> List<MultipartFile>
				 issueFileService.modalInsertIssueFile(issuNo, uploadedFiles);
		}
		
	}	
}
