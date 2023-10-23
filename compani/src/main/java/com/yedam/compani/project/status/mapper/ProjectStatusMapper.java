package com.yedam.compani.project.status.mapper;

import com.yedam.compani.project.status.service.ProjectStatusVO;

public interface ProjectStatusMapper {
    public int insert(int prjtNo);
    public ProjectStatusVO selectProjectStatus(Integer prjtNo);
}
