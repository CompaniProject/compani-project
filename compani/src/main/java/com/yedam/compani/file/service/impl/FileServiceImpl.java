package com.yedam.compani.file.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yedam.compani.file.mapper.FileMapper;
import com.yedam.compani.file.service.FileSearchDTO;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileMapper filemapper;
	
	// 파일 리스트 확인
	@Override
	public List<FileVO> fileList() {
	
		return filemapper.fileList();
	}
	
	// 조회??;
	@Override
	public FileVO fileInfo(Integer FileNo) {

		return filemapper.fileInfo(FileNo);
	}
	
	// 파일 등록
	@Override
	public int fileInsert(final FileVO fileVO) {

		return filemapper.fileInsert(fileVO);
	}
	
	// 파일 삭제
	@Override
	public int fileDelete(Integer fileNo) {
		
		return filemapper.fileDelete(fileNo);
	}
	
	// 선택 삭제 
	@Override
	public int fileSelDel(List<Integer> files) {
		for(int i=0; i<files.size(); i++) {
			filemapper.fileDelete(files.get(i));
		}
		return files.size();
	}


	@Override
	public Page<FileVO> fileList(int pageNo, String search, String keywordFile, int bussNo ) {
		PageHelper.startPage(pageNo, 5);
		return filemapper.findFile(search, keywordFile, bussNo);
	}
	

}

