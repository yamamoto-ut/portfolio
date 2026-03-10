package com.example.demo.repository;

import com.example.demo.entity.User;

public interface UserRepository {
	//ユーザ登録のメソッド
	void insert(User user);
	
	//ユーザIDをもとにユーザの検索する
	User selectByUserId(String userId);
	
}
