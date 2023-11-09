package com.yedam.compani.project.status.service;

import com.yedam.compani.project.service.ProjectVO;

public interface ProjectStatusService {
    public boolean insert(ProjectVO projectVO);
    public ProjectStatusVO getProjectStatus(Integer prjtNo);
}
