package com.web.spring.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.web.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member selectMemberById(SqlSessionTemplate session,Member m) {
		// TODO Auto-generated method stub
		return session.selectOne("member.selectMemberById",m);
	}

	@Override
	public int enrollMember(SqlSessionTemplate session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.enrollMember",m);
	}

	@Override
	public int updatePassword(SqlSessionTemplate session, Map param) {
		// TODO Auto-generated method stub
		return session.update("member.updatePassword",param);
	}

	@Override
	public int updateMember(SqlSessionTemplate session, Member m) {
		// TODO Auto-generated method stub
		return session.update("member.updateMember",m);
	}

	@Override
	public int deleteMember(SqlSessionTemplate session, String userId) {
		// TODO Auto-generated method stub
		return session.delete("member.deleteMember",userId);
	}
	
	



	
	
}
