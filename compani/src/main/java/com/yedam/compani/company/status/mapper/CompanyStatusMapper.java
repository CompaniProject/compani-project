package com.yedam.compani.company.status.mapper;

import com.yedam.compani.company.status.service.CompanyStatusVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CompanyStatusMapper {
    public void insert(Date date);
    public CompanyStatusVO selectStatusForCurrentDate(String coCd);
    public CompanyStatusVO selectStatusForProjectDate(@Param("prjtNo") int prjtNo,@Param("coCd") String coCd);
}
