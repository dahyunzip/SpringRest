package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.SampleVO;

//@RestController : 모든 동작에 @ResponseBody 적용된 형태

@RestController
@RequestMapping(value="/rest/*")
public class SampleRESTController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleRESTController.class);
	
	// 서비스 객체 주입 필요!
	
	// http://localhost:8088/test1		(X)
	// http://localhost:8088/rest/test1 (O)
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public String restTest1() throws Exception{
		logger.info("/test1 -> restTest1() 실행");
		
		return "ITWILL";
	}
	
	//http://localhost:8088/rest/test2
	//http://localhost:8088/rest/test2?bno=99
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public SampleVO restTest2(@ModelAttribute("bno") int bno) throws Exception{
		logger.info("/test2 -> restTest2() 실행");
		
		// + Jackson-databind 라이브러리 추가 설치
		
		// 서비스-DAO 연결동작 변경
		SampleVO vo = new SampleVO();
		
		vo.setBno(99);
		vo.setName("아이티윌");
		vo.setTitle("테스트 글제목");
		
		return vo;
	}
	
	//http://localhost:8088/rest/test3
	@RequestMapping(value="/test3", method=RequestMethod.GET)
	public List<SampleVO> restTest3() throws Exception{
		logger.info("/test3 -> restTest3() 실행");
		
		// 데이터 임시 생성
		List<SampleVO> sampleList = new ArrayList<>();
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			
			vo.setBno(i);
			vo.setName("이름"+i);
			vo.setTitle("제목"+i);
			
			sampleList.add(vo);
		}
		logger.info(" sampleList : {}", sampleList);
		
		// 리스트 객체를 JSON 타입의 리소스로 변경
		
		return sampleList;
	}
	
	//http://localhost:8088/rest/test4
	@RequestMapping(value="/test4", method=RequestMethod.GET)
	public Map<Integer, SampleVO> restTest4() throws Exception{
		logger.info("/test4 -> restTest4() 실행");
		
		// 데이터 임시 생성
		Map<Integer,SampleVO> sampleMap = new HashMap<>();
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			
			vo.setBno(i);
			vo.setName("이름"+i);
			vo.setTitle("제목"+i);
			
			sampleMap.put(i,vo);
		}
		logger.info(" sampleMap : {}", sampleMap);
		// 맵 객체를 JSON 타입의 리소스로 변경
		
		return sampleMap;
	}
	
	// http://localhost:8088/rest/test5?bno=100
	
	// @PathVariable : 주소줄에 생성된 변수(경로변수-PathVariable)
	//				   정보를 받아서 사용가능
	
	// http://localhost:8088/rest/test5/100
	//@RequestMapping(value="/test5, method=RequestMethod.GET)
	//@RequestMapping(value="/test5/100", method=RequestMethod.GET)
	@RequestMapping(value="/test5/{num}", method=RequestMethod.GET)
	public Integer restTest5(@PathVariable("num") int num) throws Exception{
	//public Integer restTest5(@ModelAttribute("num") int num) throws Exception{ 
							   // ModelAttribute도 권장하지 않는다. 목적에 맞지 않기 때문.
						       // @RequestParam은 사용할 수 없다!
		logger.info(" restTest5() 실행!");
		
		logger.info(" bno : "+ num);
		return num;
	}
	
	// http://localhost:8088/rest/create
	// 비동기방식으로 호출되는 동작
	// 정보 입력 동작을 처리
	// ajax에서 전달되는 객체 sample을 받아서 처리
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String create(/*@ModelAttribute SampleVO vo (기존 폼태그 정보 저장)*/
						 @RequestBody SampleVO vo) throws Exception{
						 // @RequestBody : 전달된 JSON타입의 데이터를 해당타입의 객체로 자동매핑(변환)
		
		logger.info(" /rest/create -> create() 실행! ");
		logger.info(" vo : " + vo );
		
		// 서비스-정보를 입력하는 동작
		logger.info(" 정보입력(vo) 호출 ");
		
		if(vo != null) {
			return "createYES";
		}
		return "createNO";
	}
	
	// SampleVO 객체를 리턴하는 /read 호출
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public SampleVO read() {
		
		logger.info(" /rest/read -> read() 호출");
		
		SampleVO vo = new SampleVO();
		vo.setBno(1);
		vo.setName("관리자");
		vo.setTitle("DB에서 가져온 정보");
		
		return vo;
	}
	
	@RequestMapping(value="/test6", method=RequestMethod.GET)
	public ResponseEntity<Void> restTest6() throws Exception{
		logger.info(" /rest/test6 -> restTest6() 실행! ");
		logger.info(" 기존의 리턴정보는 데이터만 전달! ");
		logger.info(" => 데이터 처리 결과(상태)를 알 수 없음!!!");
		
		// ResponseEntity<T> : 
		// HTTP 요청의 결과(데이터, HTTP 상태코드)를 처리하는 객체
		
		ResponseEntity<Void> respEntity = 
				//new ResponseEntity<>(HttpStatus.LOCKED);
				 new ResponseEntity<>(HttpStatus.NOT_FOUND);
		// Void : 반환할 타입이 없다.
		
		// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return respEntity;
	}
	
	@RequestMapping(value="/test7", method=RequestMethod.GET)
	public ResponseEntity<String> restTest7() throws Exception{
		logger.info(" /rest/test7 -> restTest7() 실행 ");
		ResponseEntity<String> respEntity = 
				// new ResponseEntity<String>("createOK", HttpStatus.NOT_FOUND);
				// NOT_FOUND 하면 받는 쪽에서 처리가 안됨.
				new ResponseEntity<String>("createOK", HttpStatus.OK);
				// new ResponseEntity<String>("createOK", HttpStatus.BAD_REQUEST);
				// new ResponseEntity<String>("createOK", HttpStatus.BAD_GATEWAY);
		logger.info(" createOK 정보를 가지고 200 상태 코드를 전달");
		return respEntity;
		
	}
	
	@RequestMapping(value="/test8", method=RequestMethod.GET)
	public ResponseEntity<SampleVO> restTest8() throws Exception{
		logger.info(" /rest/test8 -> restTest8() 실행 ");
		
		SampleVO vo = new SampleVO();
		vo.setBno(100);
		vo.setName("홍길동");
		vo.setTitle("안녕하세요?");
		// => 임시 생성 객체 (서비스로부터 전달받은 객체 정보)
		
		ResponseEntity<SampleVO> respEntity = null;
		if(vo != null) {
			respEntity = new ResponseEntity<SampleVO>(vo, HttpStatus.OK);
		}else { // null
			respEntity = new ResponseEntity<SampleVO>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		ResponseEntity<SampleVO> respEntity = 
//				new ResponseEntity<SampleVO>(vo, HttpStatus.OK);
		
		logger.info(" SampleVO 객체 정보를 가지고 200 상태 코드를 전달");
		return respEntity;
		
	}
	
	@RequestMapping(value="/test9", method=RequestMethod.GET)
	public ResponseEntity<List<SampleVO>> restTest9() throws Exception{
		   //ResponseEntity<Map<Integerm SampleVO>>
		logger.info(" /rest/test9 -> restTest9() 실행 ");
		
		List<SampleVO> sampleList = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setBno(100+i);
			vo.setName("홍길동"+i);
			vo.setTitle("안녕하세요?"+i);
			// => 임시 생성 객체 (서비스로부터 전달받은 객체 정보)

			sampleList.add(vo);
		}
		
		ResponseEntity<List<SampleVO>> respEntity = null;
		
		if(sampleList != null) {
			respEntity = new ResponseEntity<List<SampleVO>>(sampleList, HttpStatus.OK);
		}else { // null
			respEntity = new ResponseEntity<List<SampleVO>>(sampleList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		ResponseEntity<SampleVO> respEntity = 
//				new ResponseEntity<SampleVO>(vo, HttpStatus.OK);
		
		logger.info(" SampleVO 객체 정보를 가지고 200 상태 코드를 전달");
		return respEntity;
		
	}
	
	@RequestMapping(value="/test10", method=RequestMethod.GET)
	public ResponseEntity restTest10() throws Exception{
		logger.info(" /rest/test10 -> restTest10() 실행 ");
		
		// 서비스, DB처리 동작 호출
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		String msg = "<script>";
		msg += " alert('자바스크립트 코드 실행');";
		msg += " location.href='/test2';";
		msg += "</script>";
		
		return new ResponseEntity(msg, headers, HttpStatus.CREATED);
		
	}
	
}
