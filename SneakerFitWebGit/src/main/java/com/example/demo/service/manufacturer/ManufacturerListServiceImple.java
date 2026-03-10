package com.example.demo.service.manufacturer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManufacturerListServiceImple implements ManufacturerListService {
	
	//repositoryというフィールドに適したオブジェクトがDIされて使用できるようになる
	//repositoryは、データベースからメーカーの情報を取得するためのインターフェースで.
	private final ManufacturerRepository repository;
	
	@Override
	public List<Manufacturer> findByNameWildcard(String manufacturerName) {
		//repositoryのselectByNameWildcardメソッドを呼び出して、メーカー名に部分一致するメーカーのリストを取得	
		List<Manufacturer> list = repository.selectByNameWildcard(manufacturerName);
		 
		return list;
		
	}
	
}
