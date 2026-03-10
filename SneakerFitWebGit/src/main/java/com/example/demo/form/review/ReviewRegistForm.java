package com.example.demo.form.review;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ReviewRegistForm {
	//@アノテーションを使ってバリデーションルールを指定する。ハンドラーメソッドに@Validated をつける。
	//BindingResult result とresult.hasErrors()メソッドを使ってエラーチェックする
	//@Digitsは整数部と小数部の桁数を指定する
	//メーカー名ID
	@NotNull(message = "選択してください")
	private Integer manufacturerId;
	//メーカー名name
	private String manufacturerName;
	//シューズサイズ
	@NotNull(message = "入力してください。")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、または小数点第1位まで入力してください")
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal shoeSize;
	
	//シューズの名前
	@NotBlank(message = "入力してください。")
	@Size(max=32, message="1文字から32文字で指定してください。")
	private String modelName;

	//足の長さ
	@NotNull(message = "入力してください。")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、または小数点第1位まで入力してください")
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal footLength;
	
	//足幅
	@NotNull(message = "入力してください。")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、または小数点第1位まで入力してください")
	@DecimalMin(value = "0.0", inclusive = false)//0.0より大きい値を指定する
	private BigDecimal footWidth;
	
	//足の甲の高さ
	@NotNull(message = "入力してください。")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、または小数点第1位まで入力してください")
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal instepHeight;
	
	//レビューコメント
	@Size(max = 300, message = "1文字から300文字で入力してください。")
	private String comment;
}
