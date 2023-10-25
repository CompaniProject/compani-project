package com.yedam.compani.issue.file.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class IssueFileController {
			
	  @Autowired
	  IssueFileService issueFileService;
	  @Autowired
	  FileUtils fileUtils;
	  
	  //첨부 파일 다운로드
	  @GetMapping("/issues/{issuNo}/files/{issuFileNo}/download")
	    public ResponseEntity<Resource> downloadFile(@PathVariable final int issuNo, @PathVariable final int issuFileNo) {
	        IssueFileVO file = issueFileService.findFileById(issuFileNo);
	        Resource resource = fileUtils.readFileAsResource(file);
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
	
}
