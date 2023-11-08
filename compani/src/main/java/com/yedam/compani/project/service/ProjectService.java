package com.yedam.compani.project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberVO;

public interface ProjectService {

	public List<ProjectVO> getProjectList(MemberVO memberVO);
	public List<Map<Object,Object>> getProjectAndMemberList(String membId);
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO);
	public int updateFavorite(ProjectVO projectVO); 
	public int insertProject(ProjectVO projectVO);
	public Map<Object,Object> projectSelect(Integer prjtNo);
	public int updateProject(ProjectVO project); 
	public ProjectMemberVO updateCheck(Integer prjtNo, String membId);

}
