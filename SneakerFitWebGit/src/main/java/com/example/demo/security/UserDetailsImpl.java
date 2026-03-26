package com.example.demo.security;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;

/*Spring Security で準備してある "UserDetails" インターフェース
Spring Securityはログイン処理のとき、ユーザー名, パスワード, 権限（ROLE）を
UserDetails 型のオブジェクトとして扱う仕様がある。*/


//継承先クラスでオーバーライド必須 項目が３つ がある。private final String username, private final String password,
//private final Collection<?extends GrantedAuthority> authorities;

//必要に応じてオーバーライドするメソッドがあと４つある。アカウントの管理の仕方については、十分な検討が必要かも
//拡張性が多い
//独自の追加フィールドなども、検討できる。


public class UserDetailsImpl implements UserDetails {
	
	//フィールド
	private final String username;
	private final String password;
	private final Collection<?extends GrantedAuthority> authorities;
	
	private final boolean deleted;
	//GrantedAuthorityを継承したクラスをジェネリクスした、コレクション型、 <----よくわからない。勉強不足
	//権限（役割）を複数持つように設定している   
	//GrantedAuthorityは権限を表すオブジェクト USERかADMINか
	
	
	//コンストラクタここでimport するUserは、EntityにあるUserなので注意。Spring Securityのインポートではない
	//コンストラクタの引数はパッケージentityのUserに合わせる
	public UserDetailsImpl(User user) {
		username = user.getUserId();
		password = user.getPassword();
		authorities = AuthorityUtils.createAuthorityList(user.getRole()); 
		//DBから得たroleを渡して目的の型の結果をわたす。
		deleted = user.isDeleted();
	}
	
	
	 // 追加：アカウントが有効かどうかをSpring Securityに伝える
    @Override
    public boolean isEnabled() {
        return !deleted; // is_deleted=trueならログイン不可
    }
	
	
	//ユーザーの権限を返すメソッド
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	
	@Override 
	public @Nullable String getPassword() {
		//ユーザーのパスワードを返すメソッド
		return password;
	}

	@Override
	public String getUsername() {
		//ユーザーのユーザー名を返すメソッド
		return username;
	}

}

//Spring Security はログイン処理のとき、必ず UserDetails 型 のオブジェクトを扱う。
//エンティティUser を、Spring Security が理解できる形に変換するためのクラスを作る必要がある。
