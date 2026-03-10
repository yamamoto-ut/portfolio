package com.example.demo.entity;

import java.math.BigDecimal;

import lombok.Data;
//lombok@Data,アクセッサとtoString();があるよ
@Data
public class Review {
	//データ受け渡し用オブジェクト[Review]DTO
	//受け渡しを行うデータ
	
	
	//reviewID
	private Integer reviewId;
	//ユーザーname
	private String username; 
	//メーカーID
	private Integer manufacturerId; 
	//メーカー名
	private String manufacturerName;  
	//シューズの名前
	private String modelName;
	//シューズサイズ
	private BigDecimal shoeSize;
	//足の長さ
	private BigDecimal footLength;
	//足幅
	private BigDecimal footWidth;
	//足の甲の高さ
	private BigDecimal instepHeight;
	//レビューコメント
	private String comment;

}
