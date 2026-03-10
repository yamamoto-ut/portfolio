package com.example.demo.service.review;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.review.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistServiceImple implements RegistService {
//ここはサービス層 業務処理
	
	private final ReviewRepository repository;
	/*----------------追加分------------------------*/
//	private final SneakerRepository sneakerRepository;
	/*----------------ここまで追加分------------------------*/
	@Override
	public void regist(Review review) {
		
		repository.add(review);

	}
	/*----------------追加分------------------------*/
	@Override
    public String findManufacturerName(Integer manufacturerId) {
//        return sneakerRepository.findNameById(manufacturerId);
        return repository.findNameById(manufacturerId);
    }
	/*----------------ここまで追加分------------------------*/
}
