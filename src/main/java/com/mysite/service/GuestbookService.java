package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.vo.GuestbookVo;
import com.mysite.repository.GuestbookDao;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookdao;
	
	public List<GuestbookVo> getlist() {
		
		return guestbookdao.getlist();
	}
	
	public int insert(GuestbookVo vo) {
		return guestbookdao.insert(vo);
	}

	public int delete(GuestbookVo vo) {
		// TODO Auto-generated method stub
		return guestbookdao.delete(vo);
	}

}
