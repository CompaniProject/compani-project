package com.yedam.compani.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.project.mapper.ProjectMapper;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> getProjectList() {
		
		return projectMapper.selectAllProject();
	}

	@Override
	public List<Map<Object, Object>> getProjectAndMemberList() {
		return projectMapper.selectProjectAndMemberList();
	}

}
