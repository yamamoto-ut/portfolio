package com.example.demo.service.review;

import java.util.List;

import com.example.demo.entity.Manufacturer;
import com.example.demo.entity.Review;

public interface RegistService {
	//DBからメーカー名のリストを取得するための抽象メソッド
	List<Manufacturer> findAllManufacturereName();
	
//	抽象メソッド、業務処理として登録処理を行う
	void regist(Review review);
	
	//メーカーid から、メーカー名を取得するための追加分
	String findManufacturerName(Integer manufacturerId);
}
