package com.yedam.compani.project.feedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.compani.project.feedback.mapper.ProjectFeedbackMapper;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectFeedbackServiceImpl implements ProjectFeedbackService {

	private final ProjectFeedbackMapper projectFeedbackMapper;

	@Override
	public List<Map<Object,Object>> getListForLevel(int prjtNo) {
		return projectFeedbackMapper.selectListForLevel(prjtNo);
	}

	@Override
	public boolean insert(ProjectFeedbackVO projectFeedbackVO) {
		return (projectFeedbackMapper.insert(projectFeedbackVO) == 1);
	}

	@Override
	public boolean update(ProjectFeedbackVO projectFeedbackVO) {
		return (projectFeedbackMapper.update(projectFeedbackVO) == 1);
	}

	@Override
	public boolean isHaveChildrenShow(int prjtFdbkNo) {
		int count = projectFeedbackMapper.selectChildrenShowCount(prjtFdbkNo)-1;
		return (count == 0);
	}


}
