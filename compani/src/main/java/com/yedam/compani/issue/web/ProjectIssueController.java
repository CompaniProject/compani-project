package com.yedam.compani.issue.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.config.FileUtils;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;
import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.service.ProjectService;

import lombok.RequiredArgsConstructor;

/*
 * 
 * 작성자: 권오준
 * 작성일자: 2023/11/10
 * 내용: 프로젝트 이슈 게시판 조회, 등록
 */
@Controller
@RequiredArgsConstructor
public class ProjectIssueController {

	private final IssueService issueService;
	private final IssueFileService issueFileService;
	private final BusinessService businessService;
	private final ProjectService projectService;
	private final IssueHashtagService issueHashtagService;
	private final ProjectMemberService projectMemberService;

	@GetMapping("project/issues/{prjtNo}")
	public String projectIssueList(@PathVariable int prjtNo, 
							       				 String search, 
							       				 String keyword,
							       				 String filterType, 
								  @RequestParam( required = false, defaultValue = "1") int pageNum, 
								  				 Model model) {
		Map<Object, Object> pmap = projectService.projectSelect(prjtNo);
		
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getProjectIssueList(pageNum, search, keyword, prjtNo, filterType), 8);
		
		List<BusinessVO> findBuss = businessService.bussinessNameList(prjtNo);
		
		List<Map<String, String>> memvo = projectMemberService.projectMemberList(prjtNo);
		model.addAttribute("projects", pmap);
		model.addAttribute("memvo", memvo);
		model.addAttribute("bussNmList", findBuss);
		model.addAttribute("projectIssue", issues);

		return "project/project-issue";
	}

	// 프로젝트 내 이슈 리스트 조회 (Ajax)
	@GetMapping("project/aissues/{prjtNo}")
	@ResponseBody
	public Map<String, Object> projectIssue(@PathVariable int prjtNo,
														  String search, 
														  String keyword, 
														  String filterType,
											@RequestParam(required = false, defaultValue = "1") int pageNum) {
		Map<String, Object> map = new HashMap<>();
		
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getProjectIssueList(pageNum, search, keyword, prjtNo, filterType),
				8);		
		List<BusinessVO> findBuss = businessService.bussinessNameList(prjtNo);
		List<Map<String, String>> memvo = projectMemberService.projectMemberList(prjtNo);
		map.put("memvo", memvo);
		map.put("bussNmList", findBuss);
		map.put("pissue", issues);

		return map;
	}

	// 프로젝트 내 이슈 단건 조회 + 해당 이슈에 대한 모든 파일 조회 + 해시태그 조회
	@GetMapping("project/issues/{prjtNo}/{issuNo}")
	public String projectIssueSelect(@PathVariable final int prjtNo, 
									 @PathVariable final int issuNo, 
									 Model model) {
		IssueVO vo = issueService.findIssueById(issuNo);
		model.addAttribute("issueInfo", vo);

		List<IssueFileVO> list = issueFileService.findAllFileByIssuNo(issuNo);
		model.addAttribute("issueFile", list);
		
		List<IssueHashtagVO> hash = issueHashtagService.findAllHashtagsByIssuNo(issuNo);
		model.addAttribute("hash", hash);
		return "project/project-issue-info";
	}

	// 프로젝트게시판 내 이슈 삭제
	@PostMapping("project/issues/del")
	@ResponseBody
	public void projectIssueDelete(@RequestParam final int issuNo) {
		issueService.deleteIssue(issuNo);
	}
	
	// 프로젝트 게시판 내 이슈 등록
	@PostMapping("project/issues/save")
	@ResponseBody
	public void projectIssueSave(MultipartFile[] savefiles, 
								 IssueVO issueVO, 
								 String[] inserthashtagsp) {		
		// 이슈를 등록.
		 issueService.modalInsertIssue(issueVO);
		
		// 파일 업로드, 파일 DB에 저장
		List<IssueFileVO> uploadedFiles = new ArrayList<>();
		if (savefiles != null && savefiles.length > 0) {
				 uploadedFiles = FileUtils.uploadFiles(Arrays.asList(savefiles)); // 배열을  리스트로 변환하는 메서드. MultipartFile[] files -> List<MultipartFile>
				 issueFileService.modalInsertIssueFile(issueVO.getIssuNo(), uploadedFiles);
		}
		
		// 해시태그 저장
		issueHashtagService.modalInsertIssueHashtag(issueVO.getIssuNo(), Arrays.asList(inserthashtagsp));
		
	}	
}
