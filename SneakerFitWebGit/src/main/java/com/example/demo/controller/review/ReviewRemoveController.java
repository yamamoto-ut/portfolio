package com.example.demo.controller.review;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.form.review.MtReviewRemoveForm;
import com.example.demo.service.review.ReviewRemoveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewRemoveController {
	
	private final ReviewRemoveService service;
	
	
	//レビューの削除リクエスト
	@PostMapping("/mt-remove-review")
	public String mtRemoveReview(
		@Validated @ModelAttribute MtReviewRemoveForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "admin/review/mt-edit-review"; // 元の編集画面に戻す
//			throw new IllegalArgumentException("**removeReview()**");			
		}// 入力がエラーの場合例外を発生させる

		return "admin/review/mt-confirm-remove-review";		// 入力が正常の場合
	}
	
	//削除リクエスト最後の確認画面より
	@PostMapping("/mt-confirm-remove-review")
	public String mtConfirmRemoveReview(
			@Validated @ModelAttribute MtReviewRemoveForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			throw new IllegalArgumentException("**confirmRemoveReview()**");	
		}		// 入力がエラーの場合

		Review r = new Review();
		r.setReviewId(form.getReviewId());	
		r.setManufacturerId(form.getManufacturerId());
		r.setModelName(form.getModelName());
		r.setShoeSize(form.getShoeSize());
		r.setFootLength(form.getFootLength());
		r.setFootWidth(form.getFootWidth());
		r.setInstepHeight(form.getInstepHeight());
		r.setComment(form.getComment());

		service.remove(r);
		
		redirectAttributes.addFlashAttribute("msg", "(レビュー削除)");
		//フラッシュスコープに属性名msgでレビュー削除という属性名を設定している。PRGパターン
		return "redirect:/mt-complete";
	}
}
