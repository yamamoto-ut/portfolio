package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Review;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor  //final 修飾子のついたコンストラクタを初期化
public class UserRepositoryImpl implements UserRepository {
	//
	private final JdbcTemplate jdbcTemplate;

	@Override   //insertメソッドが実際にユーザー登録を行うメソッド
	public void insert(User user) {

		String sql =
				" INSERT INTO s_user " +
				" (user_id, password, role) " +
				" VALUES (?, ?, ? ) ";	
		//SQL文の作成、s_userテーブルにuser_id, password, roleの３つの項目を追加するSQL文
		
		//jdbcTemplateのupdateメソッドを使って、SQL文を実行する。プレースホルダには、userオブジェクトからuserId, password, roleを取得して渡す
			jdbcTemplate.update(sql, user.getUserId(),
									 user.getPassword(), //この時は渡されたパスワードそのまま、
									 user.getRole()			);
		//passwordの加工などは、業務処理のところで行う
	}

	@Override
	public User selectByUserId(String userId) {
		// s_userからSELECT 3つの項目を受け取る。条件   主キーの項目user_id で ? と一致した１レコードを取得する
		String sql = " SELECT "
				+ " user_id, "
				+ " password, "
				+ " role, "
				+ " is_deleted "
				+ " FROM "
				+ " s_user "
				+ " WHERE "
				+ " user_id = ? ";
		
		//戻り値として、使用するUser型のuserを定義する
		User user;
		//sql から１レコードを取得する(sql, userId)プレースホルダには、userIdがはいる
		try {
			Map<String, Object> one 
				= jdbcTemplate.queryForMap(sql, userId);
			//jdbc のqueryForMapメソッドでSQLの１レコードを取得、プレースホルダには、selectByUserId(String userId)
			//で受け取っている引数userIdを入れる
			user = new User();
			//userの中身を詰め込んでいる
			//DBから取得してoneというobjectはMapの形式になっているので、getメソッドで取得
			//オブジェクト型なので、（String型）にキャスト、
			user.setUserId((String)one.get("user_id"));    //userオブジェクトのuserIdにsetterを使って設定
			user.setPassword((String)one.get("password"));  //DBから取得した値をuserオブジェクトに設定
			user.setRole((String)one.get("role"));
			
			
			Object deletedVal = one.get("is_deleted");
			boolean isDeleted = false;
			if (deletedVal instanceof Boolean) {
			    isDeleted = (Boolean) deletedVal;
			} else if (deletedVal instanceof Number) {
			    isDeleted = ((Number) deletedVal).intValue() == 1;
			}
			user.setDeleted(isDeleted);
			 
			
		}catch (EmptyResultDataAccessException e) {
			//対象データが存在しない場合nullを設定
			user = null;
	//もし指定したIDのユーザーがいなかった場合、エラーにならずに nullを返す
		}
				
		//最終的な戻り値
		return user;
	}
	
	//論理削除の実装、ユーザIDをもとに、s_userテーブルのis_deletedをtrueにする
	@Override
	public void updateIsDeleted(String userId) {
	    String sql = "UPDATE s_user SET is_deleted = TRUE WHERE user_id = ?";
	    jdbcTemplate.update(sql, userId);
	}
	
	//ユーザIDをもとに、レビューの一覧を取得する
	@Override
	public List<Review> selectReviewsByUserId(String userId) {
	    String sql = "SELECT "
	            + "r.review_id, "
	            + "r.user_id, "
	            + "u.user_id AS username, "
	            + "r.manufacturer_id, "
	            + "m.manufacturer_name, "
	            + "r.model_name, "
	            + "r.shoe_size, "
	            + "r.foot_length, "
	            + "r.foot_width, "
	            + "r.instep_height, "
	            + "r.comment "
	            + "FROM foot_review r "
	            + "JOIN m_sneaker m ON r.manufacturer_id = m.manufacturer_id "
	            + "JOIN s_user u ON r.user_id = u.user_id "
	            + "WHERE r.user_id = ? "
	            + "ORDER BY r.review_id";

	    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, userId);
	    List<Review> result = new ArrayList<>();
	    for (Map<String, Object> row : list) {
	        Review review = new Review();
	        review.setReviewId((int) row.get("review_id"));
	        review.setUsername((String) row.get("username"));
	        review.setManufacturerId((int) row.get("manufacturer_id"));
	        review.setManufacturerName((String) row.get("manufacturer_name"));
	        review.setModelName((String) row.get("model_name"));
	        review.setShoeSize((BigDecimal) row.get("shoe_size"));
	        review.setFootLength((BigDecimal) row.get("foot_length"));
	        review.setFootWidth((BigDecimal) row.get("foot_width"));
	        review.setInstepHeight((BigDecimal) row.get("instep_height"));
	        review.setComment((String) row.get("comment"));
	        result.add(review);
	    }
	    return result;
	}

}
