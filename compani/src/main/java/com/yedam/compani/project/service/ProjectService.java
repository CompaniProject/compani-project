package com.yedam.compani.project.service;

import java.util.List;
import java.util.Map;

public interface ProjectService {

	public List<ProjectVO> getProjectList();
	public List<Map<Object,Object>> getProjectAndMemberList(String membId);
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO);
	public int updateFavorite(ProjectVO projectVO); 
	public int insertProject(ProjectVO projectVO);
	public Map<Object,Object> projectSelect(Integer prjtNo);
}
