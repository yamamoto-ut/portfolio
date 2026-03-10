package com.example.demo.service.manufacturer;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtRemoveServiceImpl implements MtRemoveService {
	
	private final ManufacturerRepository repository;
	
	@Override
	public void remove(Manufacturer manufacturer) {
		
		 int count = repository.countReviewsByManufacturerId(
		            manufacturer.getManufacturerId());
		 	//レビューが1件でもあれば削除しない
		    if (count > 0) {
		        throw new IllegalStateException("レビューが存在するため削除できません");
		    }
		
		repository.delete(manufacturer);

	}

}
