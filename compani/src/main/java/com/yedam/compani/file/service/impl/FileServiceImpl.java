package com.yedam.compani.file.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> getFileDelete(List<Integer> list) {
		boolean successOrNot = false; // 성공했는지 판단
		List<Integer> success = new ArrayList<>(); // 성공한 대상
		int count = 0; // 성공 횟수 판단
		
		for(int fileNo : list) {
			int result = filemapper.FileDelete(fileNo);
			
			if(result == 1) { // 삭제에 성공 한다면
				count+= 1; // 성공 횟수 올려주고
				success.add(fileNo); // 성공한 대상에 파일번호를 삽입
			}
		}
		if(count > 0) {
			successOrNot = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("total", list.size()); // total = list의 값을 가짐
		map.put("result", successOrNot); // result = 성공했는지 판단
		map.put("list", success); // list = 성공한 대상을 나타냄
		
		return map;
	}

}

