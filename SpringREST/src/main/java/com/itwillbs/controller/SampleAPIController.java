package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itwillbs.domain.SampleAPIVO;

@RestController
@RequestMapping(value="/api/*")
public class SampleAPIController {
	private static final Logger logger = LoggerFactory.getLogger(SampleAPIController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	// RestTemplate? 웹 서비스(API)를 호출할때 사용하는 객체
	//				 HTTP 요청/HTTP 응답/ 
	// - REST 설계 가이드를 무시해도됨
	
	//RestTemplate rt = new RestTemplate();
	
	//@GetMapping("path")
	//public String test() {
		// GET방식 호출
		// rt.getForObject(null, null); 객체
		// rt.getForEntity(null, null); 객체+상태(ResponseEntity)
		
		// POST방식 호출
		// rt.postForEntity(null, logger, null);
		// rt.postForLocation(null, logger);
		// rt.postForObject(null, logger, null);
		
		// PUT 방식 호출
		// rt.put(null, logger);
		
		// DELETE 방식 호출
		// rt.delete(null);
		
		// HTTP 메서드 상관없이 모든 경우 사용가능
		// rt.exchange(null, null)
		
	//	return "";
	//}
	
	// HttpMessageConverter
	// 송수신에 전달되는 데이터를 자바 객체로 자동 변환 객체
	
	// 클라이언트 호출 <-> {데이터} <-> RestTemplate <-> {JSON,XML} <-> API
	// 								  HttpMessageConverter
	
	// API 호출하는 동작
	// 호출 주소 : /api/call
	@RequestMapping(value="/call", method=RequestMethod.GET)
	public ResponseEntity<SampleAPIVO> callAPI() throws Exception{
		logger.info("callAPI() 실행! ");
		
		String URL = "https://dog.ceo/api/breeds/image/random";
		//RestTemplage 객체를 사용해서 API 호출
		//restTemplate.getForEntity(주소, 응답받을 파일);
		// => 결과 + 상태정보
		ResponseEntity<SampleAPIVO> result = restTemplate.getForEntity(URL, SampleAPIVO.class);
		
		// SampleAPIVO apiVO = restTemplate.getForObject(URL, SampleAPIVO.class);
		// => 결과
		// => 추가 동작을 수행가능(기존의 DB데이터와 연산/추가/수정)
		
		//return new ResponseEntity<SampleAPIVO>(apiVO, HttpStatus.OK);
		//return ResponseEntity.ok(apiVO);
		
		logger.info(result+"");
		
		return result;
	}
	
}
