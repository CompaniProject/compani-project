package com.yedam.compani.member.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberAuthVO extends User {
	public MemberAuthVO(String membId, String membPwd, Collection<? extends GrantedAuthority> authorities) {
		super(membId, membPwd, authorities);
	}

}
