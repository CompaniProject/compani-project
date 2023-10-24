package com.yedam.compani.company.status.scheduler;

import com.yedam.compani.company.status.service.CompanyStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CompanyStatusScheduler {

    private final CompanyStatusService companyStatusService;
    public void calculateCompanyStatus(){
    	// insert status data - per company, to project_status
    }
}
