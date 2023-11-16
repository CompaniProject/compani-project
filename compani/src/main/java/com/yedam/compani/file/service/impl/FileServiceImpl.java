package com.yedam.compani.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yedam.compani.file.mapper.FileMapper;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper filemapper;
	
	@Value("${filePath.driveFile}") // 환경변수 or Properties에 있는 외부값을 읽는방법이다 - EL태그 사용
	private String uploadPath;
	
	@Value("${filePath.bussFile}") // 환경변수 or Properties에 있는 외부값을 읽는방법이다 - EL태그 사용
	private String bussFilePath;

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

	// 드라이브 파일 삭제
	@Override
	public int fileDelete(Integer fileNo) {
		
		// 단건조회
		FileVO info = filemapper.fileInfo(fileNo);

		// 실제 경로 삭제
		if (info != null) {
			File file = new File(uploadPath , info.getFilePath());
			if(file.exists()) {
				file.delete();
			} else {
				
			}
			
		}
		return filemapper.fileDelete(fileNo);
	}

	// 드라이브 선택 삭제
	@Override
	public int fileSelDel(List<Integer> files) {
		for (int i = 0; i < files.size(); i++) {
			// 단건조회
			FileVO info = filemapper.fileInfo(files.get(i));

			// 실제 경로 삭제
			if (info != null) { 
				File file = new File(uploadPath , info.getFilePath());
				file.delete();
			}
			filemapper.fileDelete(files.get(i));
		}
		return files.size();
	}
	
	// 모달 파일 삭제
		@Override
		public int fileModalDel(Integer fileNo) {
			
			// 단건조회
			FileVO info = filemapper.fileInfo(fileNo);

			// 실제 경로 삭제
			if (info != null) {
				File file = new File(bussFilePath , info.getFilePath());
				if(file.exists()) {
					file.delete();
				} else {
					log.info(file.getAbsolutePath());
				}
			}
			return filemapper.fileDelete(fileNo);
		}

		// 모달 선택 삭제
		@Override
		public int fileModalSeldel(List<Integer> files) {
			for (int i = 0; i < files.size(); i++) {
				// 단건조회
				FileVO info = filemapper.fileInfo(files.get(i));

				// 실제 경로 삭제
				if (info != null) { 
					File file = new File(bussFilePath , info.getFilePath());
					file.delete();
				}
				filemapper.fileDelete(files.get(i));
			}
			return files.size();
		}

	// 모달 파일 리스트 + 검색 + 페이징
	@Override
	public Page<FileVO> fileList(int pageNo, String search, String keywordFile, int bussNo) {
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
	public void driveFileInsert(List<FileVO> files, int prjtNo, int bussNo, String membId) {
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		filemapper.driveFileInsert(files, prjtNo, bussNo, membId);
	}

	// 드라이브 파일 필터링
	@Override
	public List<FileVO> fileList(String Filter, int prjtNo) {

		return filemapper.fileFilter(Filter, prjtNo);
	}

	// 업무 + 드라이브 파일 다운로드
	@Override
	public FileVO fileDownload(Integer fileNo) {

		return filemapper.fileDownload(fileNo);
	}

}
