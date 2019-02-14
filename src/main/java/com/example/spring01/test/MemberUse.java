package com.example.spring01.test;

import javax.inject.Inject;

// 의존관계  : MemberUse는 Member에 의존한다.
class Member{
		String userid;
		String passwd;
		String name;
		
		//public Member() {}
		private Member() {} // 외부에서 new로 인스턴스를 만들 수 없음
		
		/*
		private 으로 설정되어 있는 경우 아래와 같이 static, instance를 만들고 외부에서 사용할 수 있는 Method를 만들어
		Member m = Member.getInstance(); 와 같이 사용한다.
		*/
		/*
		private static Member instance;
		public static Member getInstance()
		{
			if(instance == null) {
				instance = new Member();
			}
			return instance;
		}*/
}
public class MemberUse {
	// 의존관계 주입 (Dependency Injection, DI)
	// 객체 생성, 소멸 (lifecycle 관리)
	// Ioc - 객체에 대한 제어권이 개발자가 아닌 스프링으로 이동
	@Inject
	Member m;
	public MemberUse(Member m) {
		
	}
	
	/*public MemberUse() {
		Member  m = new Member();
		//Member m = Member.getInstance();
	}*/
}
