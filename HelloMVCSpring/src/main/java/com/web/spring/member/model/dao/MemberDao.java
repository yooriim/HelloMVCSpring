package com.web.spring.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.web.spring.member.model.vo.Member;

public interface MemberDao {
	Member selectMemberById(SqlSessionTemplate session,Member m);
	
	int enrollMember(SqlSessionTemplate session,Member m );
	
	int updatePassword(SqlSessionTemplate session,Map param);

	int updateMember(SqlSessionTemplate session,Member m);
	
	int deleteMember(SqlSessionTemplate session,String userId);
}
