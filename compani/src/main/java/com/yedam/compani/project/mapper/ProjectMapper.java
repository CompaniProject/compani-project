package com.yedam.compani.project.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.project.service.ProjectVO;

public interface ProjectMapper {
	//전체조회
	public List<ProjectVO> selectAllProject();
	public List<Map<Object,Object>> selectProjectAndMemberList(String membId);
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO);
	public int updateFavorite(ProjectVO projectVO); 
	public int updateBusiness(BusinessVO businessVO);
}
