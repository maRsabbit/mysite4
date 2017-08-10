package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.UserDao;
import com.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userdao;
	
	public int join (UserVo uservo) {
		
		return userdao.insert(uservo);
	}
	public int updateuser (UserVo uservo) {
		
		return userdao.updateuser(uservo);
	}
	public UserVo getuser(String email, String password) {
		
		return userdao.getuser(email, password);
	}
	public UserVo getuser(int no) {
		
		return userdao.getuser(no);
	}
}
