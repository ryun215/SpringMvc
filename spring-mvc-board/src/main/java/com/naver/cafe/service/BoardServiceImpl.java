package com.naver.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired	
	private BoardDao boardDao;
	@Override
	public int addBoard(Board board) {
		
		return boardDao.insertBoard(board);
	}
}
