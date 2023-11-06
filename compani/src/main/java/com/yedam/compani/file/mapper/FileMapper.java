package com.yedam.compani.file.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.yedam.compani.file.service.FileSearchDTO;
import com.yedam.compani.file.service.FileVO;

public interface FileMapper {

		// 드라이브 파일 전체보기
		public List<FileVO> fileList(@Param("pType")String pSearch, @Param("pKeyword") String pKeyword, @Param("prjtNo") int prjtNo);
		
		// 드라이브 필터링
		public List<FileVO> fileFilter(@Param("filter")String Filter, @Param("prjtNo") int prjtNo);
		
		// 다운로드할 파일 조회
		public FileVO fileInfo(Integer FileNo);
		
		// 파일 등록(업로드)
		public int fileInsert(FileVO fileVO);
		
		// 삭제
		public int fileDelete(Integer fileNo);
		
		// 선택삭제
		public int fileSelDel(List<Integer> fileNo);
		
		// 업무 모달 파일함 검색기능
		public List<FileVO> fileSearch(Map<String, String> map);

		// 검색 + 페이징
		public Page<FileVO> findFile(@Param("type")String search, @Param("keywordFile") String keywordFile, @Param("bussNo") int bussNo);
		
		// 드라이브 업로드
		void driveFileInsert(List<FileVO> files);
}
