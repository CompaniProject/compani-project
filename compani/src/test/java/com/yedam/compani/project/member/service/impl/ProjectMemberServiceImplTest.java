package com.yedam.compani.project.member.service.impl;

import com.yedam.compani.project.member.service.ProjectMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectMemberServiceImplTest {
    @Autowired
    ProjectMemberService projectMemberService;

    @Test
    void getProjectMemberList() {
        List<Map<Object,Object>> projectMemberList = new ArrayList<>();
        projectMemberList = projectMemberService.getProjectMemberList(1);
        System.out.println(projectMemberList);
    }
}