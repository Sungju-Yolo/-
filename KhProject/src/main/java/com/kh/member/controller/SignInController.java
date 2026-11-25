package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.dto.MemberDto;
import com.kh.member.model.service.MemberService;

@WebServlet("/sign-in.do")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberID = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		MemberDto member = new MemberDto();
		member.setMemberId(memberID);
		member.setMemberPwd(memberPwd);
		
		MemberDto loginMember = new MemberService().login(member);
        // 성공했을 경우 : 조회에 성공한 컬럼값을 필드에 담은 멤버 객체의 주소값		
		// 실패했을 경우 : null값
		// System.out.println(loginMember);
		
		if(loginMember != null) { // 성공
			// 로그인에 성공한 회원의 정보를
			// 로그아웃을 하거나 브라우저를 종료하기 전까지는
			// 계속해서 유지하고 사용할 것 => session
			
			HttpSession session = request.getSession();
			
			//request.setAttribute("loginMember", loginMember);
			//request.getRequestDispatcher("/index.jsp")
				   //.forward(request, response);
			session.setAttribute("loginMember", loginMember);
			response.sendRedirect("/kh");
		} else { // 실패
			request.setAttribute("msg", "로그인에 실패하셨습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/result.jsp")
			       .forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 세션, 토큰
	
	// 로그인 된 사용자의 정보를 어딘가에 담아두어야 한다 => 단, 응답이 끝난 뒤에도 사용할 수 있어야 함
	
	/*
	 * 서버측에서 사용하는 "임시" 저장소 4총사 => Map 기반
	 * 
	 * 1) application : 웹 애플리케이션 전역에서 사용 가능
	 * 
	 * 2) session : 모든 JSP와 Servlet에서 값을 꺼내 쓸 수 있음
	 * 서버에서 사용하는 저장소
	 * 				단, session의 값이 지워지기 전까지
	 * 				언제 지워지느냐, 세션종료 시점 : 브라우저 종료, 서버 멈춤, 코드로 지움
	 * 
	 * 3) request : 클라이언트의 요청으로부터 해당 request를 포워딩한 JSP까지만 쓸 수 있음
	 * 				요청 => 응답
	 * 
	 * 4) page    : 값을 담은 JSP에서만 쓸 수 있음
	 * 
	 * => 일반적으로 사용하는 두가지는 session 과 request이다.
	 * session 의 일반적 의미 (연결된 상태)
	 * 
	 * 로그인된 상황에서 session 그 나머지는 request
	 */
	
	

}
