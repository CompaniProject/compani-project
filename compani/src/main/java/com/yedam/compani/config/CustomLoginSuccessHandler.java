package com.yedam.compani.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yedam.compani.member.service.MemberAuthVO;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.service.ProjectService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
    ProjectService projectService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
			
		MemberVO vo = ((MemberAuthVO)auth.getPrincipal()).getVo();//오버라이드 된 getPrincipal()이 클래스 안에 모든 정보를 담고있고, 그 클래스(MemberAuthVO)는 첫줄에 MemberVO를 생성했고, 생성된 vo안의 값이 그 클래스의 @Data로 인해 
																	//getVo로 vo의 모든 정보를 불러오는데 그 정보를 현재의 MemberVO생성자에 넣는다.
		request.getSession().setAttribute("loginInfo", vo); // 세션에 loginInfo라는 변수에 vo값을 넣는다.
		
		// sidebar project list setting
        List<Map<Object, Object>> projectList = projectService.getProjectAndMemberList(vo.getMembId());
		request.getSession().setAttribute("projectList", projectList);



		//승인상태 체크
		if(vo.getMembAccp().equals("0C2")) {//맞는지 학인하고 맞으면 매핑(컨트롤러)시켜줌
			response.sendRedirect("/standBy");
			return; 
		}
		
		log.warn("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		log.warn("ROLE NAMES: " + roleNames);
		if(roleNames.contains("ROLE_0A2") || roleNames.contains("ROLE_0A3") || roleNames.contains("ROLE_0A4")) {
			response.sendRedirect("/home");
			return;
		} else if(roleNames.contains("ROLE_0A1")) {
			response.sendRedirect("/masterHome");
			return;
		}
		/*
			 * else if() { response.sendRedirect("/home"); return; }
			 */
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+roleNames);//권한값 확인
	}

}
