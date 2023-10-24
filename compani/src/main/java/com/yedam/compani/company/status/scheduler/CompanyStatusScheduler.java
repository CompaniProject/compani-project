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

	// 매달 말일 오전 4시에 동작
    @Scheduled(cron = "0 0 4 L * ?")
    public void calculateCompanyStatus(){
        final Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
            companyStatusService.insert(new Date());
            // insert companyStatusInfo
        }
    }
}
