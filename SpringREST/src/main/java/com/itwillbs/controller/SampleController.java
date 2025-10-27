package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//@ResponseBody : 메서드의 실행결과를 리소스로 생성 (일반 컨트롤러)
	
	// http://localhost:8088/test1
	@RequestMapping(value="/test1", method = RequestMethod.GET)
	//@ResponseBody
	public @ResponseBody String test1() throws Exception{
		logger.info(" 일반 컨트롤러 실행! ");
		logger.info(" /test1 -> test1() 실행! ");
		return "ITWILL";
	}
}
