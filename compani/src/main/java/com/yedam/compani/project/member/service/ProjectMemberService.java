package com.yedam.compani.project.member.service;

import java.util.List;

public interface ProjectMemberService {
	
	public List<ProjectMemberVO> getProjectMemberCountList();
	public int updateFavorite(ProjectMemberVO projectMemberVO);
}
