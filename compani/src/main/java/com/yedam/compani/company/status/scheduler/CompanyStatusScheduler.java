package com.yedam.compani.company.status.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CompanyStatusScheduler {

	// 매달 1일 오전 4시에 동작
    @Scheduled(cron = "0 0 4 1 * *")
    public void calculateCompanyStatus(){
    	// insert status data - per company, to project_status
    }
}
