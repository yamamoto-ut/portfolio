package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//manufacturer
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
	
	//メーカーID
	private int manufacturerId;
	//メーカー名
	private String manufacturerName;
	//レビューの数をカウントする
	private int reviewCnt;
}
