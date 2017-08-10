package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.BoardVo;
@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getlist() {
		return sqlSession.selectList("board.getList");
	}
	
	public BoardVo read(int no) {
		return sqlSession.selectOne("board.read", no);
	}
	

	public int write(BoardVo vo) {
		return sqlSession.insert("board.write", vo);
	}

	public int plusHit(BoardVo vo) {
		return sqlSession.update("board.plusHit", vo);
	}

	public int modify(BoardVo vo) {
		return sqlSession.update("board.modify", vo);
	}

	public int delete(BoardVo vo) {
		return sqlSession.delete("board.delete", vo);
	}





}
