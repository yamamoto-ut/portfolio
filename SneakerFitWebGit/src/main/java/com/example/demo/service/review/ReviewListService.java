package com.example.demo.service.review;

import java.util.List;

import com.example.demo.entity.Review;
//レビュー検索サービス
public interface ReviewListService {
	//メーカーIDに紐づくレビューを取得するサービス
	List<Review> findByManufacturerId(int manufacturerId);
	
}
