package com.yedam.compani.business.service.impl;

import com.yedam.compani.business.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessServiceImplTest {

    @Autowired
    BusinessService businessService;

    //@Test
    void getBusinessStateList() {
        List<Map<Object,Object>> stateList = businessService.getBusinessStateList(1);
        System.out.println(stateList);
    }

    @Test
    void getBusinessAndLevelList(){
        List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(1);
        System.out.println(businessLevelList);
    }
}