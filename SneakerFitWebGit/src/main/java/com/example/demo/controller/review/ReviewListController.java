package com.example.demo.controller.review;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Review;
import com.example.demo.form.review.ReviewSearchForm;
import com.example.demo.service.review.ReviewListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewListController {
	
	private final ReviewListService service;
	
	//レビュー検索リクエスト
	@GetMapping("/search-review")
	public String searchReview(@ModelAttribute ReviewSearchForm form, Model model) {
		
		// DBからメーカーIDに紐づくレビューを取得 modelに格納してviewに渡す（画面表示）
		// 取得したレビューをリストに格納
		List<Review> list = service.findByManufacturerId(form.getManufacturerId());
		
		
		if (list.size() > 0) {
			model.addAttribute("reviewList", list);
			//レビューが見つかった場合、レビューリストを表示
		}
		return "review/review-list";
		
	}
	
}
