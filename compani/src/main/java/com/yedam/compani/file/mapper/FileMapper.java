package com.yedam.compani.file.mapper;

import java.util.List;

import com.yedam.compani.file.service.FileVO;

public interface FileMapper {

		// 전체보기
		public List<FileVO> fileList();
		
		// 다운로드할 파일 조회
		public FileVO fileInfo(Integer FileNo);
		
		// 파일 등록(업로드)
		public int fileInsert(FileVO fileVO);
		
		// 삭제
		public int fileDelete(Integer fileNo);
		
		// 선택삭제
		public int fileSelDel(List<Integer> fileNo);
		
		// 업무 모달 파일함 검색기능
		public List<FileVO> fileSearch(FileVO fileVO);
	
}
