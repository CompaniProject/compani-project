package com.yedam.compani.session.service;

import javax.servlet.http.HttpServletRequest;

public interface SessionService {
    // prjtNo, membId 프로젝트 참여자 인지 확인하고 프로젝트 완료 체크
    public void checkProjectSt(Integer prjtNo, HttpServletRequest request);
    // 헤더 단건 조회
    public void setProjectInfo(Integer prjtNo, HttpServletRequest request);
    // sidebar project list 설정
    public void setProjectSidebarList(HttpServletRequest request);
    public void setProjectNo(Integer prjtNo,HttpServletRequest request);
}
