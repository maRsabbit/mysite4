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

import com.mysite.service.ReplyBoardService;
import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.UserVo;

@Controller
@RequestMapping("/reply")
public class ReplyBoardController {

	@Autowired
	private ReplyBoardService replyboardservice;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getList(Model model) {
		List<ReplyBoardVo> list = replyboardservice.getlist();
		model.addAttribute("list", list);
		return "reply/list";
	}
	
	@RequestMapping(value="/read/{no}", method=RequestMethod.GET)
	public String read(@PathVariable("no") int no, HttpSession session,
			Model model) {
		System.out.println(no);
		ReplyBoardVo replyboardvo = replyboardservice.read(no);
		System.out.println(replyboardvo);
		model.addAttribute("replyboardvo", replyboardvo);
		
		return "reply/read";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:list";
		} else {
			return "reply/writeform";
		}
	}
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute ReplyBoardVo replyreplyboardvo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		replyreplyboardvo.setUserNo(no); 
		System.out.println(replyreplyboardvo.toString());
		replyboardservice.write(replyreplyboardvo);
		
		return "redirect:list";
		//return "redirect:/board/read/"+no;
	}
	
	@RequestMapping(value="/replyform", method=RequestMethod.GET)
	public String replyform(@PathVariable("no") int no, HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:list";
		} else {
			return "reply/replyform";
		}
	}
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute ReplyBoardVo replyboardvo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		replyboardvo.setUserNo(no); 
		System.out.println(replyboardvo.toString());
		replyboardservice.reply(replyboardvo);
		
		return "redirect:list";
		//return "redirect:/board/read/"+no;
	}
	
	@RequestMapping(value="/modifyform/{no}", method=RequestMethod.GET)
	public String modifyform(@PathVariable("no") int no,
							 HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		ReplyBoardVo replyboardvo = replyboardservice.read(no);
		System.out.println(authUser);
		System.out.println(replyboardvo);
		if(authUser == null) {
			return "redirect:/user/loginform";
		} else {
			if(authUser.getNo() == replyboardvo.getUserNo()) {
				model.addAttribute("replyboardvo", replyboardvo);
				
				return "reply/modifyform";
			} else {
				return "redirect:list";
			}
		}
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute ReplyBoardVo replyboardvo) {
		System.out.println(replyboardvo.toString());
		int no = replyboardvo.getNo();
		System.out.println(no);
		replyboardservice.modify(replyboardvo);
		//return "redirect:list";
		return "redirect:/reply/read/"+no;
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(HttpSession session,  @PathVariable("no") int no) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) { // 鍮꾨줈洹몄씤 �궗�슜�옄 李⑤떒�슜
			return "redirect:/user/loginform";
		} else {
			ReplyBoardVo replyboardvo = replyboardservice.read(no);
			// �젙蹂� �솗�씤
			System.out.println("authUser.getNo() = "+authUser.getNo()+"\n"+
					"replyboardvo.getUserNo() = "+replyboardvo.getUserNo());
			
			if(authUser.getNo()==replyboardvo.getUserNo()) {
				replyboardservice.delete(replyboardvo);
				return "redirect:/reply/list";
			} else {
				return "redirect:/user/loginform";
			}
		}
	}
}
