package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

//현재 클래스를 스프링에서 관리하는 service bean으로 설정
// 1개의 Service => 1개의 DAO
// 1개의 Service => n개의 DAO
@Service
public class MemberServiceImpl implements MemberService {

	
	@Inject
	MemberDAO memberDao;
	
	//MemberDAO memberDao = new MemberDAOImpl();
	
	
	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		//return null;
		return memberDao.memberList();
	}

	@Transactional
	@Override
	public void insertMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		memberDao.insertMember(dto);
		//memberDao.deleteMember(dto.getUserid());
		/*
		 * 회원탈퇴의 경우
		 * 회원정보 뿐만 아니라 해당 회원의 다른 정보도 삭제해야 하는 경우
		 * - 회원정보 삭제
		 * - 구매목록 삭제
		 * - 게시글 삭제
		 * - 등등등
		 * @Transactional 으로 선언되었을 경우
		 * 위의 단위 기능 중에서 하나라도 실패할 경우 전부 트랜잭션 처리
		 */
	}

	@Override
	public MemberDTO viewMember(String userid) {
		// TODO Auto-generated method stub
		return memberDao.viewMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
		// TODO Auto-generated method stub
		memberDao.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		memberDao.updateMember(dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		// TODO Auto-generated method stub
		return memberDao.checkPw(userid, passwd);
	}

}
