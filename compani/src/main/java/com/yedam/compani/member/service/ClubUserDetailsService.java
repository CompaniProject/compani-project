package com.yedam.compani.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.compani.member.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ClubUserDetailsService implements UserDetailsService{
	
	@Autowired
	MemberMapper map;
	
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
		log.info("ClubUserDetailsService loadUserByUserid " + userId);
		
		MemberVO vo = new MemberVO();
		vo.setMembId(userId);
		vo = map.selectMemberInfo(vo);
		
		if(vo==null) {
			throw new UsernameNotFoundException("Check id");
		}
		List<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(vo.getPermNo()));
		
		return new MemberAuthVO(vo.getMembId(), vo.getMembPwd(), auth, vo);  //얘로인해 MemberAuthVO에 MemberVO 정보가 전달됨
	}
}
