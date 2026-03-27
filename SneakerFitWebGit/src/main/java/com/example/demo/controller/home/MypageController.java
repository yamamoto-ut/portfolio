package com.example.demo.controller.home;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.service.user.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MypageService mypageService;

	// マイページ表示
	@GetMapping("/mypage")
	public String showMypage(Model model) {

		// ログイン中ユーザーのIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();

		// 自分のレビュー一覧取得
		List<Review> reviewList = mypageService.findReviewsByUserId(userId);

		model.addAttribute("userId", userId);
		model.addAttribute("reviewList", reviewList);

		return "user/mypage";
	}

	// アカウント削除
	@PostMapping("/delete-account")
	public String deleteAccount(HttpSession session, RedirectAttributes redirectAttributes) {

		// ログイン中ユーザーのIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();

		// 論理削除
		mypageService.deleteAccount(userId);

		// セッションを無効化（強制ログアウト）
		session.invalidate();
		// フラッシュ属性にメッセージを追加してリダイレクト
		redirectAttributes.addFlashAttribute("deleteAccountMsg", "アカウントを削除しました");
		return "redirect:/";
	}
}
