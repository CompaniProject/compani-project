package com.yedam.compani.company.status.service.impl;

import com.yedam.compani.company.status.mapper.CompanyStatusMapper;
import com.yedam.compani.company.status.service.CompanyStatusService;
import com.yedam.compani.company.status.service.CompanyStatusVO;
import com.yedam.compani.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CompanyStatusServiceImpl implements CompanyStatusService {

    private final CompanyStatusMapper companyStatusMapper;
    private final ProjectService projectService;

    @Override
    public void insert(Date date) {
        companyStatusMapper.insert(date);
    }
}
