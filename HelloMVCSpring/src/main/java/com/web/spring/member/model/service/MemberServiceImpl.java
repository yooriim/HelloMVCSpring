package com.web.spring.member.model.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.web.spring.member.model.dao.MemberDao;
import com.web.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao dao;
	private SqlSessionTemplate session;
	
	public MemberServiceImpl(MemberDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}

	@Override
	public Member selectMemberById(Member m) {
		// TODO Auto-generated method stub
		return dao.selectMemberById(session,m);
	}

	@Override
	public int enrollMember(Member m) {
		// TODO Auto-generated method stub
		return dao.enrollMember(session,m);
	}

	@Override
	public int updatePassword(Map param) {
		// TODO Auto-generated method stub
		return dao.updatePassword(session,param);
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return dao.updateMember(session,m);
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(session,userId);
	}


	
	
}
