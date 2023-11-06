package com.yedam.compani.member.web;

/*
 * 작성자 : 이상길
 * 작성일자 : 
 * Member 관리 : 회원가입, 정보수정, 로그인 
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.company.service.CompanyVO;
import com.yedam.compani.member.service.MemberAuthVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MemberController {
	@Autowired
	MemberService service;
	@Autowired
	CompanyService serviceC;

	HttpServletRequest request;
	HttpSession session;

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String memberLoginForm() {
		return "member/memberLoginForm";
	}

	// 가입 후 대기
	@GetMapping("/standBy")
	public String memberStandByForm() {
		return "member/memberStandBy";
	}

	// 가입완료
	@GetMapping("/complete")
	public String memberSignUpComplete() {
		return "member/memberSignUpped";
	}

	// 회원가입 유형
	@GetMapping("/signUp")
	public String signUpPage() {
		return "member/signUp";
	}

	// 사원 등록 폼
	@GetMapping("/memberSignUp")
	public String memberSignUpForm(CompanyVO vo) {
		return "member/memberSignUp";
	}
	// 사원 등록 폼
	@PostMapping("/memberSignUp")
	public String memberSignUpForm2(CompanyVO vo) {
		return "member/memberSignUp";
	}
	// 아이디 중복체크용
	@PostMapping("/memberIdList")
	@ResponseBody
	public Map<String, Object> getMemberIdLists() {
		Map<String, Object> membIdList = new HashMap<>();
		membIdList.put("result", true);
		membIdList.put("data", service.getMemberIdList());
		return membIdList;
	}

	// 가입 서브밋
	@PostMapping("/SignUpped")
	public String memberSignUpped(MemberVO membVO, CompanyVO compVO, Model model) {
		if (membVO.getPermNo().equals("2")) {
			if (serviceC.setCompanyInfo(compVO) > 0) {
				if (service.setMemberInfo(membVO) > 0) {
					return "redirect:complete";
				} else {
					model.addAttribute("notice", "회원가입(사원부분)이 정상적으로 이루어지지 않았습니다.");
					return "memberSignUp";
				}
			} else {
				model.addAttribute("notice", "회원가입(기업부분)이 정상적으로 이루어지지 않았습니다.");
				return "memberSignUp";
			}
		} else {
			if (service.setMemberInfo(membVO) > 0) {
				return "redirect:complete";
			} else {
				model.addAttribute("notice", "회원가입(사원부분)이 정상적으로 이루어지지 않았습니다.");
				return "companySignUp";
			}
		}
	}

	// 수정
	@GetMapping("memberEditForm")
	public String memberEditForm() {
		return "member/memberEditInfo";
	}

	//대철's
	@GetMapping("memSearchAjax")
	@ResponseBody
	public List<MemberVO> memberSearchAjax(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		
		int prjtNo = (int) request.getSession().getAttribute("prjtNo"); 
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("loginInfo"); 
		String coCd = memberVO.getCoCd();
		System.out.println(prjtNo);
		System.out.println(coCd);
		map.put("prjtNo", prjtNo);
		map.put("coCd", coCd);
		List<MemberVO> List = service.getMemberList(map);
	
		return List;
	}

	
	// set 세션 로그인 정보
	@PostMapping("/memberInfo")
	@ResponseBody
	public MemberVO memberInfo(@AuthenticationPrincipal MemberAuthVO vo) {
		MemberVO membVO = new MemberVO();
		membVO.setMembId(vo.getUsername());
		membVO = service.getMemberInfo(membVO);
		return membVO;
	}

	// 정보수정
	@PostMapping("/memberEditInfo")
	public String editMemberInfo(MemberVO vo) {
		service.editMemberInfo(vo);
		return "redirect:memberEditForm";
	}

	// 비번수정
	@PostMapping("/memberEditPwd")
	public String editMemberPwd(MemberVO vo) {
		service.editMemberPwd(vo);
		return "redirect:memberEditForm";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String filename = uploadfile.getOriginalFilename();
			String directory = "/images/member";
			String filepath = Paths.get(directory, filename).toString();

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	} 
	// method uploadFile
	/////////////////////////////////////////////////////////

	//사이드 프로젝트 등록 모달 ajax
	@PostMapping("/prjtInsert")
	@ResponseBody
	public Map<String,Object> prjtModalAjax(MemberVO memberVO){

		Map<String, Object> map = new HashMap<>();
		
		//로그인 한 멤버 단건 조회 
		MemberVO membVO = service.getMemberInfo(memberVO);
		map.put("member", membVO);
		//cocd 회사 멤버 리스트 
		List<MemberVO> list = service.memberList(membVO);
		map.put("memberList", list);
		return map;
	}
	
	// 김연규, 2023-11-03, 마스터 멤버리스트
	@GetMapping("/masterHome")
	public String masterList(Model model) {
		List<CompanyVO> companyList = serviceC.companyAllList();
		List<Map<Object, Object>> memberList = service.masterMemberList();
		model.addAttribute("masterCompanyList", companyList);
		model.addAttribute("masterMemberList", memberList);
		return "master/master-home";
	}
	
}
