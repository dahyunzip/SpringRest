package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.service.ChatGPTService;

@Controller
public class ChatGPTController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatGPTController.class);
	
	@Inject
	private ChatGPTService gptService;
	
	// oepnAI API 사용한 테스트
	@RequestMapping(value="/ask", method=RequestMethod.GET)
	public String startGPT() throws Exception{
		logger.info(" startGPT() 실행! ");
		logger.info(" /views/gpt/startGPT.jsp 페이지 이동");
		return "/gpt/startGPT";
	}
	
	// POST - http://localhost:8088/sendAsk
	// GPTAPI 호출하기
	@RequestMapping(value="/sendAsk", method=RequestMethod.POST)
	public String sendGPT(@RequestParam("prompt") String prompt) throws Exception{
		logger.info("sendGPT() 실행! ");
		
		// 1. 전달된 파라메터 정보를 저장
		logger.info("prompt : " + prompt);
		
		// 2. 그 정보를 사용해서 처리(API 호출)
		gptService.askChatGPT(prompt);
		
		// 3. 그 결과를 받아서 뷰페이지로 전달
		
		
		
		return "/gpt/startGPT";
	}
	
}
