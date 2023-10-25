package com.yedam.compani.issue.reply.service;

import java.util.List;

public interface IssueReplyService {
		
	public int saveIssueReply(IssueReplyVO params);
	
	public IssueReplyVO findReplyById(int issuRplyNo);
	
	public int updateReply(IssueReplyVO params);
	
	public int deleteReply(int issuRplyNo);
	
	public List<IssueReplyVO> findAllReply(int issuNo);
}
