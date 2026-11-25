package com.kh.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.member.model.dto.MemberDto;

public class MemberDao {
	
	
	public int insertMember(SqlSession sqlSession, MemberDto member) {
		return sqlSession.insert("memberMapper.insertMember", member);
		// "" 안에 들어가는 건 네임 속성 값 다음 . 참조하여 아이디 속성 값, 아이디 속성 값은 메소드 식별자와 똑같이 적어줌
	}
	
	public MemberDto login(SqlSession sqlSession, MemberDto member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}

}
