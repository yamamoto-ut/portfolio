package com.example.demo.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	/*-----管理者がADMINかUSERか選べる*/
	@Override
	@Transactional       
	public void registAdmin(User user) {
		//この引数（User user）には、入力時の平文のパスワードが入っている。
		//ハッシュ化してない用
//		String hashed = "{noop}" + user.getPassword();
		
		//passwordEncoderオブジェクトのencodeというメソッドにuserのパスワードをuser.getPassword()で
		//渡すことで、★{bcrypt方式}でハッシュ化されたパスワードを戻り値として得ることができる
		String hashed = passwordEncoder.encode(user.getPassword());
		
		// パスワードの再設定をしている
		user.setPassword(hashed);
		//repositoryオブジェクトにinsertメソッドでuserオブジェクトを渡している。ハッシュ化されてDBに渡される
		repository.insert(user);
	}
	
	/*一般ユーザー登録用(ROLE_USER)*/
	@Override
    @Transactional
    public void registUser(User user) {
        user.setRole("ROLE_USER"); // 強制
        //ハッシュ化なしバージョン
//        String hashed = "{noop}" + user.getPassword();
        //★正式{bcrypt方式}でハッシュ化
        String hashed = passwordEncoder.encode(user.getPassword());
		
		// パスワードの再設定
		user.setPassword(hashed);
        repository.insert(user);
    }
	/*一般ユーザー登録用*/
}
