package com.example.demo.service.review;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entity.Review;
//レビュー検索サービス
public interface ReviewListService {
	//メーカーIDに紐づくレビューを取得するサービス
	List<Review> findByManufacturerId(int manufacturerId);
	
	//メーカーIDに紐づくレビューを取得するサービス（フィルタリング機能をつかう）
	List<Review> findReviewsByManufacturerIdWithFilters(Integer manufacturerId, String modelName,
            BigDecimal footLength, BigDecimal footWidth,
            BigDecimal instepHeight);
}
