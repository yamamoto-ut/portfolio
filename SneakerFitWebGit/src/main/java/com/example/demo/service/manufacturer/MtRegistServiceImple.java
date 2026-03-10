package com.example.demo.service.manufacturer;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtRegistServiceImple implements  MtRegistService{
	
private final ManufacturerRepository repository;
	
	@Override
	public void regist(Manufacturer m) {

		repository.add(m);
		
	}
}
