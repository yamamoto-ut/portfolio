package com.example.demo.controller.manufacturer;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Manufacturer;
import com.example.demo.form.manufacturer.MtRegistForm;
import com.example.demo.service.manufacturer.MtRegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManufacturerRegistController {
	
	private final MtRegistService service;
	
	//メーカーの登録画面
	@PostMapping("/manufacturer-show-regist")
	public String mtShowRegist(@ModelAttribute MtRegistForm form) {
		return "admin/manufacturer/mt-manufacturer-regist";
	}

	//メーカー登録リクエスト
	@PostMapping("admin/mt-regist")
	public String mtRegist(@Validated @ModelAttribute MtRegistForm form,
		BindingResult result ) {
		//Validationをおこなって、その結果をModelに格納 BindingResult resultでvalidationの結果を判定する
		if (result.hasErrors()) {
			return "admin/manufacturer/mt-manufacturer-regist";			// 入力がエラーの場合
		}

		return "admin/manufacturer/mt-confirm-regist";		// 入力が正常の場合次の画面へ遷移
	}

	//メーカーの登録
	@PostMapping("/mt-confirm-regist")
	public String mtConfirmRegist(
			@Validated @ModelAttribute MtRegistForm form,
			BindingResult result,                     //Validationをおこなって、その結果をModelに格納 BindingResult resultでvalidationの結果を判定する
			RedirectAttributes redirectAttributes ) {
		
		if (result.hasErrors()) {
			return "admin/manufacturer/mt-manufacturer-regist";			// 入力がエラーの場合
		}

		Manufacturer m = new Manufacturer();
		m.setManufacturerName(form.getManufacturerName());
		//メーカー登録処理を呼び出す
		service.regist(m);
		//メーカー登録完了のメッセージをリダイレクト先に渡す
		redirectAttributes.addFlashAttribute("msg", "(メーカー名登録)");
			
		return "redirect:/mt-complete";
	}


}
