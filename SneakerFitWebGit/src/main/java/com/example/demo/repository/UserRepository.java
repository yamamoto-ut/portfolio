package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Review;
import com.example.demo.entity.User;

public interface UserRepository {
	//ユーザ登録のメソッド
	void insert(User user);
	
	//ユーザIDをもとにユーザの検索する
	User selectByUserId(String userId);
	
	
	// 論理削除
	void updateIsDeleted(String userId);

	// 自分のレビュー一覧取得
	List<Review> selectReviewsByUserId(String userId);
}
