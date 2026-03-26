package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
	
	private final UserRepository userRepository;
	//ユーザIDをもとに、論理削除する
	@Override
	public void deleteAccount(String userId) {
		 userRepository.updateIsDeleted(userId);

	}
	//ユーザIDをもとに、自分のレビュー一覧を取得する
	@Override
	public List<Review> findReviewsByUserId(String userId) {
		return userRepository.selectReviewsByUserId(userId);
	}

}
