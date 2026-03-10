package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	//アクセスエラーの場合、業務処理はPOSTの場合、とGETの場合、があるので、@RequestMapping を使用する。
	//ハンドラーメソッド
	
	//アクセスエラーの場合,アクセスエラー画面を表示する
	@RequestMapping("/access-error")
	public String showAccessError() {
		return "access-error";
	}
	
	//ログイン画面を表示する
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	/*ログインでエラーが起こった場合*/
	@GetMapping("/login-error")
	public String showLoginFail(Model model) {
		//
		model.addAttribute("message", "IDまたはパスワードが違います");
		
		return "login";
	}
}
