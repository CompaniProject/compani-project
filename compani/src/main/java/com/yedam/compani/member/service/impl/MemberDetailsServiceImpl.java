//package com.yedam.compani.member.service.impl;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import com.yedam.compani.member.service.MemberService;
//import com.yedam.compani.member.service.MemberVO;
//
//@Service
//public class MemberDetailsServiceImpl implements UserDetailsService {
//
//	private final MemberService service;
//
//	public MemberDetailsServiceImpl(MemberService ms) {
//		this.service = ms;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String userId) {
//		MemberVO vo = MemberVO
//				.builder()
//				.userId(userId)
//				.build();
//
//	}
//
//}
