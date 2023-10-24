package com.yedam.compani.company.status.service.impl;

import com.yedam.compani.company.status.mapper.CompanyStatusMapper;
import com.yedam.compani.company.status.service.CompanyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyStatusServiceImpl implements CompanyStatusService {

    @Autowired
    CompanyStatusMapper companyStatusMapper;

    @Override
    public void insert(String date) {
        companyStatusMapper.insert(date);
    }
}
