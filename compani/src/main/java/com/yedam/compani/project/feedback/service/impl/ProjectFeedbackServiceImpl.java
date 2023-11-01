package com.yedam.compani.project.feedback.service.impl;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.project.feedback.service.ProjectFeedbackService;

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

	
}
