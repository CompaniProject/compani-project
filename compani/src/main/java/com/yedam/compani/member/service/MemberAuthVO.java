package com.yedam.compani.member.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
@Data
public class MemberAuthVO extends User {
	MemberVO vo;//보를 생성한거고
					//clubUserDetailsService 에서 받은 VO정보를 여기 밑에 넣어줌 = return new MemberAuthVO(vo.getMembId(), vo.getMembPwd(), auth, vo);
	public MemberAuthVO(String membId, String membPwd, Collection<? extends GrantedAuthority> authorities, MemberVO vo) {
		super(membId, membPwd, authorities);
		this.vo = vo; // 여기 위에 Member생성자의 vo에 현재의 vo넣는다. 즉, club의 vo => 현재 매개변수의 MemberVO vo => 현재의vo => 생성된 vo(가장첫줄 코드) 
	}

//	@Override
//	public boolean isEnabled() {
//		return vo.getMembAccp().equals("0C1") ;
//	}// 로그인 승인권한 설정

	
}
