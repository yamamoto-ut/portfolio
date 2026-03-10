package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//DBからユーザ情報を取得する
@Data    //アクセッサの自動定義
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String userId;
	private String password;
	
	//role 管理者か一般ユーザーか
	private String role;

}


//@AllArgsConstructor   フィールド全てに対応したコンストラクタ
//@NoArgsConstructor    引数なしのコンストラクタ
