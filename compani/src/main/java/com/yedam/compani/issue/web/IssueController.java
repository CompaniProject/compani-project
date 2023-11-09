package com.yedam.compani.issue.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;
import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IssueController {
	
	private final IssueService issueService;
	private final IssueFileService issueFileService;
	private final ProjectStatusService projectStatusService;
	private final IssueHashtagService issueHashtagService;
	private final com.yedam.compani.config.FileUtils fileUtils;
	private final BusinessService businessService;
	private final ProjectMemberService projectMemberService;
	private final ProjectFeedbackService projectFeedbackService;
	
	// 모달에서 이슈리스트 나오기
	@GetMapping("/ModalIssueList/{bussNo}")
	public String modalIssueList(@PathVariable final int bussNo, String searchBI, String keyword, IssueVO issueVO, String filterTypeM,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getIssueList(pageNum, searchBI, keyword, bussNo, filterTypeM), 8);
		Page<IssueVO> vo = issueService.getIssueList(pageNum, searchBI, keyword, bussNo, filterTypeM);
		
		BusinessVO bussvo = businessService.businessSelect(bussNo);
		int prjtNo = bussvo.getPrjtNo();			
		List<Map<String, String>> memvo = projectMemberService.projectMemberList(prjtNo);
		
		model.addAttribute("memvomi", memvo);
		model.addAttribute("issue", issues);
		model.addAttribute("issueList", vo);
		model.addAttribute("search", searchBI);
		model.addAttribute("buss", bussvo);
		return "modal/modal-issue";
	}

	// 페이징 또는 검색 시 ajax 처리
	@GetMapping("/ModalAjaxIssueList/{bussNo}")
	@ResponseBody
	public Map<String, Object> modalIssueList(@PathVariable final int bussNo, String searchBI, String keyword, String filterTypeM,
			@RequestParam(required = false, defaultValue = "1") int pageNum) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getIssueList(pageNum, searchBI, keyword, bussNo, filterTypeM), 8);
		Map<String, Object> map = new HashMap<>();
		
		BusinessVO bussvo = businessService.businessSelect(bussNo);
		int prjtNo = bussvo.getPrjtNo();			
		List<Map<String, String>> memvo = projectMemberService.projectMemberList(prjtNo);
		
		map.put("memvomi", memvo);
		map.put("issue", issues);
		map.put("issues", issueService.getIssueList(pageNum, searchBI, keyword, bussNo, filterTypeM));
		map.put("search", searchBI);
		map.put("buss", bussvo);
		return map;
	}
	
	// 모달 이슈 단건 조회 + 해당 이슈에 대한 모든 파일 조회 + 해당 이슈에 대한 모든 해시태그 조회
	@RequestMapping("/ModalIssueInfo")
	@ResponseBody
	public Map<String, Object> modalIssueSelect(final int issuNo) {
			Map<String, Object> map = new HashMap<>();
			IssueVO vo = issueService.findIssueById(issuNo);
			map.put("issueInfo", vo);
			
			List<IssueFileVO> list = issueFileService.findAllFileByIssuNo(issuNo);			
			map.put("issueFile", list);
			
			List<IssueHashtagVO> hash = issueHashtagService.findAllHashtagsByIssuNo(issuNo);
			map.put("issueHash", hash);
			return map;
	}
	
	
	// 모달에서 이슈 등록
	@PostMapping("/ModalAjaxIssueInsert")
	@ResponseBody
	public void modalIssueInsert(MultipartFile[] files, final IssueVO issueVO, String[] inserthashtags) {
		// 이슈를 등록.
		int issuNo = issueService.modalInsertIssue(issueVO);
		
		// 파일 업로드, 파일 DB에 저장
		List<IssueFileVO> uploadedFiles = new ArrayList<>();
		if (files != null && files.length > 0) {
				 uploadedFiles = fileUtils.uploadFiles(Arrays.asList(files)); // 배열을  리스트로 변환하는 메서드. MultipartFile[] files -> List<MultipartFile>
				 issueFileService.modalInsertIssueFile(issuNo, uploadedFiles);
		}
		// 해시태그 저장
		issueHashtagService.modalInsertIssueHashtag(issuNo, Arrays.asList(inserthashtags));
				
	}
	
	// 모달에서 이슈 수정
	@PostMapping("/ModalIssueUpdate")
	@ResponseBody
	public IssueVO modalIssueUpdate(final IssueVO issueVO, MultipartFile[] editFiles, String[] edithashtags) {

		// 1. 이슈글 정보 수정
		int issuNo = issueService.updateIssue(issueVO);

		// 2. 파일 업로드 (to disk & to database)
		List<IssueFileVO> uploadedFiles = new ArrayList<>();
		if(editFiles != null && editFiles.length > 0) {
			uploadedFiles = fileUtils.uploadFiles(Arrays.asList(editFiles));
			issueFileService.modalInsertIssueFile(issueVO.getIssuNo(), uploadedFiles);
		}

		// 3. 삭제할 파일 정보 조회 (from database)
		List<IssueFileVO> deleteFiles = issueFileService.findAllByIds(issueVO.getRemoveFileIds());

		// 4. 파일 삭제 (from disk)
		fileUtils.deleteFiles(deleteFiles);

		// 5. 파일 삭제 (from database)
		issueFileService.deleteAllFileByIds(issueVO.getRemoveFileIds());
		
		// 6. 해시태그 수정 ( db에서 해당 이슈에 있는 모든 해시태그를 삭제 후 새로 등록 )
		if(CollectionUtils.isEmpty(Arrays.asList(edithashtags))) {
			issueHashtagService.deleteHashtagbyId(issuNo);
		} else {
		issueHashtagService.modalEditIssueHashtag(issuNo, Arrays.asList(edithashtags));
		}
		return issueService.findIssueById(issueVO.getIssuNo());
		
		
	}
	
	// 모달에서 이슈 삭제
	@PostMapping("/ModalIssueDelete")
	@ResponseBody
	public void modalIssueDelete(@RequestParam final int issuNo) {
		issueService.deleteIssue(issuNo);
	}
	// 김연규, 2023-10-25, 프로젝트 이슈 피드백
	@GetMapping("/project/feedback/{prjtNo}/issue")
	public String projectFeedbackIssueList(@PathVariable int prjtNo, Model model) {
		
		List<IssueVO> list = issueService.getProjectFeedbackIssueList();
		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		List<Map<Object,Object>> feedbackList = projectFeedbackService.getListForLevel(prjtNo);
		
		model.addAttribute("projectFeedbackIssueList", list);
		model.addAttribute("projectStatus",projectStatus);
		model.addAttribute("projectFeedbackList",feedbackList);
		return "project/feedback-issue";
	}
	
}
