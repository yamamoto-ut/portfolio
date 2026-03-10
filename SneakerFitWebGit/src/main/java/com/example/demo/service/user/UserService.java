package com.example.demo.service.user;

import com.example.demo.entity.User;

public interface UserService {
	// 管理者登録
	void registAdmin(User user);
	// 一般ユーザー登録
	void registUser(User user);        
	
}
