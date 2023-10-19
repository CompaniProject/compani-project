package com.yedam.compani.project.member.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.project.member.service.ProjectMemberVO;

public interface ProjectMemberMapper {
	
	public List<ProjectMemberVO> selectAllProjectMemberCount();
	public List<Map<Object,Object>> selectProjectMemberList(Integer prjtNo);
	public int updateFavorite(ProjectMemberVO projectMemberVO);
}
