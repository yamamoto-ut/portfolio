package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Manufacturer;

public interface ManufacturerRepository {
	//メーカーの追加処理
	void add(Manufacturer m);
	//抽象メソッドで部分一致検索のメソッドの型を作る
	List<Manufacturer> selectByNameWildcard(String manufacturerName);
	
	//メーカーの名前の編集処理だが、今のところ、使わない。deleteメソッドを使うために配置
	void update(Manufacturer manufacturer);
	//メーカーの削除処理
	void delete(Manufacturer manufacturer); 
	
	//レビューの数をカウントする
	int countReviewsByManufacturerId(Integer manufacturerId);
	
	

}
