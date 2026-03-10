package com.example.demo.form.review;

import lombok.Data;

@Data
public class ReviewSearchForm {
	//メーカーIDからレビューを探す
	
	//メーカーID
	private Integer manufacturerId;
	// メーカー名
	private String manufacturerName;

}
