package com.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.service.BoardService;
import com.mysite.vo.BoardVo;
import com.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardservice;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getList(Model model) {
		List<BoardVo> list = boardservice.getlist();
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping(value="/read/{no}", method=RequestMethod.GET)
	public String read(@PathVariable("no") int no, HttpSession session,
			Model model) {
		System.out.println(no);
		BoardVo boardVo = boardservice.read(no);
		System.out.println(boardVo);
		/*
		int hit = boardVo.getHit();
		hit = hit+1;
		boardVo.setHit(hit);
		boardservice.plusHit(boardVo);
		*/
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:list";
		} else {
			return "board/writeform";
		}
	}
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardvo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		boardvo.setUserNo(no); 
		System.out.println(boardvo.toString());
		boardservice.write(boardvo);
		
		return "redirect:list";
		//return "redirect:/board/read/"+no;
	}
	
	@RequestMapping(value="/modifyform/{no}", method=RequestMethod.GET)
	public String modifyform(@PathVariable("no") int no,
							 HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BoardVo boardVo = boardservice.read(no);
		System.out.println(authUser);
		System.out.println(boardVo);
		if(authUser == null) {
			return "redirect:/user/loginform";
		} else {
			if(authUser.getNo() == boardVo.getUserNo()) {
				model.addAttribute("boardVo", boardVo);
				
				return "board/modifyform";
			} else {
				return "redirect:list";
			}
		}
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardvo) {
		System.out.println(boardvo.toString());
		int no = boardvo.getNo();
		System.out.println(no);
		boardservice.modify(boardvo);
		//return "redirect:list";
		return "redirect:/board/read/"+no;
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(HttpSession session,  @PathVariable("no") int no) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) { // 비로그인 사용자 차단용
			return "redirect:/user/loginform";
		} else {
			BoardVo boardvo = boardservice.read(no);
			// 정보 확인
			System.out.println("authUser.getNo() = "+authUser.getNo()+"\n"+
					"boardvo.getUserNo() = "+boardvo.getUserNo());
			
			if(authUser.getNo()==boardvo.getUserNo()) {
				boardservice.delete(boardvo);
				return "redirect:/board/list";
			} else {
				return "redirect:/user/loginform";
			}
		}
	}
}
