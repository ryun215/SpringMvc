package com.naver.cafe.service;

import java.util.List;
import java.util.Map;

public interface BoardDao {
	public int insertBoard(Board board);
	int selectTotalBoardCount();
	List<Board> selectBoardListPerPage(Map<String, Integer> map);
	Board selectBoardByKey(Board board);
	int deleteBoard(Board board);
	int updateBoard(Board board);
}
