package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.SampleVO;

//@RestController : 모든 동작에 @ResponseBody 적용된 형태

@RestController
@RequestMapping(value="/rest/*")
public class SampleRESTController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleRESTController.class);
	
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
}
