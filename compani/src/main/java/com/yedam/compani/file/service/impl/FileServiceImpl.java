package com.yedam.compani.file.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

	// 조회??;
	@Override
	public FileVO fileInfo(Integer FileNo) {

		return filemapper.fileInfo(FileNo);
	}
	
	// 모달 파일 업로드
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
	
	// 모달 파일 리스트 + 검색 + 페이징
	@Override
	public Page<FileVO> fileList(int pageNo, String search, String keywordFile, int bussNo ) {
		PageHelper.startPage(pageNo, 5);
		return filemapper.findFile(search, keywordFile, bussNo);
	}
	
	
	// 드라이브 파일 리스트 + 검색 
	@Override
	public List<FileVO> fileList(String pSearch, String pKeyword, int prjtNo) {
	
		return filemapper.fileList(pSearch, pKeyword, prjtNo);
	}
	
	// 드라이브 파일 업로드 
	@Override
	public void driveFileInsert(List<FileVO> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
		filemapper.driveFileInsert(files);
	}
	
	// 드라이브 파일 필터링
	@Override
	public List<FileVO> fileList(String Filter, int prjtNo) {

		return filemapper.fileFilter(Filter, prjtNo);
	}

	@Override
	public int fileDriveDel(List<Integer> driveFile) {
			for(int i=0; i<driveFile.size(); i++) {
				filemapper.fileDelete(driveFile.get(i));
			}
		return driveFile.size();
	}

	@Override
	public FileVO fileDownload(Integer fileNo) {
		
		return filemapper.fileDownload(fileNo);
	}
	

}

