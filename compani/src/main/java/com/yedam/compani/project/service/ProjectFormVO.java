package com.yedam.compani.project.service;

import java.util.List;

import com.yedam.compani.project.member.service.ProjectMemberVO;

import lombok.Data;

@Data
public class ProjectFormVO {
	ProjectVO project;
	List<ProjectMemberVO> projectMember;
}
