package com.yedam.compani.project.feedback.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.issue.service.IssueVO;

public interface ProjectFeedbackService {
	public List<Map<Object,Object>> getListForLevel(int prjtNo);
	public boolean insert(ProjectFeedbackVO projectFeedbackVO);
	public boolean update(ProjectFeedbackVO projectFeedbackVO);
}
