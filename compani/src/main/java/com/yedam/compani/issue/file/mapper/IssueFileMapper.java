package com.yedam.compani.issue.file.mapper;

import java.util.List;

import com.yedam.compani.issue.file.service.IssueFileVO;

public interface IssueFileMapper {
    /**
     * 파일 정보 등록
     *
     * @param issueFileVO - 파일 정보 리스트
     */

    void modalInsertIssueFile(List<IssueFileVO> files);

    /**
     * 파일 리스트 조회
     *
     * @param issuNo - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    List<IssueFileVO> select(int issuNo);

    /**
     * 파일 리스트 조회 -> 물리적 파일의 삭제 처리에 사용
     *
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    List<IssueFileVO> findAllByIds(List<Integer> ids);

    /**
     * 파일 삭제
     *
     * @param ids - PK 리스트 -> DB에서 첨부파일을 삭제 처리
     */
    void deleteAllByIds(List<Integer> ids);

    /**
     * 파일 상세정보 조회
     *
     * @param issuFileNo - PK
     * @return 파일 상세정보
     */
    IssueFileVO findById(int issuFileNo);

    /**
     * 파일 수 카운팅
     *
     * @param issuNo - 게시글 번호 (FK)
     * @return 파일 수
     */
    int count(int issuNo);
}
