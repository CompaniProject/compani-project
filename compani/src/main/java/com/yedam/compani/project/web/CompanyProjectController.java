package com.yedam.compani.project.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompanyProjectController {

	private final ProjectService projectService;
	
	//회사 프로젝트 게시판 
	@GetMapping("/company/project/{coCd}")
	public String projectBoard(@PathVariable String coCd, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
		PageInfo<ProjectVO> projects = new PageInfo<>(projectService.getCompanyProjectList(pageNum, search, keyword, coCd), 8);
		Page<ProjectVO> vo = projectService.getCompanyProjectList(pageNum, search, keyword, coCd);
		
		model.addAttribute("companyProject", projects);
		model.addAttribute("companyProjectList", vo);

		return "company/company-project";
	}

	@GetMapping("/company/projects/{coCd}")
	@ResponseBody
	public Map<String, Object> companyProject(@PathVariable String coCd, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum) {
		PageInfo<ProjectVO> projects = new PageInfo<>(projectService.getCompanyProjectList(pageNum, search, keyword, coCd), 8);
		Page<ProjectVO> vo = projectService.getCompanyProjectList(pageNum, search, keyword, coCd);
		Map<String, Object> map = new HashMap<>();

		map.put("cproject", projects);
		map.put("cprojectList", vo);

		return map;
	}
}
