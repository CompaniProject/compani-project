package com.yedam.compani;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompaniApplicationTests {
    
    @Autowired
    StringEncryptor jasyptStringEncryptor;
    
	/*
	 * @Test void encryptionTest() { String[] dataList = {
	 * "net.sf.log4jdbc.sql.jdbcapi.DriverSpy",
	 * "jdbc:log4jdbc:oracle:thin:@192.168.0.107:1521/xe", "compani", "1234"
	 * 
	 * }; for(String data : dataList) { String encData =
	 * jasyptStringEncryptor.encrypt(data); System.out.println(encData); } }
	 */
}
