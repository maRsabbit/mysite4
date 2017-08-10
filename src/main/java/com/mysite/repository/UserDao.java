package com.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo uservo) {
		
		return sqlSession.insert("user.insert", uservo);
	}
	public int updateuser(UserVo uservo) {
		System.out.println(uservo.toString()); 
		return sqlSession.update("user.update", uservo);
	}
	public UserVo getuser(String email, String password) {
		Map<String, Object> usermap = new HashMap<String, Object>();
		usermap.put("email", email);
		usermap.put("password", password);
		
		UserVo uservo = sqlSession.selectOne("selectUserByEmailPw", usermap);
		
		return uservo;
	}

	public UserVo getuser(int no) {
		UserVo uservo = sqlSession.selectOne("selectUserByNo", no);
		return uservo;
	}
}
