package com.example.demo.controller.manufacturer;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Manufacturer;
import com.example.demo.form.manufacturer.SearchForm;
import com.example.demo.service.manufacturer.ManufacturerListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManufacturerListController {
	
	//フィールドにサービス層のオブジェクトをコンストラクタインジェクションでDIする
	//コンストラクタを定義したら＠アノテーションRequiredArgsConstructorで初期化をする
	private final ManufacturerListService service;
	
	
	//検索request
	@GetMapping("/manufacturer-search")
	private String manufacturerSearch(@ModelAttribute SearchForm form, Model model) {
		
		//部分一致の文字列はformで入力された値です。（引数のform）
		//-----------サービス層の処理を呼び出して、結果を取得する--------------
		List<Manufacturer> list = service.findByNameWildcard(form.getManufacturerName());
		
		if (list.size() > 0) {
			model.addAttribute("manufacturerList", list);
		}else {
			model.addAttribute("msg", "該当するメーカーはありませんでした。");
		}
		return "index";
	}
	
	//管理者からの検索request <-----ほぼ同じですが、リダイレクト先が違います。
	
		@GetMapping("/admin/manufacturer-search")
		private String adminManufacturerSearch(@ModelAttribute SearchForm form, Model model) {
			
			List<Manufacturer> list = service.findByNameWildcard(form.getManufacturerName());
			
			if (list.size() > 0) {
				model.addAttribute("manufacturerList", list);
			}else {
				model.addAttribute("msg", "該当するメーカーはありませんでした。");
			}
			//管理者用のリスト表示を返す
			return "admin/manufacturer/mt-manufacturer-List";
		}
	
	
	
}
