package com.naver.cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.cafe.service.Board;
import com.naver.cafe.service.BoardService;
	
	
	
//controller annotation : 객체를 등록해준다(bean형태) , servlet형태로 만들어준다.
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value="/board/boardList")
	public String boardList(Board board){
		return "/board/boardList";
		
	}
	
	
	@RequestMapping(value="/board/boardAdd", method=RequestMethod.GET)
	public String boardAdd(){
		return "/board/boardAdd"; // forward
	}
	
	@RequestMapping(value="/board/boardAdd", method=RequestMethod.POST)
	public String boardAdd(Board board){
		System.out.println(board);
		boardService.addBoard(board);
		return "redirect:/board/boardList";
	}
	
}

