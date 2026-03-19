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
		//ユーザ登録の前に、同じuser_idがすでに存在するかどうかをチェックすることが重要です。
		// 重複チェック：同じuser_idがすでに存在する場合は例外を投げる
	    User existing = repository.selectByUserId(user.getUserId()); // user_idでユーザを検索
	    if (existing != null) {
	        throw new IllegalArgumentException("このユーザIDはすでに使われています: " + user.getUserId());// 例外を投げることで、呼び出し元でエラーハンドリングが可能になる
	    }
	    //-------------------------------------------------------------------------------------------------
        user.setRole("ROLE_USER"); //ユーザのロールをROLE_USERに設定
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
// 処理の流れ
//
//フォーム送信
//    ↓
//BindingResult でバリデーション（文字数チェック等）
//    ↓ エラーあり → 登録画面に戻る
//    ↓ エラーなし
//selectByUserId() で重複チェック
//    ↓ 重複あり → IllegalArgumentException → 登録画面にエラー表示
//    ↓ 重複なし
//insert() で登録 → 完了画面へ



	