package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boarddao;
	

	public List<BoardVo> getlist() {
		
		return boarddao.getlist();
	}

	public BoardVo read(int no) {
		return boarddao.read(no);
		
	}

	public int write(BoardVo vo) {
		return boarddao.write(vo);
	}
	

	public int plusHit(BoardVo vo) {
		return boarddao.plusHit(vo);
		
	}
	public int modify(BoardVo vo) {
		return boarddao.modify(vo);
		
	}

	public int delete(BoardVo vo) {
		return boarddao.delete(vo);
	}





}
