package com.yedam.compani.project.feedback.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;

public interface ProjectFeedbackMapper {
	// 프로젝트 이슈 피드백
	public List<IssueVO> selectProjectFeedbackIssueList();
	public List<Map<Object,Object>> selectListForLevel(int prjtNo);
}
