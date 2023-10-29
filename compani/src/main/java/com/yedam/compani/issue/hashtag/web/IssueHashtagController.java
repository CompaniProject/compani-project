package com.yedam.compani.issue.hashtag.web;

import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IssueHashtagController {

    private final IssueHashtagService issueHashtagService;

    @PostMapping("/ModalAjaxHashtagInsert")
    @ResponseBody
    public void modalHashtagInsert(@RequestBody List<IssueHashtagVO> hashtags, final int issuNo) {
       issueHashtagService.modalInsertIssueHashtag(issuNo,hashtags);
    }
}
