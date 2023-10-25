package com.yedam.compani.project.feedback.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.feedback.mapper.ProjectFeedbackMapper;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;

@Service
@RequiredArgsConstructor
public class ProjectFeedbackServiceImpl implements ProjectFeedbackService {

	@Override
	public List<IssueVO> getProjectFeedbackIssueList() {
		return projectFeedbackMapper.selectProjectFeedbackIssueList();
	}
	
	
}
