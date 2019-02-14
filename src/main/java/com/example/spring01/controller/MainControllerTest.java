package com.example.spring01.controller;

//import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// junit 4.0 버전으로 테스트
@RunWith(SpringJUnit4ClassRunner.class)
// web-application의 설정정보를 가져옴
// 스프링의 설정파일을 가져와야 테스트 함
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) // servlet-context.xml, root-context.xml
public class MainControllerTest {
	
	//spring에서 instance를 만든 다음에 전달해 줌(의존관계 주입, DI, IoC)
	@Inject
	WebApplicationContext wac;
	
	//Mvc 테스트
	MockMvc mockMvc;
	
	@Before //junit의 before를 써야함. 테스트 하기 전에 미리 실행되는 코드
	public void setup() throws Exception{
		//실제 mvc를 테스트 하기 위한 인스턴스 생성
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	//junit 이 테스트하는 코드
	@Test
	public void testMain() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/main"));
	}

	@Test
	public void testGugu() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/gugu.do"));
	}

	@Test
	public void testTest() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/test.do"));
	}

	@Test
	public void testDoA() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doA"));
	}

	@Test
	public void testDoB() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doB"));
	}

	@Test
	public void testDoC() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doC"));
	}

	@Test
	public void testDoD() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doD"));
	}

	@Test
	public void testDoE() throws Exception {
		//fail("Not yet implemented");
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doE"));
	}

}
