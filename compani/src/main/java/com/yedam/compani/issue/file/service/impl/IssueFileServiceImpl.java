package com.yedam.compani.issue.file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yedam.compani.issue.file.mapper.IssueFileMapper;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;

@Service
public class IssueFileServiceImpl implements IssueFileService {

	@Autowired
	private IssueFileMapper issueFileMapper;
	
	/*
	 * 게시글 번호 ( issuNo), 파일 정보 (issueFileVO)를 전달받아, 업로드된 파일 정보를 테이블에 저장하는 역할. 만약 게시글
	 * 등록 (INSERT OR UPDATE) 하는 시점에 업로드 된 파일이 없다면 로직을 종료, 파일 이있으면 모든 요청 객체에 게시글 번호
	 * (issuNo)을 세팅한후 테이블에 파일 정보를 저장.
	 */
	@Override
	@Transactional
	public void modalInsertIssueFile(int issuNo, final List<IssueFileVO> files) {
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		for (IssueFileVO file : files) {
			file.setIssuNo(issuNo);
		}
		issueFileMapper.modalInsertIssueFile(files);
	}

	@Override
	public List<IssueFileVO> selectIssueFile(int issuNo) {	 
		return issueFileMapper.select(issuNo);
	}

	@Override
	public IssueFileVO findFileById(int issuFileNo) {
		return issueFileMapper.findById(issuFileNo);
	}
	
}
