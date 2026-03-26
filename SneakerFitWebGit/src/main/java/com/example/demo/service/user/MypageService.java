package com.example.demo.service.user;

import java.util.List;

import com.example.demo.entity.Review;

public interface MypageService {
	//論理削除
	void deleteAccount(String userId);
	
	//自分のレビュー一覧取得
	List<Review> findReviewsByUserId(String userId);

}
