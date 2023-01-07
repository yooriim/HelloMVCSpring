package com.web.spring.member.model.service;

import java.util.Map;

import com.web.spring.member.model.vo.Member;

public interface MemberService {
	
	Member selectMemberById(Member m);
	
	int enrollMember(Member m);
	
	int updatePassword(Map param);

	int updateMember(Member m);
	
	int deleteMember(String userId);
}
