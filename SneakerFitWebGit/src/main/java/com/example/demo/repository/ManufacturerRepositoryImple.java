package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Manufacturer;

import lombok.RequiredArgsConstructor;

//インフラ層のデータアクセス部品
@Repository
@RequiredArgsConstructor
public class ManufacturerRepositoryImple implements ManufacturerRepository {

	//JdbcTemplateオブジェクト、 jdbctemplate DIする
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(Manufacturer m) {
		//メーカーの追加処理
		
		String sql = " INSERT INTO m_sneaker " +
				" (manufacturer_name) " +
				" VALUES (?) ";
		//メーカー名を条件にして、メーカーを追加するSQL文
		jdbcTemplate.update(sql,
				m.getManufacturerName());

	}
	
	//メーカーの全件検索処理
	@Override
	public List<Manufacturer> selectByNameWildcard(String manufacturerName) {
					//メーカーの部分一致検索処理
		String sql = "SELECT "
				+ "ms.manufacturer_id, "
				+ "ms.manufacturer_name, " //メーカーのIDと名前を取得する
				+ "COUNT(fr.review_id) AS rv_count "
				+ "FROM m_sneaker ms "  // m_sneakerテーブルをmsとして指定
				+ "LEFT OUTER JOIN foot_review fr " // foot_reviewテーブルをfrとして指定、LEFT OUTER JOINでm_sneakerテーブルとfoot_reviewテーブルを結合
				+ "ON ms.manufacturer_id = fr.manufacturer_id "// m_sneakerテーブルのmanufacturer_idとfoot_reviewテーブルのmanufacturer_idを結合
				+ "WHERE "
				+ "ms.manufacturer_name LIKE ? "		//メーカー名の部分一致検索、プレースホルダを使う	
				+ "GROUP BY "
				+ "  ms.manufacturer_id, "	//
				+ "  ms.manufacturer_name "
				+ "ORDER BY "
				+ "ms.manufacturer_id";
		
		String p = "%" + manufacturerName + "%"; //プレースホルダの値
		//SQLのLIKE句で部分一致検索をするために、検索文字列の前後に%をつける
		//SQLで検索（プレースホルダ : p）
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p);
		//Mapオブジェクトがメーカー件数分返される

		//Map型のlist を DTOとしてつかうManufacturer型のlistに変換す  る
		//値の取得から => 結果の格納
		List<Manufacturer> result = new ArrayList<Manufacturer>(); //結果の初期化
		//拡張forループ 
		for (Map<String, Object> i : list) {
			Manufacturer mf = new Manufacturer(); //一件分のメーカーをnew する
			mf.setManufacturerId((int) i.get("manufacturer_id"));
			//Mapのgetメソッドでキー名（属性名）manufacturer_idを指定して値を取得。=> setterを使ってManufacturerオブジェクトに設定					
			mf.setManufacturerName((String) i.get("manufacturer_name"));
			mf.setReviewCnt(((Long) i.get("rv_count")).intValue());
			result.add(mf);
		}
		return result;
		//結果のlistを戻り値としている

	}

	@Override
	public void update(Manufacturer manufacturer) {
		//メーカーの名前の編集処理
		String sql = "UPDATE "
				+ "m_sneaker "
				+ "SET "
				+ "manufacturer_name "
				+ "WHERE "
				+ "manufacturer_id = ?";
         //メーカーIDを条件にして、メーカーの名前を更新するSQL文
		jdbcTemplate.update(sql,
				manufacturer.getManufacturerName(), // SET
				manufacturer.getManufacturerId() // WHERE
		);

	}

	@Override
	public void delete(Manufacturer manufacturer) {
		//メーカーの削除処理
		String sql = "DELETE "
				+ "FROM "
				+ "m_sneaker "
				+ "WHERE "
				+ "manufacturer_id = ?";
			//メーカーIDを条件にして、メーカーを削除するSQL文
		jdbcTemplate.update(sql, manufacturer.getManufacturerId());
		//指定IDのメーカー1件を削除

	}
	
	//メーカーIDを条件にして、そのメーカーのレビュー件数を数える処理
	@Override
	public int countReviewsByManufacturerId(Integer manufacturerId) {
		String sql = "SELECT "
				+ " COUNT(*) " //COUNT(*)は、指定した条件に一致する行の数を数えるSQL関数	
				+ "FROM "
				+ "foot_review " 
				+ "WHERE manufacturer_id = ?"; //メーカーIDを条件にして、foot_reviewテーブルからレビューの数を数えるSQL文
		
		//そのメーカーのレビュー件数を数える
		return jdbcTemplate.queryForObject(sql,
				Integer.class, manufacturerId);
		//queryForObjectは、結果が1件だけ返るSELECTを実行するメソッド
		//Integer.class結果をどのJava型に変換するか,COUNT(*)は数値なので、Integer型にする
	}

}
