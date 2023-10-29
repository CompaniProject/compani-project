package com.yedam.compani.file.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.compani.config.BusinessFileUtils;
import com.yedam.compani.file.service.FileDTO;
import com.yedam.compani.file.service.FileSearch;
import com.yedam.compani.file.service.FileService;
import com.yedam.compani.file.service.FileVO;

@Controller
public class FileController {

	@Autowired
	FileService fileservice;
	BusinessFileUtils businessfileutils;

	// 프로젝트 자료함 전체조회
	@GetMapping("projectFile")
	public String prjtF() {
		return "projectFile";
	}

	// 업무 파일함 전체조회
	@GetMapping("workFile")
	public String workFile(Model model) {
		List<FileVO> lFile = fileservice.fileList();
		model.addAttribute("fileList", lFile);
		return "workFile";
	}

	// 모달 확인용
	@GetMapping("modal")
	public String moD() {
		return "modal";
	}

	// 등록 모달창 확인용
	@GetMapping("insertmodal")
	public String insertmodal() {
		return "insertmodal";
	}

	// 파일 업로드 다운로드 테스트용
	@GetMapping("FileTest")
	public String FileTest() {
		return "FileTest";
	}

	// 업무 모달 파일함 검색 ajax 처리

	@GetMapping("/AjaxSearch")
	@ResponseBody
    public List<FileVO> AjaxSearch(@RequestParam String type, @RequestParam String keyword) {
        FileSearch search = new FileSearch();
        search.setType(type);
        search.setKeyword(keyword);
        return fileservice.fileSearch(search);
    }

	// 업무 파일함 파일 업로드(등록)
	@PostMapping("/AjaxFileInsert")
	@ResponseBody
	public void AjaxFileInsert(MultipartFile[] files, FileVO fileVO) {
		// 파일 업로드, 파일 DB에 저장
		List<FileVO> uploadedFiles = new ArrayList<>();

		if (files != null && files.length > 0) {
			uploadedFiles = businessfileutils.uploadFiles(Arrays.asList(files)); // 배열을 리스트로 변환하는 메서드. MultipartFile[]
			fileservice.fileInsert(fileVO);															// files -> List<MultipartFile>
		}	
	}
	
	/*
	 * @PostMapping("/AjaxFileOneInsert")
	 * 
	 * @ResponseBody public int AjaxFileOneInsert(MultipartFile[] file, FileVO
	 * fileVO) { businessfileutils.uploadFile(); }
	 */
	
	/*
	 * //첨부 파일 다운로드
	 * 
	 * @GetMapping("/issues/{issuNo}/files/{issuFileNo}/download") // 나중에 수정 하자
	 * public ResponseEntity<Resource> downloadFile(@PathVariable final int FileNo)
	 * { FileVO file = fileservice.fileInto(FileNo); Resource resource =
	 * businessfileutils.readFileAsResource(file); try { String filename =
	 * URLEncoder.encode(file.getFileNm(), "UTF-8"); return ResponseEntity.ok()
	 * .contentType(MediaType.APPLICATION_OCTET_STREAM)
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename
	 * + "\";") .header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() + "")
	 * .body(resource);
	 * 
	 * } catch (UnsupportedEncodingException e) { throw new
	 * RuntimeException("filename encoding failed : " + file.getFileNm()); } }
	 */
	
	
	// 테스트
	// FileUploadController.java
	// 파일 업로드 처리 (파일명 중복되지 않도록 파일 이름 변경해서 업로드)
	    @PostMapping("/fileUpload")
	    public String fileUpload(@RequestParam("uploadFile") MultipartFile file, Model model) throws IOException {
	        String savedFileName = "";
	        // 1. 파일 저장 경로 설정 : 실제 서비스되는 위치(프로젝트 외부에 저장)
	        String uploadPath = "C:/Users/admin/Desktop";
	        // 2. 원본 파일 이름 알아오기
	        String originalFileName = file.getOriginalFilename();
	        // 3. 파일 이름 중복되지 않게 이름 변경(서버에 저장할 이름) UUID 사용
	        UUID uuid = UUID.randomUUID();
	        savedFileName = uuid.toString() + "_" + originalFileName;
	        // 4. 파일 생성
	        File file1 = new File(uploadPath + savedFileName);
	        // 5. 서버로 전송
	        file.transferTo(file1);
	        // model로 저장
	        model.addAttribute("originalFileName", originalFileName);
	        return"upload/fileUploadResult";
	    }
	    
	    @PostMapping("/upload")
	    public String upload(@RequestParam MultipartFile[] uploadfile, Model model) throws IllegalStateException, IOException {
	    	List<FileDTO> list = new ArrayList<>();
	    	for (MultipartFile file : uploadfile) {
	    		if (!file.isEmpty()) {
	    			FileDTO dto = new FileDTO(UUID.randomUUID().toString(),
	    									  file.getOriginalFilename(),
	    									  file.getContentType());
	    			list.add(dto);
	    			
	    			File newFileName = new File(dto.getFilePath() + "_" + dto.getFileNm());
	    			
	    			file.transferTo(newFileName);
	    		}
	    	}
	    	model.addAttribute("files", list);
	    	return "FileTest";
	    }
	    
	    
	    
}