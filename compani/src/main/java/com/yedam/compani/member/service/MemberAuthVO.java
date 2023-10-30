package com.yedam.compani.member.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
@Data
public class MemberAuthVO extends User {
	MemberVO vo;
	
	public MemberAuthVO(String membId, String membPwd, Collection<? extends GrantedAuthority> authorities, MemberVO vo) {
		super(membId, membPwd, authorities);
		this.vo = vo;
	}

	@Override
	public boolean isEnabled() {
		return vo.getMembAccp().equals("0C1") ;
	}// 로그인 승인권한 설정

	
}
