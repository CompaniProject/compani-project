package com.yedam.compani.file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.file.mapper.FileMapper;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileMapper filemapper;
	
	@Override
	public List<FileVO> getFileList() {
	
		return filemapper.FileList();
	}

	@Override
	public FileVO getFileInto(FileVO fileVO) {

		return filemapper.FileInfo(fileVO);
	}

	@Override
	public int getFileInsert(FileVO fileVO) {

		return filemapper.FileInsert(fileVO);
	}

	@Override
	public int getFileDelete(int FileNum) {

		return filemapper.FileDelete(FileNum);
	}

}

