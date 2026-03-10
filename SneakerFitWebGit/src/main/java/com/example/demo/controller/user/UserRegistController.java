package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.user.UserRegistForm;
import com.example.demo.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegistController {

	private final UserService service;
	
	//新規ユーザ登録画面を表示する
	@GetMapping("/user/show-user-regist-form")
	public String showUserRegistForm(@ModelAttribute UserRegistForm form) {
		return "user/user-regist";
	}

	// 新規ユーザ登録リクエストを処理する
	@PostMapping("/user/regist-user")
	public String registUser(
			@Validated @ModelAttribute UserRegistForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "user/user-regist";
		}
		
		return "user/user-confirm-regist";
	}

	//新規ユーザ登録の確認画面
	@PostMapping("/user/confirm-regist-user")
	public String confirmRegistUser(
			@Validated UserRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
			//入力値のエラーチェック
		if (result.hasErrors()) {
			return "user/user-regist";
		}
		//フォームの値をUserエンティティにコピーする
		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		
		//ユーザ登録処理を呼び出す
		service.registUser(user);
		
		redirectAttributes.addFlashAttribute("msg", "(ユーザ登録)");
		
		return "redirect:/complete";
	}

}
