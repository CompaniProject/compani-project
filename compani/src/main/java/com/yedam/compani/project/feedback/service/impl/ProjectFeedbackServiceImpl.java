package com.yedam.compani.project.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.feedback.mapper.ProjectFeedbackMapper;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;

@Service
public class ProjectFeedbackServiceImpl implements ProjectFeedbackService {
	
	@Autowired
	private ProjectFeedbackMapper projectFeedbackMapper;

	@Override
	public List<IssueVO> getProjectFeedbackIssueList() {
		return projectFeedbackMapper.selectProjectFeedbackIssueList();
	}
	
	
}
