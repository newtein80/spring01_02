package com.example.spring01.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.MemberService;

@Controller // 스프링에서 관리하는 컨트롤러 빈에 등록
public class MemberController {
	
	@Inject
	MemberService memberService;
	@Inject
	MemberDAO memberDao;
	
	//menu.jsp에서 설정한 url
	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		
		List<MemberDTO> list = memberService.memberList();
		
		model.addAttribute("list", list);
		
		
		
		// WEB-INF/views/member/member_list.jsp로 포워딩
		return "member/member_list";
	}
	
	@RequestMapping("member/write.do")
	public String write() {
		return "member/write";
	}
	
	//태그의 name이 DTO의 이름과 같아야 한다.(뭔가 문제가 있을듯...)
	//@ModelAttribute : 폼에서 전달된 값을 저장하는 객체
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		//System.out.println(dto);
		
		//F4를 누르면 '구현'한 코드를 볼 수 있는 곳으로 바로 간다.
		//memberService.insertMember(dto);
		
		//다음과 같이 바로 DAO를 사용할 수 있다.
		memberDao.insertMember(dto);
		return "redirect:/member/list.do";
	}
	
	//다음과 같이도 가능하다
	/*
	@RequestMapping("member/insert.do")
	public String insert(@RequestParam String userid, @RequestParam String passwd, @RequestParam String name, @RequestParam String email) {
		return "redirect:/member/list.do";
	}
	*/
	
	/*
	 * insert.do
	 * -> MemberController.java
	 * -> MemberService.java(선언) -> MemberServiceImpl.java(구현)
	 * -> MemberDAO.java(선언) -> MemberDAOImpl.java(구현)
	 * -> memberMapper.xml (**** root-context.xml 에 정의 ****)
	 * 
	 */
	
	/*
	 * 트랜잭션
	 * --> 논리적 기능 : 세부적인 기능 + 세부적인 기능 + .....
	 * 
	 * 계좌이체 -> 출금 + 입금
	 * 
	 * 상품구매 -> 구매 + 포인트
	 * 
	 * 결제 ->  포인트 사용 + 카드 결제
	 *
	 */
	
	@RequestMapping("member/view.do")
	//public String View(@RequestParam String userid, Model model) {
	public String View(String userid, Model model) {
		//model에 자료를 저장하고
		model.addAttribute("dto", memberService.viewMember(userid));
		//view.jsp로 포워딩
		return "member/view";
	}
	
	@RequestMapping("member/udpate.do")
	public String update(MemberDTO dto, Model model, Date join_date) {
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		
		if(result) {
			memberService.updateMember(dto);
			return "redirect:/member/list.do";//redirect
		}
		else {
			model.addAttribute("dto", dto);
			model.addAttribute("join_date", memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message","비밀번호를 확인하세요.");
			return "member/view";//forward
		}
		
		/*
		 * forward : 주소는 그대로이며 화면은 변경됨 (대량의 데이터를 전달)
		 * 			화면에 출력 용도
		 * redirect : 주소도 변경되고 화면도 변경 (parameter만 전달 가능)
		 * 			화면 변경 용도
		 */
	}
	
	@RequestMapping("member/delete.do")
	public String delete(String userid, String passwd, Model model) {
		boolean result = memberService.checkPw(userid, passwd);
		
		if(result) {
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		}else {
			model.addAttribute("message","비밀번호를 확인하세요.");
			model.addAttribute("dto", memberService.viewMember(userid));
			return "member/view";//forward
		}
	}
}
