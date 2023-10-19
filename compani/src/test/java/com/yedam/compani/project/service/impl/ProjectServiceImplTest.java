package com.yedam.compani.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@SpringBootTest
class ProjectServiceImplTest {
    @Autowired
    ProjectService projectService;
    
    @Autowired
    ProjectMemberService projectMemberService;

    @Test
    public void projectTest() {
        List<ProjectVO> projects = new ArrayList<ProjectVO>();
        projects = projectService.getProjectList();
        System.out.println(projects);
        
       
        
    }
}