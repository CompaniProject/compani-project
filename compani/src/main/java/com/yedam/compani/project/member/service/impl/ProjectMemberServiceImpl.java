package com.yedam.compani.project.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.project.member.mapper.ProjectMemberMapper;
import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

	@Autowired
	ProjectMemberMapper projectMemberMapper;
	@Override
	public List<ProjectMemberVO> getProjectMemberCountList() {
		
		return projectMemberMapper.selectAllProjectMemberCount();
	}

}
