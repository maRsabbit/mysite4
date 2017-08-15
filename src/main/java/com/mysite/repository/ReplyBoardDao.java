package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyBoardVo;
@Repository
public class ReplyBoardDao {
	@Autowired
	private SqlSession sqlSession;

	public List<ReplyBoardVo> getlist() {
		return sqlSession.selectList("replyboard.getList");
	}
	
	public ReplyBoardVo read(int no) {
		return sqlSession.selectOne("replyboard.read", no);
	}
	

	public int write(ReplyBoardVo vo) {
		return sqlSession.insert("replyboard.write", vo);
	}
	
	public int  replyincrease(int groupNo, int orderNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupno", groupNo);
		map.put("orderno", orderNo);
		return sqlSession.update("replyboard.replyIncrease",map);
	}

	public int reply(ReplyBoardVo vo) {
		int orderNo = vo.getOrderNo();
		int depth = vo.getDepth();
		++orderNo;
		++depth;
		vo.setOrderNo(orderNo);
		vo.setDepth(depth);

		return sqlSession.insert("replyboard.reply",vo);
	}

	public int plusHit(ReplyBoardVo vo) {
		return sqlSession.update("replyboard.plusHit", vo);
	}

	public int modify(ReplyBoardVo vo) {
		return sqlSession.update("replyboard.modify", vo);
	}

	public int delete(ReplyBoardVo vo) {
		return sqlSession.delete("replyboard.delete", vo);
	}

	public int count() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("replyboard.getCount");
	}







}
