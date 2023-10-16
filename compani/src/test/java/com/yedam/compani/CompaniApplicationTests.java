package com.yedam.compani;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompaniApplicationTests {
    
    @Autowired
    StringEncryptor jasyptStringEncryptor;
    
	@Test
	void contextLoads() {
	}
	
    @Test
    void encryptionTest() {
        String[] dataList = {
        };
        
        for(String data : dataList) {
            String encData = jasyptStringEncryptor.encrypt(data);
            System.out.println(encData);
        }
    }
}
