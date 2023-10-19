package com.yedam.compani.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@SpringBootTest
class ProjectServiceImplTest {
    @Autowired
    ProjectService projectService;

    @Test
    public void projectTest() {
        List<ProjectVO> projects = new ArrayList<ProjectVO>();
        projects = projectService.getProjectList();
        System.out.println(projects);
    }
    @Test
    public void projectAndMemberTest() {
        List<Map<Object,Object>> projects = new ArrayList<>();
        projects = projectService.getProjectAndMemberList();
        System.out.println(projects);
    }
}