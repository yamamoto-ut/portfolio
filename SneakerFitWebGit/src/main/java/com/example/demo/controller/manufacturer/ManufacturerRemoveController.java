package com.example.demo.controller.manufacturer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Manufacturer;
import com.example.demo.form.manufacturer.MtManufacturerRemoveForm;
import com.example.demo.form.manufacturer.SearchForm;
import com.example.demo.service.manufacturer.MtRemoveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManufacturerRemoveController {

	private final MtRemoveService service;

	//メーカーの削除画面	
	@GetMapping("/manufacturer-remove-show-list")
	public String ShowManufacturer(@ModelAttribute SearchForm form, Model model) {
		return "admin/manufacturer/mt-manufacturer-List";
	}

	//メーカーの削除リクエスト
	@PostMapping("/mt-remove-manufacturer")
	public String mtRemoveManufacturer(
			@Validated @ModelAttribute MtManufacturerRemoveForm form, 
			BindingResult result, Model model) {
			//Validationをおこなって、その結果をModelに格納 BindingResult resultでvalidationの結果を判定する
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**confirmRemoveManufacturer()**"); // 入力がエラーの場合
		}
		model.addAttribute("mtManufacturerRemoveForm", form);
		
		return "admin/manufacturer/mt-confirm-remove-manufacturer"; // 入力が正常の場合
	}
	
	

	//メーカーの削除
	@PostMapping("/mt-confirm-remove-manufacturer")
	public String mtConfirmRemoveManufacturer(
			@Validated @ModelAttribute MtManufacturerRemoveForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
			
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**confirmRemoveManufacturer()**"); // 入力がエラーの場合
		}
			
			
		try {
			Manufacturer m = new Manufacturer();
			m.setManufacturerId(form.getManufacturerId());
			m.setManufacturerName(form.getManufacturerName());

			service.remove(m);

			redirectAttributes.addFlashAttribute("msg", "(メーカー 削除)");

			return "redirect:/mt-complete";
			
		}catch (IllegalStateException e) {
			//
			  redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
			  
		        return "redirect:/manufacturer-remove-show-list";
		}

		
	}

}
