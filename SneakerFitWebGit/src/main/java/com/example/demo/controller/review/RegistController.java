package com.example.demo.controller.review;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.form.review.ReviewRegistForm;
import com.example.demo.service.review.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {
	
	private final RegistService service;
	//@RequiredArgsConstructorでRegistService serviceでコンストラクタを初期化
	//service というフィールドに適したオブジェクトがDIされる
	
	
	/*--- レビュー登録画面表示リクエスト ---*/
	@GetMapping("/show-review-form")
	public String showReviews( @ModelAttribute ReviewRegistForm form, Model model) {
		model.addAttribute("manufacturerList", service.findAllManufacturereName());
		return "review/regist-review";
	}
	
	//確認画面からの戻るボタンを押したとき
	@PostMapping("/back-review-form")
	public String backReview(@ModelAttribute ReviewRegistForm form, Model model) {
		model.addAttribute("manufacturerList", service.findAllManufacturereName());
		return "review/regist-review";
	}
	
	// (レビューをPOSTしたとき、@Validatedリクエストパラメータをformオブジェクトに設定するときに
	//Validationをおこなって、その結果をModelに格納 BindingResult resultでvalidationの結果を判定する
	@PostMapping("/regist-review")
	public String submitReview(@Validated @ModelAttribute ReviewRegistForm form, BindingResult result, Model model) {
		//自動的Model //reviewRegistForm （先頭小文字になる）クラスのフィールドの値が束ねられる
		if (result.hasErrors()) {
			//errorの場合,登録画面に戻す
			 model.addAttribute("manufacturerList", service.findAllManufacturereName());
			return "review/regist-review";
		}
		/*----------------追加分------------------------*/
		// DBからメーカー名を取得
	    String name = service.findManufacturerName(form.getManufacturerId());
	    // form にセット
	    form.setManufacturerName(name);
	 
	    
		//正常な入力の場合、登録実行画面へ遷移
		return "review/confirm-regist-review";

	}
	
	/*登録確認画面より登録実行ボタンを押したとき*/
	@PostMapping("/confirm-regist-review")
	public String confirmRegistReview(@Validated ReviewRegistForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		//RedirectAttributesオブジェクト redirectAttributes
		
		/*入力エラー処理*/
		if (result.hasErrors()) {
			//errorの場合,登録画面に戻す
			return "regist-review";
		}
		/*ここまで入力エラー処理*/
		
		//Authentication ログイン中ユーザーの身分証明書オブジェクト
		//ログイン中ユーザーの認証情報（ユーザーID・権限など）を保持するオブジェクトです。
		//Spring Securityでは、ユーザー情報をSecurityContextに保存されている。
		//保存場所がSecurityContextHolder
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String userId = auth.getName();
		    //ログイン中ユーザーを取得
		    
		//ReviewRegistForm型の formで入力されたデータを受け取っている,
		Review r = new Review();
		//データ受け渡し用オブジェクト[Review.java]
		//formからのデータをDTOに詰め替えている
		r.setUsername(userId); 
		r.setManufacturerId(form.getManufacturerId());
		r.setModelName(form.getModelName());
		r.setShoeSize(form.getShoeSize());
		r.setFootLength(form.getFootLength());
		r.setFootWidth(form.getFootWidth());
		r.setInstepHeight(form.getInstepHeight());
		r.setComment(form.getComment());
		//fieldに定義されたserviceのregistメソッドを使って登録処理を行っている
		service.regist(r);
		//登録処理を完了
		redirectAttributes.addFlashAttribute("msg", "レビュー登録");
		//フラッシュスコープにmsg属性名で値を設定
		
		return "redirect:/complete";
		//URLパターンをreturnする.→ CommonController.javaの@GetMapping("/complete")へいく
	}

}
