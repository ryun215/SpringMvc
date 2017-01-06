package com.naver.cafe.service;

import java.util.Map;

public interface BoardService {
	public int addBoard(Board board);
	Map<String, Object> getBoardListPerPage(int currentPage);
	Board getBoardByKey(Board boardNo);
	int deleteBoard(Board board);
	int updateBoard(Board board);
}
