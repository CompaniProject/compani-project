package com.yedam.compani.project.feedback.service;

import java.util.List;

import com.yedam.compani.issue.service.IssueVO;

public interface ProjectFeedbackService {
	// 프로젝트 이슈 피드백
	public List<IssueVO> getProjectFeedbackIssueList();
}
