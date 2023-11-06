package com.yedam.compani.project.member.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.project.service.ProjectFormVO;

public interface ProjectMemberService {
	
	public List<ProjectMemberVO> getProjectMemberCountList();
	public List<Map<Object,Object>> getProjectMemberList(Integer prjtNo);
	public List<Map<Object,Object>> getBusinessCompleteStatus(Integer prjtNo);
	public int insertProjectMember(ProjectFormVO formVO);
	public List<Map<String,String>> projectMemberList(Integer prjtNo);
	public int deleteProjectMember(ProjectFormVO formVO);
	public ProjectMemberVO projectMemberSelect(int prjtNo, String membId);
}
