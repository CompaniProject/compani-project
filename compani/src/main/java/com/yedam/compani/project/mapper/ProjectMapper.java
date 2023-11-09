package com.yedam.compani.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.service.ProjectVO;

public interface ProjectMapper {
	//전체조회
	public List<ProjectVO> selectAllProject(MemberVO memberVO);
	public List<Map<Object,Object>> selectProjectAndMemberList(String membId);
	public List<ProjectVO> getProjectStateList(ProjectVO projectVO);
	public int updateFavorite(ProjectVO projectVO); 
	public int updateBusiness(BusinessVO businessVO);
	public int insertProject(ProjectVO projectVO);
	public Map<Object,Object> projectSelect(Integer prjtNo);
	public int updateProject(ProjectVO project);
	public ProjectMemberVO updateCheck(@Param("prjtNo") Integer prjtNo, @Param("membId") String membId);
	Page<ProjectVO> findCompanyProject(@Param("search")String search, @Param("keyword") String keyword, @Param("coCd") String coCd);
}
