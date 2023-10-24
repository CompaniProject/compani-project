package com.yedam.compani.project.feedback.mapper;

import java.util.List;

import com.yedam.compani.issue.service.IssueVO;

public interface ProjectFeedbackMapper {
	// 프로젝트 이슈 피드백
	public List<IssueVO> selectProjectFeedbackIssueList();
}
