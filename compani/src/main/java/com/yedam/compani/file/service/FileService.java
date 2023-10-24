package com.yedam.compani.file.service;

import java.util.List;

public interface FileService {
	
	// 전체보기
	public List<FileVO> getFileList();
	
	// 조회
	public FileVO getFileInto(FileVO fileVO);
	
	// 등록
	public int getFileInsert(FileVO fileVO);
	
	// 삭제
	public int getFileDelete(int FileNum);
	

	
}
