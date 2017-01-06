package com.naver.cafe.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.cafe.service.Board;
import com.naver.cafe.service.BoardService;
	
	
	
//controller annotation : 객체를 등록해준다(bean형태) , servlet형태로 만들어준다.
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//수정폼에서 수정값 입력후 action post방식
	@RequestMapping(value="/board/boardModify", method=RequestMethod.POST)
	public String updateBoardAction(Board board){
		//boardService의 updateBoard메소드를 호출하고 매개변수로 board를 입력해줌
		boardService.updateBoard(board);
		//db에 값이 수정됨
		return "redirect:/board/boardList";
		//list로 리다이렉트
	}
	
	//수정요청 받음 get방식
	@RequestMapping(value="/board/boardModify", method=RequestMethod.GET)
	public String updateBoard(Model model, Board board){
		//board변수에 boardService의 boardNo값으로 하나의 게시글을 조회해오는 메소드 호출 매개변수로 board를 입력
		//매개변수인 board에는 boardNo하나만 들어있고 나머지는 null상태
		board =boardService.getBoardByKey(board);
		//한명의 회원정보를 조회해 온 결과를 Board타입의 객체참조변수 board에 담고 이를 model에 세팅해준다
		model.addAttribute("board", board);
		//board/boardModify 주소로 forwarding 해준다.
		return "/board/boardModify";
	}
	
	//비밀번호 입력받아 값 비교하여 삭제함 post방식
	@RequestMapping(value="/board/boardRemove", method=RequestMethod.POST)
	public String boardRemove(Board board){
		//boardService의 boardNo,boardPw값을 비교하여 삭제하는 메소드 호출 매개변수로 board를 입력해준다.
		//이때 매개변수 board에는 boardNo와 boardPw값이 존재한다.
		int result = boardService.deleteBoard(board);
		//메소드를 실행한 뒤 결과값을 숫자인 result로 받는데 삭제쿼리가 실행되면 1값 실행되지않으면 0값이 리턴된다.
		
		//result의 값이 0인지 그렇지않은지(0과 1밖에 안오기때문에) 비교하여 0이면 다시 비밀번호 입력폼으로 포워딩
		//0이아니면 list로 리다이렉트해준다.
		if(result==0){
			return "/board/boardRemove";
		}else{
			return "redirect:/board/boardList";
		}
	}
	
	// 삭제요청 get방식
	@RequestMapping(value="/board/boardRemove" ,method=RequestMethod.GET)
	public String boardRemove(Model model,@RequestParam(value="boardNo")int boardNo){
		//삭제요청시 매개변수로 Model객체와 요청시 넘어오는 request에 담긴 boardNo를 int형태로 형변환해서 받는다.
		// model에 boardNo를 세팅해주고
		model.addAttribute("boardNo", boardNo);
		// boardRemove 페이지로 포워딩시켜준다.
		return "/board/boardRemove";
	}
	
	// view페이지로 요청 get방식
	@RequestMapping(value="/board/boardView" ,method=RequestMethod.GET)
	public String boardView(Model model,Board board){
		//view 요청시 boardNo값을 넘겨주는데 이를 board가 받는다. 
		//
		
		if(board.getBoardNo() == 0){
			return "/board/error";
		}else{
			board = boardService.getBoardByKey(board);
			
			model.addAttribute("board", board);
			
			return "/board/boardView";
		}
	}
	
	@RequestMapping(value="/board/boardList")
	public String boardList(Model model,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage){
		Map<String, Object> returnMap = boardService.getBoardListPerPage(currentPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRowCount", returnMap.get("totalRowCount"));
		model.addAttribute("lastPage", returnMap.get("lastPage"));
		model.addAttribute("list", returnMap.get("list"));
		return "/board/boardList";
		
	}
	
	@RequestMapping(value="/board/boardAdd", method=RequestMethod.GET)
	public String boardAdd(){
		return "/board/boardAdd"; 
	}
	
	@RequestMapping(value="/board/boardAdd", method=RequestMethod.POST)
	public String boardAdd(Board board){
		System.out.println(board);
		boardService.addBoard(board);
		return "redirect:/board/boardList";
	}
	
}

