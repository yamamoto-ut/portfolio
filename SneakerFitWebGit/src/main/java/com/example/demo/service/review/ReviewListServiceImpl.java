package com.example.demo.service.review;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.review.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewListServiceImpl implements ReviewListService {
	//レビュー検索サービスの実装クラス
	private final ReviewRepository repository; 

	@Override
	public List<Review> findByManufacturerId(int manufacturerId) {
		
		List<Review> list = repository.selectByManufacturerId(manufacturerId);
		
		return list;
	}

	@Override
	public List<Review> findReviewsByManufacturerIdWithFilters(Integer manufacturerId, String modelName,
			BigDecimal footLength, BigDecimal footWidth, BigDecimal instepHeight) {
			//フィルタリング機能を使って、メーカーIDに紐づくレビューを取得するサービスの実装
			return repository.findReviewsByManufacturerIdWithFilters(manufacturerId, modelName, footLength, footWidth, instepHeight);
	
	}

}
