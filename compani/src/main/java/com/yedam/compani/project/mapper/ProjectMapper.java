package com.yedam.compani.project.mapper;

import java.util.List;

import com.yedam.compani.project.service.ProjectVO;

public interface ProjectMapper {
	//전체조회
	public List<ProjectVO> selectAllProject();
}
