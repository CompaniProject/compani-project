package com.yedam.compani.project.service.impl;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.project.mapper.ProjectMapper;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> getProjectList() {
		
		return projectMapper.selectAllProject();
	}

	@Override
	public List<Map<Object, Object>> getProjectAndMemberList() {
		return projectMapper.selectProjectAndMemberList();
	}

	@Override
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO) {
		
		return projectMapper.getProjectStateList(projectVO);
	}

	@Override
	public int updateFavorite(ProjectVO projectVO) {
		return projectMapper.updateFavorite(projectVO);
	}

}
