package com.yedam.compani.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yedam.compani.member.service.MemberAuthVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		request.getSession().setAttribute("loginInfo", ((MemberAuthVO)auth.getPrincipal()).getVo());
		
		log.warn("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		log.warn("ROLE NAMES: " + roleNames);
		
		//if(roleNames.contains("ROLE_0C1")) {
		response.sendRedirect("/home");
		//	return;
		//} else	if(roleNames.contains("ROLE_0C2")) {
		//	response.sendRedirect("/home");
		//	return;
		//}
		
	}

}