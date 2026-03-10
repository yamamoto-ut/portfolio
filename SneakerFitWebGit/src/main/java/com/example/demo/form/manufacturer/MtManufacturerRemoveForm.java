package com.example.demo.form.manufacturer;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MtManufacturerRemoveForm {
	//メーカー削除用
	//メーカーID
	@NotNull
	private Integer manufacturerId;
	//メーカー名
	private String manufacturerName;
}
