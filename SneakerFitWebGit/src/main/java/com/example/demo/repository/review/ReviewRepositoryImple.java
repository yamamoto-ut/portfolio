package com.example.demo.repository.review;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Manufacturer;
import com.example.demo.entity.Review;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImple implements ReviewRepository {
	//jdbcTemplateの値がDIされる。初期化
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(Review review) {

		String sql = "INSERT INTO foot_review"
				+ "(user_id, manufacturer_id, model_name, shoe_size, foot_length, foot_width, instep_height, comment )"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		//プレースホルダ
		//foot_review テーブルにレコードを追加。
		jdbcTemplate.update(sql, 
				
				review.getUsername(),        // user_id
				review.getManufacturerId(),
				review.getModelName(),
				review.getShoeSize(),
				review.getFootLength(),
				review.getFootWidth(),
				review.getInstepHeight(),
				review.getComment());

	}

	@Override
	public List<Review> selectByManufacturerId(int manufacturerId) {
		
		String sql = "SELECT "
					+ "r.review_id, "
					+ "r.manufacturer_id, "
					+ "m.manufacturer_name, "
					+ "r.user_id, "
					+ "u.user_id AS username, "
					+ "r.model_name, "
					+ "r.shoe_size, "
					+ "r.foot_length, "
					+ "r.foot_width, "
					+ "r.instep_height, "
					+ "r.comment "
				+ "FROM foot_review r "  // foot_reviewテーブルをrとして指定
				+ "JOIN m_sneaker m ON r.manufacturer_id = m.manufacturer_id " // m_sneakerテーブルをmとして指定、foot_reviewテーブルのmanufacturer_idとm_sneakerテーブルのmanufacturer_idを結合
				+ "JOIN s_user u ON r.user_id = u.user_id " // s_userテーブルをuとして指定、foot_reviewテーブルのuser_idとs_userテーブルのuser_idを結合
				+ "WHERE r.manufacturer_id = ? " // WHERE句で、引数で受け取ったmanufacturerIdに一致するレコードを検索
				+ "ORDER BY r.review_id"; // review_idの昇順で結果を並べ替える

		//SQL で検索（プレースホルダは引数で受け取ったmanufacturerId）
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, manufacturerId);

		//検索結果をReviewオブジェクトのリストに変換して返す
		List<Review> result = new ArrayList<Review>();
		for (Map<String, Object> i : list) {
			Review review = new Review();
			
			review.setReviewId((int) i.get("review_id"));
			review.setUsername((String) i.get("username"));
			review.setManufacturerId((int) i.get("manufacturer_id"));
			review.setManufacturerName((String) i.get("manufacturer_name"));
			review.setModelName((String) i.get("model_name"));
			review.setShoeSize((BigDecimal) i.get("shoe_size"));
			review.setFootLength((BigDecimal) i.get("foot_length"));
			review.setFootWidth((BigDecimal) i.get("foot_width"));
			review.setInstepHeight((BigDecimal) i.get("instep_height"));
			review.setComment((String) i.get("comment"));

			result.add(review);
		}

		return result;
	}
	//レビューの編集処理
	@Override
	public void update(Review review) {
		
		String sql = "UPDATE "
				+ "foot_review "
				+ "SET "
				+ "model_name = ?, "
				+ "shoe_size = ?,  "
				+ "foot_length = ?, "
				+ "foot_width = ?, "
				+ "instep_height = ?, "
				+ "comment = ? "
				+ "WHERE "
				+ "review_id = ? "; 
		
		jdbcTemplate.update(sql, 
				
				review.getModelName(),
				review.getShoeSize(),
				review.getFootLength(),
				review.getFootWidth(),
				review.getInstepHeight(),
				review.getComment(),
				review.getReviewId()
				);
	}

	
	@Override
	public void delete(Review review) {
		//DELETE文、 WHERE 句で削除対象レコードの指定 
		
		String sql = "DELETE "
				+ "FROM "
				+ "foot_review "
				+ "WHERE "
				+ "review_id = ?";
				
				jdbcTemplate.update(sql,  review.getReviewId());
				//DIして使えるようになっているjdbcTemplateオブジェクトのupdate()methodを使用してSQLのDELETE文を実行する
				
	}

	@Override
	public String findNameById(Integer manufacturerId) {
		/*メーカーIDから名前表示をするための*/
	        String sql = " SELECT "
	        		+ " manufacturer_name "
	        		+ " FROM "
	        		+ "m_sneaker "
	        		+ " WHERE "
	        		+ "manufacturer_id = ?" ;
	            
	        return jdbcTemplate.queryForObject(sql, String.class, manufacturerId);
	    //jdbcTemplateのqueryForObject() methodを使用してSQLのSELECT文を実行する。引数は、SQL文、戻り値の型、プレースホルダに渡す値	
	}
	
	
	//メーカー名の一覧を取得するための追加分
	@Override
	public List<Manufacturer> findAllManufacturereName() {
		 String sql = "SELECT manufacturer_id, manufacturer_name FROM m_sneaker";
		 //SQL文を定義する。m_sneakerテーブルからmanufacturer_idとmanufacturer_nameを選択するSELECT文
		 List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		 //jdbcTemplateのqueryForList() methodを使用してSQLのSELECT文を実行する。戻り値は、List<Map<String, Object>>型で、各Mapは1行分のデータを表す。Mapのキーは列名、値は列の値となる。
		 List<Manufacturer> result = new ArrayList<Manufacturer>();
		 
		 for (Map<String, Object> i : list) {
			 Manufacturer manufacturer = new Manufacturer();
			 manufacturer.setManufacturerId((int) i.get("manufacturer_id"));
			 manufacturer.setManufacturerName((String) i.get("manufacturer_name"));
			 result.add(manufacturer);
		 }
		 
		 return result;
		 
		 
	}

}
