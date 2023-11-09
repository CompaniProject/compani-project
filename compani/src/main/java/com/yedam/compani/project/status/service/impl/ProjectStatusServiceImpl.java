package com.yedam.compani.project.status.service.impl;

import com.yedam.compani.project.service.ProjectVO;
import com.yedam.compani.project.status.mapper.ProjectStatusMapper;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectStatusServiceImpl implements ProjectStatusService {

    private final ProjectStatusMapper projectStatusMapper;

    @Override
    public boolean insert(ProjectVO projectVO) {
		if (projectVO.stateCheckEnd() == false) {
			return false;
		}
		
        return (projectStatusMapper.insert(projectVO.getPrjtNo()) == 1);
    }

    @Override
    public ProjectStatusVO getProjectStatus(Integer prjtNo) {
        return projectStatusMapper.selectProjectStatus(prjtNo);
    }
}
