package com.example.demo.service.review;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.review.ReviewRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReviewEditServiceImpl implements ReviewEditService {
	
	private final ReviewRepository repository;

	@Override
	public void edit(Review review) {
		
		repository.update(review);

	}
}
