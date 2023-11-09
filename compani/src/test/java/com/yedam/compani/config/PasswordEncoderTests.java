package com.yedam.compani.config;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.compani.member.service.MemberService;

@SpringBootTest
public class PasswordEncoderTests {
  
   @Autowired
   private MemberService sv;

   @Autowired
   private PasswordEncoder passwordEncoder;
  
   @Test
   @DisplayName("패스워드 암호화 테스트")
   void passwordEncode() {
      // given
      String rawPassword = "1234";

      // when
      String encodedPassword = passwordEncoder.encode(rawPassword);
System.out.println(encodedPassword);
      // then
      assertAll(
            () -> assertNotEquals(rawPassword, encodedPassword),
            () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
      );
   } 
}
