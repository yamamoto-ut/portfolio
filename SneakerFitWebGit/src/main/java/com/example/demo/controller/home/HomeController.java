package com.example.demo.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.manufacturer.SearchForm;

@Controller
public class HomeController {
	
	//トップページ
	@GetMapping("/")
    public String index(@ModelAttribute SearchForm form) {
        return "index";
    }
	
	//リダイレクト先を指定
	@GetMapping("/complete")
	private String complete() {
		return "complete";
	}
	
	//管理者用リダイレクト先 ＜－分ける必要があるのかわからないけど、念のため分けておく
	@GetMapping("/mt-complete")
	private String mtComplete() {
		return "admin/mt-complete";
	}
	
}
