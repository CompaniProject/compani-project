package com.yedam.compani.project.member.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@Controller
public class ProjectMemberController {

	@Autowired
	ProjectMemberService projectMemberService;
	
	@Autowired
	ProjectService projectService;

	@PostMapping("favAjax")
	@ResponseBody
	public Map<String, Object> favAjax(ProjectMemberVO projectMemberVO) {

		System.out.println(projectMemberVO.getPrjtNo());
		int n = projectMemberService.updateFavorite(projectMemberVO);

		if (n >= 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}

		Map<String, Object> map = new HashMap<>();

		List<ProjectVO> projectVO = projectService.getProjectList();
		map.put("result", true);
		map.put("project", projectVO);

		return map;
	}

}
