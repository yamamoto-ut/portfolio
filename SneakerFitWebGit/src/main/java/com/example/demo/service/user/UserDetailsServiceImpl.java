package com.example.demo.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;


//このクラスをSpringのBeanに登録
//User関連のDBアクセスを行う
@Service
@RequiredArgsConstructor //finalのついたフィールドを初期化するコンストラクタが自動生成
public class UserDetailsServiceImpl implements UserDetailsService {
		
	private final UserRepository userRepository;
	
	//Spring Securityでは、ユーザIDにあたるものをusernameという変数で表現している
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// ログイン時にDBからユーザー情報を取ってくる。ログイン時にSpring Securityが自動で呼ぶ
		User user = userRepository.selectByUserId(username);
			//ユーザーIDを受け取る
		
		if (user == null ) {
			throw new UsernameNotFoundException("ユーザーが存在しない");
			//Spring Securityが自動でログイン失敗扱いにする
		}
		 
		return new UserDetailsImpl(user);
		//UserDetailsImplのインスタンス化, securityパッケージのクラス
		//引数でDBからuserオブジェクトを渡して、内部のフィールドを初期化する
		//UserDetailsImplクラスの各メソッドを呼び出せるようにしている
		
		//UserDetailsImplオブジェクトをloadUserByUsernameメソッドの戻り値としている
		
		//ログイン判定に使うユーザー情報は必ずUserDetails型で扱う
	}

}
