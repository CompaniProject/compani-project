package com.yedam.compani.project.feedback.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;

public interface ProjectFeedbackMapper {
	public List<Map<Object,Object>> selectListForLevel(int prjtNo);
	public int insert(ProjectFeedbackVO projectFeedbackVO);
	public int update(ProjectFeedbackVO projectFeedbackVO);
}
