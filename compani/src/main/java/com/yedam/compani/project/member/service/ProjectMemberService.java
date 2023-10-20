package com.yedam.compani.project.member.service;

import java.util.List;
import java.util.Map;

public interface ProjectMemberService {
	
	public List<ProjectMemberVO> getProjectMemberCountList();
	public List<Map<Object,Object>> getProjectMemberList(Integer prjtNo);
	public List<Map<Object,Object>> getBusinessCompleteStatus(Integer prjtNo);
}
