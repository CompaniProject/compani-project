package com.yedam.compani.project.feedback.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.issue.service.IssueVO;

public interface ProjectFeedbackService {
	// 프로젝트 이슈 피드백
	public List<IssueVO> getProjectFeedbackIssueList();
	public List<Map<Object,Object>> getListForLevel(int prjtNo);
}
