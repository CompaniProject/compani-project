package com.yedam.compani.file.service;

import java.util.List;
import java.util.Map;

public interface FileService {
	
	// 전체보기
	public List<FileVO> fileList();
	
	// 조회
	public FileVO fileInto(FileVO fileVO);
	
	// 등록
	public int getfileInsert(FileVO fileVO);
	
	// 삭제
	public int fileDelete(Integer fileNo);
	
	// 선택삭제
	public int fileSelDel(List<Integer> FileNo);
	
	// 업무 모달 파일함 검색기능
	public List<FileVO> fileSearch(FileVO fileVO);
	
}
