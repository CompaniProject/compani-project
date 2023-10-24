package com.yedam.compani.company.status.service.impl;

import com.yedam.compani.company.status.mapper.CompanyStatusMapper;
import com.yedam.compani.company.status.service.CompanyStatusService;
import com.yedam.compani.company.status.service.CompanyStatusVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CompanyStatusServiceImpl implements CompanyStatusService {

    private final CompanyStatusMapper companyStatusMapper;

    @Override
    public void insert(Date date) {
        companyStatusMapper.insert(date);
    }

    @Override
    public CompanyStatusVO getStatusForCurrentDate(String coCd) {
        return companyStatusMapper.selectStatusForCurrentDate(coCd);
    }

    @Override
    public CompanyStatusVO getStatusForProjectDate(int prjtNo, String coCd) {
        return companyStatusMapper.selectStatusForProjectDate(prjtNo,coCd);
    }
}
