package com.yedam.compani.business.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yedam.compani.business.service.BusinessService;

@Component
public class BusinessDelayUpdateScheduler {

	@Autowired
	BusinessService businessService;
	
	// 매일 자정에 동작
    @Scheduled(cron = "0 0 0 * * *")
    public void calculateCompanyStatus(){
    	businessService.updateProceedToDelay();
    }
}
