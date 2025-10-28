package com.itwillbs.controller;

import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
}
