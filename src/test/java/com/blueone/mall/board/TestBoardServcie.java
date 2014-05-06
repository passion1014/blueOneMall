package com.blueone.mall.board;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestBoardServcie extends BlueoneTestCase {
	
	/**
	 * 게시판 글목록 조회
	 */
	@Test
	public void testBoardList() throws Exception {
		// http://localhost:8080/board/list.do?srchBrdTyp=10&brdCodeType=01&brdCodeKey=P5571
		mockMvc.perform(post("/board/list.do")
				.param("srchBrdTyp", "10")
				.param("brdCodeType", "01")
				.param("brdCodeKey", "P5571")
				)
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * [게시판 글쓰기]
	 * 
	 * private long brdSeq;		// 게시물일련번호
	 * private int brdTyp;			// 게시판유형(FK)
	 * private int srchBrdTyp;		// 게시판유형(FK)
	 * private String brdTypNm;	// 게시판명
	 * private long rootSeq;		// 부모일련번호
	 * private long refSeq;		// 자식일련번호
	 * private int depth;			// 게시물깊이
	 * private String passwd;		// 비밀번호
	 * private String title;		// 제목
	 * private String cont;		// 내용
	 * private int hit;			// 조회수
	 * private int commCnt;		// 덧글수
	 * private int replyCnt;		// 답변수
	 * private String delYn;		// 삭제여부
	 * private String delDt;		// 삭제일자
	 * private String delUser;		// 삭제자
	 * private String insDt;		// 최초입력일
	 * private String insDtTyp1;	// 최초입력일
	 * private String insUser;		// 최초입력자
	 * private String updDt;		// 최종변경일
	 * private String updUser;		// 최종변경자
	 * private MultipartFile[] uploadFile;		// 첩부파일
	 * private MultipartFile imgFile;			// 첨부 대표이미지
	 * private String procSt;		// 처리상태
	 * private String userNm;		// 작성자명
	 * private String noticeYn;		// 공지여부
	 * private int rnum;				// ROWNUM
	 * private String todayYn;
	 * private int[] flNo;
	 * private String publicYmd;
	 * private String orgPublicYmd;
	 * private String publicNm;
	 * private String repreImage;
	 * private String[] contImageFile;	// 내용에 추가된 이미지파일
	 * private List<BoardAttachFileInfo> attachFiles;
	 */
	@Test
	public void testBoardInsert() throws Exception {
		mockMvc.perform(post("/board/list.do")
				.param("brdSeq", "1")		// 게시물일련번호
				.param("brdTyp", "1")			// 게시판유형(FK)
				.param("srchBrdTyp", "1")		// 게시판유형(FK)
				.param("brdTypNm", "1")	// 게시판명
				.param("rootSeq", "1")		// 부모일련번호
				.param("refSeq", "1")		// 자식일련번호
				.param("depth", "1")			// 게시물깊이
				.param("passwd", "1")		// 비밀번호
				.param("title", "1")		// 제목
				.param("cont", "1")		// 내용
				.param("hit", "1")			// 조회수
				.param("commCnt", "1")		// 덧글수
				.param("replyCnt", "1")		// 답변수
				.param("delYn", "1")		// 삭제여부
				.param("delDt", "1")		// 삭제일자
				.param("delUser", "1")		// 삭제자
				.param("insDt", "1")		// 최초입력일
				.param("insDtTyp1", "1")	// 최초입력일
				.param("insUser", "1")		// 최초입력자
				.param("updDt", "1")		// 최종변경일
				.param("updUser", "1")		// 최종변경자
				.param("uploadFile", "1")		// 첩부파일
				.param("imgFile", "1")			// 첨부 대표이미지
				.param("procSt", "1")		// 처리상태
				.param("userNm", "1")		// 작성자명
				.param("noticeYn", "1")		// 공지여부
				.param("rnum", "1")				// ROWNUM
				.param("todayYn", "1")
				.param("publicYmd", "1")
				.param("orgPublicYmd", "1")
				.param("publicNm", "1")
				.param("repreImage", "1"))
				.andExpect(status().isOk())
		.andDo(print());
	}
	
}
