package com.yedam.compani.file.mapper;

import java.util.List;

import com.yedam.compani.file.service.FileVO;

public interface FileMapper {

		// 전체보기
		public List<FileVO> FileList();
		
		// 조회
		public FileVO FileInfo(FileVO fileVO);
		
		// 등록
		public int FileInsert(FileVO fileVO);
		
		// 삭제
		public int FileDelete(Integer FileNo);
		
		// 업무 모달 파일함 검색기능
		public List<FileVO> FileSearch(FileVO fileVO);
	
}
