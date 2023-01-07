package com.web.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.web.spring.member.model.service.MemberService;
import com.web.spring.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	   public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
	      this.service=service;
	      this.passwordEncoder=passwordEncoder;
	   }
	
	//로그인
	@RequestMapping("/login.do")
	String loginMember(Member m,HttpSession session) {
		Member loginMember=service.selectMemberById(m);
		if(loginMember!=null
				&& passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			
			session.setAttribute("loginMember", loginMember);
			
		}
		
		return "redirect:/";
	}
	
	//회원가입 페이지로 이동
	@RequestMapping("/enrollMember.do")
	String enrollMember() {
		return "/member/enrollMember";
	}
	
	//회원가입
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
				
		//비번 암호화
		String encodePassword=passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
		
		
		System.out.println(m);
		
		int result=service.enrollMember(m);
		
		if(result>0) {
			mv.addObject("msg","횐갑성공");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg","횐갑실패");
			mv.addObject("loc","/member/enrollMember.do");
                                                                                                                                                                                                                                                 			
		}
		mv.setViewName("common/msg");
		return mv;
	}
	
	//아이디 중복확인
	@RequestMapping("/idDuplicate.do")
	public ModelAndView idDuplicate(Member m,ModelAndView mv) {
		
		Member member=service.selectMemberById(m);
		
		mv.addObject("member",member);
		mv.setViewName("member/idDuplicate");
		
		return mv;
	}
	
	//로그아웃
//	@RequestMapping("/logout.do")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
	
	@RequestMapping("/logout.do")
	public String logout(SessionStatus session) {
//		System.out.println("되나요? "+session);
		if(!session.isComplete()) {
			session.setComplete();
			//이거 쓸땐 컨트롤러 위에 @SessionAttributes({"loginMember"}) 써줘야 하나봐
		}
		return "redirect:/";
	}
	
	//내정보봅기
	@RequestMapping("/memberView.do")
	public ModelAndView memberView(ModelAndView mv,Member m) {
		Member member=service.selectMemberById(m);
		mv.addObject("member",member);
		mv.setViewName("member/editMember");
		return mv;
	}
	
	//비밀번호 변경 이동
	@RequestMapping("/updatePassword.do")
	public String updatePassword() {
		
		return "member/updatePassword";
		
	}
	
	//비밀번호 변경하기
	@RequestMapping("/updatePasswordEnd")
	public ModelAndView updatePasswordEnd(HttpServletRequest request,ModelAndView mv,Member m,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="password") String oriPass,
			@RequestParam(value="password_new") String newPass) {
		Map param=new HashMap();
		
		
		param.put("userId", userId);
		param.put("oriPass", oriPass);
		param.put("newPass", newPass);
		
		String encodePassword=passwordEncoder.encode(newPass);
		param.put("newPass",encodePassword);

		Member member=service.selectMemberById(m);
		
		if(member!=null ) {
			
			int result=service.updatePassword(param);
			if(result>0) {
				String script="opener.location.replace('"+request.getContextPath()+"/member/logout.do');close();";
//				String script="opener.location.replace('"+"${pageContext.request.contextPath}"+"/logout.do');close();";
				//http://localhost:8080/spring/member/logout.do
				mv.addObject("msg","비밀번호 변경 성공");
				mv.addObject("loc","/member/logout.do");
				mv.addObject("script",script);
			}else {
				mv.addObject("msg","비밀번호 변경 실패");
				mv.addObject("loc","/member/updatePassword.do?userId="+userId);				
				
			}
			
		}else {
			mv.addObject("msg","현재 비밀번호가 일치하지 않습니다. 다시 시도하세요!");
			mv.addObject("loc","/member/updatePassword.do?userId="+userId);
		}
				
		
		mv.setViewName("common/msg");
		
		
		return mv;
	}
	
	@RequestMapping("/updateMember.do")
	public ModelAndView updateMember(ModelAndView mv,Member m,HttpSession session) {
		
		int result=service.updateMember(m);
		
		System.out.println(result);
		System.out.println(m);
		
		if(result>0) {
			mv.addObject("msg","회원정보 수정완료");
			mv.addObject("loc","/");
			
			session.setAttribute("loginMember", m);
			
			
		}else {
			mv.addObject("msg","회원정보 수정실패");
			mv.addObject("loc","/memberView.do");

		}
		
		mv.setViewName("common/msg");
		
		
		return mv;
	}
	
	//탈퇴
	@RequestMapping("/deleteMember.do")
	public ModelAndView deleteMember(ModelAndView mv,String userId) {
		int result=service.deleteMember(userId);
		if(result>0) {
			mv.addObject("msg", "탈퇴 성공");
			mv.addObject("loc", "/member/logout.do");
			
		}else {
			mv.addObject("msg", "탈퇴 실패");
			mv.addObject("loc", "/member/memberView.do");
			
		}
		
		mv.setViewName("common/msg");
		
		return mv;
		
	}
	


}
