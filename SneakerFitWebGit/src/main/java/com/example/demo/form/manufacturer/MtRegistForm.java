package com.example.demo.form.manufacturer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class MtRegistForm {
	//メーカー登録フォーム
	@NotBlank(message="必須入力です")
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String manufacturerName;
}
