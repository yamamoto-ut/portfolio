package com.example.demo.form.user;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistForm {
	
	@Size(min=1, max=16, message="1文字から16文字で指定してください。")
	private String userId; //ユーザーID
	
	@Size(min=1, message="入力してください。")
	private String password; //パスワード
	
	//一般ユーザー登録では role を聞かれず、自動的に "ROLE_USER" が入る
	private String role;
    
}
