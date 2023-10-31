package com.yedam.compani.file.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class FileUploadController {

		@Autowired
		FileService fileservice;
		
		@Value("${file.upload.path}") // 환경변수 or Properties에 있는 외부값을 읽는방법이다 - EL태그 사용
		private String uploadPath;
		
		@PostMapping("uploadsAjax")
		@ResponseBody
		public Map<String, Object> uploadFile(@RequestPart MultipartFile[] uploadFiles, FileVO fileVO) {
		    										// 화면에 Multipart 유무에 따라 배열 넣냐 안넣냐 차이
			
			List<String> fList = new ArrayList<>();
			
		    for(MultipartFile uploadFile : uploadFiles){
		        String originalName = uploadFile.getOriginalFilename(); // 실제 파일명 
		        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
		        				  // ↑ 둘다 실제 사용자가 본인이 올린 파일명
		        
		        
		        Long fileSize = uploadFile.getSize(); // 파일 사이즈 가져오기
		        
		        // --- 확장자만 따로 구하기
		        String fileType = fileName.substring(fileName.lastIndexOf(".") +1);       // 파일명에 붙어있는 확장자명만 빼오기 (타입)
		    
		        // --------------------------------------------------
		        //날짜 폴더 생성 (서버내에 저장됨)
		        String folderPath = makeFolder();
		        //UUID
		        String uuid = UUID.randomUUID().toString(); // 랜덤값 지정 (UUID값)
		        //저장할 파일 이름 중간에 "_"를 이용하여 구분
		        
		        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
		        // 실제 경로
		        String saveName = uploadPath + File.separator + uploadFileName;
		        // 서버 안의 별도의 공간 그 안에 내가 지금 저장할 이름을 정하는 코드
		        // --------------------------------------------------
		        
		        Path savePath = Paths.get(saveName);
		        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
		        System.out.println("path : " + saveName);
		        try{
		        	uploadFile.transferTo(savePath); // 실제 작동하는 코드
		            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
		        } catch (IOException e) {
		             e.printStackTrace();	             
		        }
		        
		          //VO에 업로드된 파일의 정보 담기
		        
			      // 로그인한 멤버 세션 불러와서 지정하면 작성자 가능? String id=(String)request.getSession().getAttribute("id"); 
			      //fileVO.setmembId(id); //세션에서 읽어낸 파일 업로더의 아이디지정 (작성자)
			      fileVO.setFileNm(originalName); // 파일명 저장
			      fileVO.setFilePath(uploadFileName); // 경로
			      fileVO.setFileType(fileType); // 확장자
			      fileVO.setFileSize(fileSize); // 크기
      
			      // DB 에 저장하기
			      fileservice.fileInsert(fileVO); // 등록과 동시에 DB에 저장 
			      
		        fList.add(setImagePath(uploadFileName));     
		     } 
		    
	        Map<String, Object> map = new HashMap<>();
	        
	        map.put("fList", fList);
	        map.put("fileVO", fileVO);
		    
		    return map;
		}
		
		private String makeFolder() {
			String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			// LocalDate를 문자열로 포멧
			String folderPath = str.replace("/", File.separator); // File.separator 파일인식
			File uploadPathFoler = new File(uploadPath, folderPath);
			// File newFile= new File(dir,"파일명");
			if (uploadPathFoler.exists() == false) {
				uploadPathFoler.mkdirs();
				// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
				// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
				// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
			}
			return folderPath;
		}
		
		private String setImagePath(String uploadFileName) {
			return uploadFileName.replace(File.separator, "/");
		}
		
		// 파일 다운로드
		@GetMapping(value="workfile/{fileNo}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(String fileName){	
			log.info("download file : " + fileName);
			
			// 존재하는 파일 찾아감
			FileSystemResource resource = new FileSystemResource("c:\\Temp\\" + fileName);
			
			log.info("resource : " + resource);
			
			String resourceName = resource.getFilename();
			
			HttpHeaders headers = new HttpHeaders();
			try {			// 파일명이 한글일 시 깨짐 방지
				headers.add("Content-Disposition",
						"attachment; filename=" + new String(resourceName.getBytes("UTF-8"),
						"ISO-8859-1"));
			}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		}		
	
}
