package com.yedam.compani.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.mapper.ProjectMapper;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> getProjectList(MemberVO memberVO) {
		
		return projectMapper.selectAllProject(memberVO);
	}

	@Override
	public List<Map<Object, Object>> getProjectAndMemberList(String membId) {
		return projectMapper.selectProjectAndMemberList(membId);
	}

	@Override
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO) {
		
		return projectMapper.getProjectStateList(projectVO);
	}

	@Override
	public int updateFavorite(ProjectVO projectVO) {
		return projectMapper.updateFavorite(projectVO);
	}

	@Override
	public int insertProject(ProjectVO projectVO) {
		
		return projectMapper.insertProject(projectVO);
	}

	@Override
	public Map<Object, Object> projectSelect(Integer prjtNo) {
		
		return projectMapper.projectSelect(prjtNo);
	}

	@Override
	public int updateProject(ProjectVO project) {
		
		return projectMapper.updateProject(project);
	}
	
	

}
