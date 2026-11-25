package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.dto.MemberDto;
import com.kh.member.model.service.MemberService;

@WebServlet("/join.do")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 방식이 get이냐 post 냐 post 면 가입할 때 이름을 홍길동이라 적었는데 get파라미터면 오류발생
		// POST
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 넘어오는 것은 "요청시 전달 값" => (Request Parameter)
		String memberId = request.getParameter("memberId");
		// 인자값은 키값 네임 속성에 있는 것이 키 값
		String memberPwd = request.getParameter("memberPwd");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		
		// 3) 위 4개를 DTO로 가공 묶어줌 "데이터 가공" (서비스로 가기 위해
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setMemberPwd(memberPwd);
		member.setMemberName(memberName);
		member.setEmail(email);
		
		// 4) 요청처리 요청 처리는 자바 => 서비스 멤버 서비스가 받아야하는데 멤버 서비스가 받으려면?
		// 서비스 메소드 호출하기 위해 객체 생성
		int result = new MemberService().insertMember(member);
		// 서블릿의 존재 의의 프로그램을 매 번 띄우지 말고 계속 떠있는 JVM 안에서 Java 객체가 요청을 처리하게 하자
		
		// 정수형 int까지 받고 이제 뭐하지?
		// 회원가입에 성공했는지 실패했는지에 따라서 응답화면을 다르게 지정
		
		if(result > 0) { //성공
			/*
			 * //mainPage로 가야하는데 가는 방법은
			request.getRequestDispatcher("/index.jsp")
			.forward(request, response);
		// sendRedirect 방식
			 */
			response.sendRedirect("/kh");
			
			// sendRedirect : Client에게 URL을 다시 요청하게 한ㄷ
			// response 객체를 이용
			// response.sendRedirect("/다시 요청 보내게할 URL 경로");
		} else { //실패
			request.setAttribute("msg", "회원가입에 실패하셨습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/result.jsp")
			.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
