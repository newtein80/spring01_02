package com.example.spring01.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.MemberDTO;

//현재 클래스를 스프링에서 관리하는 dao bean으로 설정(서버가 올라올 때 single-ton으로 메모리에 올라옴)
@Repository
public class MemberDAOImpl implements MemberDAO {

	//mybatis의 SqlSession 객체를 스프링에서 주입시킴
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		//return null;
		return sqlSession.selectList("member.memberList");
		
		//MemberController => MemberService => MemberDAO => SqlSession => memberMapper.xml
		//DI에 의해서 MemberDAO => SqlSession 이 MemberDAO <= SqlSession <= Spring framework 와 같이 된다.
		//객체 생성부터 소멸까지 SpringFramework에서 관리한다.
	}

	@Override
	public void insertMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		// auto commit, auto close
		sqlSession.insert("member.insertMember", dto);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		// TODO Auto-generated method stub
		//return null;
		//전달할 record가 1개 : selectOne
		//			  2개 : selectList
		return sqlSession.selectOne("member.viewMember", userid);
	}

	@Override
	public void deleteMember(String userid) {
		// TODO Auto-generated method stub
		sqlSession.delete("member.deleteMember", userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.update("member.updateMember",dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		// TODO Auto-generated method stub
		//return false;
		boolean result = false;
		
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		int count = sqlSession.selectOne("member.checkPw",map);
		
		if(count == 1) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}

}
