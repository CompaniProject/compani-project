package com.yedam.compani.project.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;

@Controller
public class ProjectMemberController {
	
	@Autowired
	ProjectMemberService projectMemberService;
	

	@PostMapping("projectFavoriteUpdate")
	@ResponseBody
	public Map<String, Object> projectFavoriteUpdate(ProjectMemberVO projectMemberVO){
		
		System.out.println(projectMemberVO.getPrjtFav());
		int n = projectMemberService.UpdateFavorite(projectMemberVO);
		
		if( n == 1 ) {
			System.out.println("성공");
		}
		else {
			System.out.println("실패");
		}
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", true);
	
		
		return map;
	}
	

}
