package com.yedam.compani.project.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.member.service.MemberVO;

public interface ProjectService {

	public List<ProjectVO> getProjectList(MemberVO memberVO);
	public List<Map<Object,Object>> getProjectAndMemberList(String membId);
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO);
	public int updateFavorite(ProjectVO projectVO); 
	public int insertProject(ProjectVO projectVO);
	public Map<Object,Object> projectSelect(Integer prjtNo);
	public int updateProject(ProjectVO project); 

}
