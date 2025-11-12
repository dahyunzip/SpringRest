package com.itwillbs.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
 * 	ChatGPTClient
 * 	-> 외부 API와 연결(통신) 동작을 수행
 * 	(API 호출에 필요한 정보 처리)
 *    
 *    얼마든지 다른 형태로 전환 가능(GPT, Gemini 등 다른 AI 호출시 사용 가능)
 * */

@Component
public class ChatGPTClient {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatGPTClient.class);

	// 외부 API호출을 위한 정보
	// url, OPENAI_API_KEY, model, temperature(답변온도)
	
	private String URL = "https://api.openai.com/v1/chat/completions";
	private String MODEL = "gpt-4o-mini"; // GPT 모델명
	private double TEMPERATURE = 0.5; // 답변온도
	
	//private String APIKEY = "sk-proj-cqTLL...";
	// appdata.properties 파일에서 정보를 가져온다(주입)
	@Value("${gpt.API_KEY}")
	private String APIKEY;
	
	//GPT API 호출
	public String sendChatGPT(String prompt) {
		
		// 1. (필수) 헤더정보를 설정
		// org.springframework.http.HttpsHeaders
		HttpHeaders headers = new HttpHeaders();
		//  -H "Content-Type: application/json" \
		//  -H "Authorization: Bearer $OPENAI_API_KEY" \
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + APIKEY);
		
		// 2. 요청 파라메터 정보 설정(JSON)
		// 시스템 설정(AI를 해당 서비스(프로젝트)에 활용)
		Map<String, String> roleSystem = new HashMap<>();
		roleSystem.put("role", "system");
		roleSystem.put("content", " 질문에 대한 답변을 깔끔하게 50자 이내로 답변해줘.");
//		roleSystem.put("content", "너는 000 서비스의 안내원 역할이다"
//				+ " 우리 서비스에서 데이터를 제공하겠다."
//				+ " 그 데이터를 가지고 추천 취업정보를 10개만 골라줘"
//				+ " 가져올 때 최대 1000글자 이내로 가져오고, 데이터는 공백없이 콤마(,)로 구분해서 연결"
//				+ " 별다른 설명없이 데이터만 가져와라."
//				);
		
		// 사용자 요청정보(질문) 설정
		Map<String, String> roleUser = new HashMap<>();
		roleUser.put("role", "user");
		roleUser.put("content", prompt); // 사용자가 view에서 입력한 질문
		
		// 리스트에 시스템 설정 + 사용자 질문정보 설정 저장
		List<Map<String, String>> messages = new ArrayList<>();
		messages.add(roleSystem);
		messages.add(roleUser);
		
		// Map 정보를 JSON 객체로 변환 (+ 필요한 정보를 추가)
		JSONObject requestData = new JSONObject();
		requestData.put("model", MODEL); // 모델명
		requestData.put("temperature", TEMPERATURE); // 답변온도
		requestData.put("messages", messages); // 메세지(시스템설정, 사용자 질문)

		logger.info(" ------------------ 전달 준비 완료! --------------------");
		
		// 3. HTTP 요청 정보를 관리하는 객체
		HttpEntity<String> httpEntity = new HttpEntity<>(requestData.toString(), headers);
		logger.info(" httpEntity : " + httpEntity);
		
		// 4. REST API 호출 객체 RestTemplate 생성
		RestTemplate restTemplate = new RestTemplate();
		
		// UTF-8 인코딩 설정
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		messageConverters.addAll(restTemplate.getMessageConverters());
		
		// RestTemplate 객체에 UTF-8 인코딩 설정
		restTemplate.setMessageConverters(messageConverters);
		
		// REST API 호출
		// restTemplate.exchange(호출주소, 호출방법, 요청데이터+헤더정보, 리턴받을 타입);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class );
		
		logger.info(" responseEntity : " + responseEntity);
		
		return "";
	}
}
