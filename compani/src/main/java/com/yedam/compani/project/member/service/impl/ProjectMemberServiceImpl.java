package com.yedam.compani.project.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.project.member.mapper.ProjectMemberMapper;
import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.service.ProjectFormVO;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

	@Autowired
	ProjectMemberMapper projectMemberMapper;

	@Override
	public List<ProjectMemberVO> getProjectMemberCountList() {
		return projectMemberMapper.selectAllProjectMemberCount();
	}

	@Override
	public List<Map<Object, Object>> getProjectMemberList(Integer prjtNo) {
		return projectMemberMapper.selectProjectMemberList(prjtNo);
	}

	@Override
	public List<Map<Object, Object>> getBusinessCompleteStatus(Integer prjtNo) {
		return projectMemberMapper.selectBusinessCompleteStatus(prjtNo);
	}

	@Override
	public int insertProjectMember(ProjectFormVO formVO) {

		for (int i = 0; i < formVO.getProjectMember().size(); i++) {

			// 프로젝트 생성자는 CRTR , 참여자는 PARTICIR
			formVO.getProjectMember().get(i).setPrjtNo(formVO.getProject().getPrjtNo());
			if (i == 0) {
				formVO.getProjectMember().get(i).setPrjtmembPerm("CRTR");
			} else {
				formVO.getProjectMember().get(i).setPrjtmembPerm("PARTICIR");
			}
		}
		return projectMemberMapper.insertProjectMember(formVO.getProjectMember());
	}

	@Override
	public List<Map<String,String>> projectMemberList(Integer prjtNo) {
		
		return projectMemberMapper.projectMemberList(prjtNo);
	}

	@Override
	public int deleteProjectMember(ProjectFormVO formVO) {
		
		int prjtNo = formVO.getProject().getPrjtNo();
		
		return projectMemberMapper.deleteProjectMember(prjtNo);
	}

	

}
