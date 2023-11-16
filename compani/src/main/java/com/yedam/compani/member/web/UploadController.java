package com.yedam.compani.member.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.yedam.compani.session.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {
	@Autowired
	MemberService sv;
	@Autowired
	SessionService sessionService;
	
	
	@Value("${filePath}")
	private String uploadPath;

	@PostMapping("/uploadsAjaxs")
	@ResponseBody
	public String uploadFiles(@RequestPart(value = "image", required = false) MultipartFile uploadFiles, HttpServletRequest request) {
			MemberVO vo = sessionService.getLoginInfo(request);
			if (uploadFiles.getContentType().startsWith("image") == false) {
				return null;
			}

			String originalName = uploadFiles.getOriginalFilename();						 //
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);//실제 파일이름을 불러옴


			// 날짜 폴더 생성																	//
			String folderPath = makeFolder();												//	
			// UUID																			//	
			String uuid = UUID.randomUUID().toString();										//
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분											//
																							//
			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;	//실제 서버에 저장될 파일이름
																							//
			String saveName = uploadPath + File.separator + uploadFileName;					//
			Path savePath = Paths.get(saveName);
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			try {
				uploadFiles.transferTo(savePath);
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
			} catch (IOException e) {
				e.printStackTrace();
			}
			// DB에 해당 경로 저장
			// 1) 사용자가 업로드할 때 사용한 파일명
			// 2) 실제 서버에 업로드할 때 사용한 경로
			vo.setMembPhtPath(setImagePath(uploadFileName));
			sv.editProfile(vo);
		return vo.getMembPhtPath();
	}
	
	@ResponseBody
	@PostMapping("/deletesAjaxs")
	public void deleteFiles(String path) {
		File file = new File(uploadPath + "/" + path);
		file.delete();
	}
	
	
	

	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = "upload/profilePht/" + str.replace("/", File.separator);
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
}

