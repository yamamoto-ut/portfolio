package com.example.demo.controller.review;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.form.review.MtEditReviewForm;
import com.example.demo.service.review.ReviewEditService;

import lombok.RequiredArgsConstructor;
 
@Controller
@RequiredArgsConstructor
public class ReviewEditController {
	
	private final ReviewEditService service;
	
	@PostMapping("/show-edit-form")
	public String mtShowEdit(@ModelAttribute MtEditReviewForm form) {
		return "admin/review/mt-edit-review";
	}
	
	/*--- 更新リクエスト（編集画面より） ---*/
	@PostMapping("/mt-edit-review")
	public String mtEdit(
		@Validated @ModelAttribute MtEditReviewForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "admin/review/mt-edit-review";			// 入力がエラーの場合
		}

		return "admin/review/mt-confirm-edit-review";		// 入力が正常の場合
	}
	/*--- （編集確認画面からの戻り） ---*/
	@PostMapping("/show-edit-form-ret")
	public String backReview(@ModelAttribute MtEditReviewForm form) {
		//自動的にmodel.addAttribute("reviewRegistForm", new ReviewRegistForm());
		return "admin/review/mt-edit-review";
	}
	
	/*--- 更新リクエスト（編集最後の確認画面より） ---*/
	@PostMapping("/mt-confirm-edit-review")
	public String mtConfirmEdit(
			@Validated @ModelAttribute MtEditReviewForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "admin/review/mt-edit-review";		// 入力がエラーの場合
		}

		Review mer = new Review();
		mer.setReviewId(form.getReviewId());	
		mer.setManufacturerId(form.getManufacturerId());
		mer.setModelName(form.getModelName());
		mer.setShoeSize(form.getShoeSize());
		mer.setFootLength(form.getFootLength());
		mer.setFootWidth(form.getFootWidth());
		mer.setInstepHeight(form.getInstepHeight());
		mer.setComment(form.getComment());

		service.edit(mer);
			
		redirectAttributes.addFlashAttribute("msg", "(更新)");
			
		return "redirect:/mt-complete";
	}
}
