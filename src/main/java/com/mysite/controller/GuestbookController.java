package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.vo.GuestbookVo;
import com.mysite.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookservice;
	

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getList(Model model) {
		List<GuestbookVo> list = guestbookservice.getlist();
		model.addAttribute("list", list);
		return "guestbook/list";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		int count = guestbookservice.insert(vo);
		System.out.println(count + "건 등록");
		return "redirect:list";
	}
	
	@RequestMapping(value="/deleteform/{no}", method=RequestMethod.GET)
	public String deleteform(@PathVariable("no") int no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteform(@ModelAttribute GuestbookVo vo) {
		int count = guestbookservice.delete(vo);
		System.out.println(count + "건 삭제");
		return "redirect:list";
	}

}
