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
}
