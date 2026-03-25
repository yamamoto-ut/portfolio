 package com.example.demo.repository.review;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entity.Manufacturer;
import com.example.demo.entity.Review;

public interface ReviewRepository {
	
	List<Manufacturer> findAllManufacturereName();
	
	//DBへのレビュー登録を行うメソッド
	void add(Review review);

	//ここは、DBからレビュー 一覧を取得するメソッド
	List<Review> selectByManufacturerId(int manufacturerId);
	
	//DBからレビュー 一覧を取得するメソッド（フィルタリング機能をつかう）
	List<Review> findReviewsByManufacturerIdWithFilters(Integer manufacturerId, String modelName,
            BigDecimal footLength, BigDecimal footWidth,
            BigDecimal instepHeight);
	
	//レビューの編集処理
	void update(Review review);
	
	//レビューの削除処理
	void delete(Review review);
	
	//メーカーid から、メーカー名を取得するための追加分
	String findNameById(Integer manufacturerId);

}
