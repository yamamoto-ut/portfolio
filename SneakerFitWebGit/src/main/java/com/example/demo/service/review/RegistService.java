package com.example.demo.service.review;

import com.example.demo.entity.Review;

public interface RegistService {
//	抽象メソッド、業務処理として登録処理を行う
	void regist(Review review);
	
	//メーカーid から、メーカー名を取得するための追加分
	String findManufacturerName(Integer manufacturerId);
}
