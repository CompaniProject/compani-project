package com.yedam.compani.business.service.impl;

import com.yedam.compani.business.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessServiceImplTest {

    @Autowired
    BusinessService businessService;

    //@Test
    void getBusinessStateList() {
        List<List<String>> stateList = businessService.getBusinessStateList(1);
        System.out.println(stateList);
    }

    //@Test
    void getBusinessAndLevelList(){
        List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(1);
        System.out.println(businessLevelList);
    }

    //@Test
    void updateProceedToDelay(){
        int cnt = businessService.updateProceedToDelay();
        System.out.println(cnt);
    }
    
    @Test
    // 개인 캘린더 테스트
    void personalCalendarList() {
    	List<Map<Object,Object>> list = businessService.getPersonalCalendarBusinessList();
    	System.out.println(list.get(0).get("bussNo"));
    }
}