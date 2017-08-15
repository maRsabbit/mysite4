package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.ReplyBoardDao;
import com.mysite.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {
	@Autowired
	private ReplyBoardDao replyboarddao;
	

	public List<ReplyBoardVo> getlist() {
		
		return replyboarddao.getlist();
	}

	public ReplyBoardVo read(int no) {
		ReplyBoardVo bvo = replyboarddao.read(no);
		replyboarddao.plusHit(bvo);
		return bvo;
		
	}

	public int write(ReplyBoardVo vo) {
		return replyboarddao.write(vo);
	}
	

	public int reply(ReplyBoardVo vo) {
		replyboarddao.replyincrease(vo.getGroupNo(), vo.getOrderNo());
		return replyboarddao.reply(vo);
	}
	

	public int modify(ReplyBoardVo vo) {
		return replyboarddao.modify(vo);
		
	}

	public int delete(ReplyBoardVo vo) {
		return replyboarddao.delete(vo);
	}

	public int count() {
		// TODO Auto-generated method stub
		return replyboarddao.count();
	}






}
