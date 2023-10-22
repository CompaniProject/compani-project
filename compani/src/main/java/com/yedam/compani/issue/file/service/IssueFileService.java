package com.yedam.compani.issue.file.service;

import java.util.List;

public interface IssueFileService {
	
	public void modalInsertIssueFile(final int issuNo, final List<IssueFileVO> files);
}
