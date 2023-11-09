package com.yedam.compani.session.service.impl;

import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final ProjectService projectService;

    @Override
    public void checkProjectSt(Integer prjtNo, HttpServletRequest request) {
        MemberVO memberVO = (MemberVO) request.getSession().getAttribute("loginInfo");
        String membId = memberVO.getMembId();

        ProjectMemberVO pmVO = projectService.updateCheck(prjtNo, membId);
        request.getSession().setAttribute("updateCheck", pmVO);
    }

    @Override
    public void setProjectInfo(Integer prjtNo, HttpServletRequest request) {
        Map<Object, Object> projectVO = projectService.projectSelect(prjtNo);
        request.getSession().setAttribute("projectVO", projectVO);
    }

    @Override
    public void setProjectSidebarList(HttpServletRequest request) {
        MemberVO memberVO = (MemberVO) request.getSession().getAttribute("loginInfo");
        List<Map<Object, Object>> projectList = projectService.getProjectAndMemberList(memberVO.getMembId());
        request.getSession().setAttribute("projectList", projectList);
    }

    @Override
    public void setProjectNo(Integer prjtNo,HttpServletRequest request) {
        request.getSession().setAttribute("prjtNo", prjtNo);
    }
}
