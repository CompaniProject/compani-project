package com.yedam.compani.project.status.service;

public interface ProjectStatusService {
    public boolean insert(int prjtNo);
    public ProjectStatusVO getProjectStatus(Integer prjtNo);
}
