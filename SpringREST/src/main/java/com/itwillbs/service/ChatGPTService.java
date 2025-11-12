package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 * ChatGPTService
 *   => 내가 만든 프로젝트의 규칙/흐름에 따른 처리
 *      (파라메터 검증, 권한, 로그 출력...)
 * */
@Service
public class ChatGPTService {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatGPTService.class);

	@Inject
	private ChatGPTClient gptClient;
	
	// API 호출 동작(질문정보를 가지고 호출)
	public String askChatGPT(String prompt) throws Exception{
		logger.info(" askChatGPT() 실행 ");
		
		// gpt 호출동작 실행
		gptClient.sendChatGPT(prompt);
		
		return "";
	}
}
