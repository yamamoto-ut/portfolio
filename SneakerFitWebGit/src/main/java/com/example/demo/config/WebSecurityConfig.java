package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		//メモリ上に保存している idとパスワードを使う場合、（後にはDBから取得する）
//		//メソッドチェーン, .buildメソッドを最後に呼び出してユーザーオブジェクトを呼び出して aaaa に代入
//		UserDetails aaaa = User.withUsername("aaaa")
//				.password("{noop}aaaapass")
//				.roles("ADMIN")
//				.build();
//		
//		//Userオブジェクトの参照値を入れている
//		UserDetails bbbb = User.withUsername("bbbb")
//				.password("{noop}bbbbpass")
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(aaaa, bbbb);
//		
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//必要な設定,メソッドをを呼んでる。
		//requestの認可の設定、auth に対して処理を設定している。request対象は、URLパターン/adminという役割がアクセス可能
		//.anyRequest().authenticated()←   <=ここは認証が完了しているユーザーがアクセス可能
		http
			.authorizeHttpRequests(
					auth -> auth
						.requestMatchers("/", "/index").permitAll() //トップページを許可する
						.requestMatchers("/menu", "/user/show-user-regist-form", 
									"/user/regist-user", "/user/confirm-regist-user",
									"/complete", "/mt-complete").permitAll() //ユーザーの新規登録用の許可
						.requestMatchers("/manufacturer-search","/search-review").permitAll() //メーカー一覧表示,レビュー表示許可
						.requestMatchers("/login", "/login-error").permitAll() //ログイン画面も許可
						.requestMatchers("/css/**", "/images/**", "/js/**").permitAll()    //cssはログイン画面でも常に適用する。jsは一応入れている
						.requestMatchers("/admin/**").hasRole("ADMIN") //ADMINはadmin/からはじまる処理に行ける
						.anyRequest().authenticated()
					)
					.formLogin(
							form -> form
							.loginPage("/login")                 //ログイン画面を表示するURLパターン
							.failureUrl("/login-error")        //認証がNGの場合にリクエストをおくるURLパターン
							.defaultSuccessUrl("/", true)) //loginが完了したときにいく遷移先のURL
					
					.exceptionHandling(ex -> ex.accessDeniedPage("/access-error"));
					//例外時のオブジェクトex にエラー時の遷移先を設定している
		
		//HttpSecurity httpは必須オブジェクト、戻り値としてhttp.buildを返す。
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//PasswordEncoderFactoriesはpasswordEncoderを生成するオブジェクト.
		//戻り値をBean化
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
