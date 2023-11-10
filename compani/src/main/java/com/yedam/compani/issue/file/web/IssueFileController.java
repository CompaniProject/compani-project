package com.yedam.compani.issue.file.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.compani.config.FileUtils;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;

import lombok.RequiredArgsConstructor;

/*
 * 작성자: 권오준
 * 작성일자: 2023/11/10
 * 내용: 이슈 내 파일 조회, 다운로드, 수 카운트 
 */
@RestController
@RequiredArgsConstructor
public class IssueFileController {

	  private final IssueFileService issueFileService;

	  // 파일 리스트 조회
	  @GetMapping("/issues/{issuNo}/files")
	  public List<IssueFileVO> findAllFileByIssuNo(@PathVariable final int issuNo) {
		  return issueFileService.findAllFileByIssuNo(issuNo);
	  }
	  
	  //첨부 파일 다운로드
	  @GetMapping("/issues/{issuNo}/files/{issuFileNo}/download")
	    public ResponseEntity<Resource> downloadFile(@PathVariable final int issuNo, @PathVariable final int issuFileNo) {
	        IssueFileVO file = issueFileService.findFileById(issuFileNo);
	        Resource resource = FileUtils.readFileAsResource(file);
	        try {
	            String filename = URLEncoder.encode(file.getIssuFileNm(), "UTF-8");
	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
	                    .header(HttpHeaders.CONTENT_LENGTH, file.getIssuFileSize() + "")
	                    .body(resource);

	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("filename encoding failed : " + file.getIssuFileNm());
	        }
	    }
	  	  
	  // 파일 수 카운트
	  @GetMapping("/issues/{issuNo}/files/count")
		public int countReply(@PathVariable final int issuNo) {
			return issueFileService.countFile(issuNo);
		}
}
