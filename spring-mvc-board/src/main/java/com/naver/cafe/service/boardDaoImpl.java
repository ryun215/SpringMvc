package com.naver.cafe.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class boardDaoImpl implements BoardDao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertBoard(Board board) {
		
		return sqlSession.insert("com.naver.cafe.BoardMapper.boardAdd",board);
	}
	
}
