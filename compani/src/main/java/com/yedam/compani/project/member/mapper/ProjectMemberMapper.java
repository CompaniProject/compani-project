package com.yedam.compani.project.member.mapper;

import java.util.List;

import com.yedam.compani.project.member.service.ProjectMemberVO;

public interface ProjectMemberMapper {
	
	public List<ProjectMemberVO> selectAllProjectMemberCount();
	public int updateFavorite(ProjectMemberVO projectMemberVO);
}
