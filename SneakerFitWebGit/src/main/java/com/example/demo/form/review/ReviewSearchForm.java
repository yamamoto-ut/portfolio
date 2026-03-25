package com.example.demo.form.review;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ReviewSearchForm {
	//メーカーIDからレビューを探す
	
	//メーカーID
	private Integer manufacturerId;
	// メーカー名
	private String manufacturerName;
	
	
	/*検索で使う*/
	@Size(max = 32, message = "32文字以内で入力してください")
	private String modelName;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "0より大きい値を入力してください")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、小数点第1位まで入力してください")
	private BigDecimal footLength;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "0より大きい値を入力してください")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、小数点第1位まで入力してください")
    private BigDecimal footWidth;     // 小数点第1位まで
	
	@DecimalMin(value = "0.0", inclusive = false, message = "0より大きい値を入力してください")
	@Digits(integer = 2, fraction = 1, message = "整数2桁、小数点第1位まで入力してください")
    private BigDecimal instepHeight;

}
