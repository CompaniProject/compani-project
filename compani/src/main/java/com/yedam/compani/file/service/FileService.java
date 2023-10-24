package com.yedam.compani.file.service;

import java.util.List;
import java.util.Map;

public interface FileService {
	
	// 전체보기
	public List<FileVO> getFileList();
	
	// 조회
	public FileVO getFileInto(FileVO fileVO);
	
	// 등록
	public int getFileInsert(FileVO fileVO);
	
	// 삭제
	public Map<String, Object> getFileDelete(List<Integer> list);
	
	// 업무 모달 파일함 검색기능
	public List<FileVO> getFileSearch(FileVO fileVO);
	
}
