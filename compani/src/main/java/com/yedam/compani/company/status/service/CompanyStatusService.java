package com.yedam.compani.company.status.service;

import java.util.Date;

public interface CompanyStatusService {
    public void insert(Date date);
    public CompanyStatusVO getStatusForCurrentDate(String coCd);
    public CompanyStatusVO getStatusForProjectDate(int prjtNo, String coCd);
}
