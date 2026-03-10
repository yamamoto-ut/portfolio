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
public class AdminUserRegistController {

	private final UserService service;
	
	//ユーザ登録画面表示 
	@GetMapping("/admin/show-user-regist-form")
	public String showUserRegistForm(@ModelAttribute UserRegistForm form) {
		return "admin/user/user-regist";
	}

	//ユーザ登録リクエスト
	@PostMapping("/admin/regist-user")
	public String registUser(
			@Validated @ModelAttribute UserRegistForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "admin/user/user-regist"; //入力エラーの場合
		}
		
		return "admin/user/user-confirm-regist";
	}

	//ユーザ登録リクエスト（登録確認画面より）
	@PostMapping("/admin/confirm-regist-user")
	public String confirmRegistUser(
			@Validated UserRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/user-regist";
		}
		//ユーザ登録処理を呼び出す
		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setRole(form.getRole());
		
		service.registAdmin(user);
		
		redirectAttributes.addFlashAttribute("msg", "(ユーザ登録)");
		
		return "redirect:/mt-complete";
	}

}
