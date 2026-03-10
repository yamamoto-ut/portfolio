package com.example.demo.service.review;

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

}
