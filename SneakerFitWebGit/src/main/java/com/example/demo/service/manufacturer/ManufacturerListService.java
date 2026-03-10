package com.example.demo.service.manufacturer;

import java.util.List;

import com.example.demo.entity.Manufacturer;


public interface ManufacturerListService {
	//メーカーの部分一致検索、抽象メソッドで型を定義
	//引数はメーカー名、返り値はメーカーのリスト
	List<Manufacturer> findByNameWildcard(String manufacturerName);
}
