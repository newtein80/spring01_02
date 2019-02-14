package com.example.spring01.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.ProductDTO;



//현재 클래스를 스프링에서 관리하는 컨트롤러 빈으로 등록함
@Controller
public class MainController {
	//로깅 선언
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//URL mapping
	@RequestMapping("/")
	public String main(Model model) {
		//Model에 자료 저장( ==> Servlet의 Request에 해당)
		model.addAttribute("message", "홈페이지 방문을 환영합니다. - ParkJongSoo");
		//main.jsp로 포워딩됨(WEB-INF/views/main.jsp)
		return "main";
	}
	
	/*@RequestMapping("gugu.do")
	public String gugu_old(Model model) {
		int dan  = 7;
		String result = "";
		
		for(int i=1; i <= 9; i++) {
			result += dan + "x" + i + "=" + dan * i + "<br>";
		}
		model.addAttribute("result", result);
		
		return "test/gugu";
	}*/
	
	/*@RequestMapping("gugu.do")
	public String gugu_old(Model model, HttpServletRequest request) {
		int dan  = 7;
		
		dan  = Integer.parseInt(request.getParameter("dan"));
		String result = "";
		
		for(int i=1; i <= 9; i++) {
			result += dan + "x" + i + "=" + dan * i + "<br>";
		}
		model.addAttribute("result", result);
		
		return "test/gugu";
	}*/
	
	/*@RequestMapping("gugu.do") // param의 이름과 매개변수의 이름이 같으면 된다
	public String gugu_old(Model model, int dan) {
		//int dan  = 7;
		
		//dan  = Integer.parseInt(request.getParameter("dan"));
		String result = "";
		
		for(int i=1; i <= 9; i++) {
			result += dan + "x" + i + "=" + dan * i + "<br>";
		}
		model.addAttribute("result", result);
		
		return "test/gugu";
	}*/
	
	//param의 이름과 매개변수의 이름이 같으면 된다, @RequestParam은 dan이라는 매개변수는 param으로 넘어오는 매개변수라는 선언 추가, @RequestParam = request.getParameter("dan")을 대체
	//@RequestParam은 생략가능하지만 기본값을 주기위해서 선언
	/*@RequestMapping("gugu.do")
	public String gugu_old(Model model, @RequestParam(defaultValue="3") int dan) {
		//int dan  = 7;
		
		//dan  = Integer.parseInt(request.getParameter("dan"));
		String result = "";
		
		for(int i=1; i <= 9; i++) {
			result += dan + "x" + i + "=" + dan * i + "<br>";
		}
		model.addAttribute("result", result);
		
		return "test/gugu";
	}*/
	
	@RequestMapping(value="gugu.do",method=RequestMethod.GET)
	public String gugu(@RequestParam(defaultValue="3") int dan, Model model)
	{
		String result = "";
		for(int i=1; i <= 9; i++) {
			
			result += dan + "x" + i + "=" + dan * i + "<br>";
		}
		
		model.addAttribute("result", result);
		
		return "test/gugu";
	}
	
	@RequestMapping("test.do")
	public String test() {
		//return 값이 없을(void)경우 @RequestMapping과 같은 이름의 페이지로 이동
		return "test";
	}
	
	@RequestMapping("test/doA")
	public String doA(Model model) {
		//자료저장
		model.addAttribute("message", "방문을 환영합니다.");
		//포워딩
		return "test/doA";
	}
	
	//return 값이 없을(void)경우 @RequestMapping과 같은 이름의 페이지로 이동
	@RequestMapping("test/doB")
	public void doB() {
		logger.info("doB 호출.....");
	}
	
	//Model - 전달할 데이터
	//ModelAndView - 전달할 데이터(Model)와 포워딩할 페이지 정보(View) 포함
	@RequestMapping("test/doC")
	public ModelAndView doC() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("product", new ProductDTO("샤프", 1000));
		
		//new ModelAndView("페이지이름", "변수", 값);
		return new ModelAndView("test/doC", "map", map);
	}
	
	@RequestMapping("test/doD")
	public String doD() {
		//다시 컨트롤러를 실행!
		return "redirect:/test/doE";
	}
	
	@RequestMapping("test/doE")//doE.jsp로 포워딩
	public void doE() {
		
	}
	
	//뷰를 리턴하는 것이 아닌 데이터 자체를 리턴할 경우
	//@ResponseBody --> dto를 json으로 변환(jackson-databind를 사용)
	
	//@RestController로 사용하는 방법을 위해 주석처리
	/*
	@ResponseBody
	@RequestMapping("test/doF")
	public ProductDTO doF() {
		return new ProductDTO("냉장고", 500000);
	}*/
}
