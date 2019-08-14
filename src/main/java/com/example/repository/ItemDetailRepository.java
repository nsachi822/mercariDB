
package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.ItemDetail;

@Repository
@Transactional

public class ItemDetailRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper <ItemDetail> item_detail_rowMapper = (rs,i) -> {
		
		ItemDetail id = new ItemDetail();
		id.setDetailId(rs.getInt("detailId"));
		id.setItemName(rs.getString("name"));
		id.setPrice(rs.getInt("price"));
		id.setCategory(rs.getString("name_all"));
		id.setBrandName(rs.getString("brand_name"));
		id.setConditionId(rs.getInt("item_condition_id"));
		id.setDescription(rs.getString("description"));
		return id;
	};
	
	
//	detail全取得
	
//	public List <ItemDetail> findItemDetail(){
//		String sql = "SELECT row_number() over() as detailId,items.id,category_id, "
//				+ "name,price,brand_name,item_condition_id,name_all,description from items "
//				+ "join category on items.id=category.id where name_all is not null";
//		
//		List<ItemDetail> itemDetailList = template.query(sql, item_detail_rowMapper);
//		return itemDetailList;
//	}
	
//	nameを引数にやってみる
	public ItemDetail load(String itemName) {
		String sql = "SELECT row_number() over() as detailId,items.id,category_id, "
				+ "name,price,brand_name,item_condition_id,name_all,description from items "
				+ "join category on items.id=category.id where name_all is not null and name = :name";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name",itemName);
		ItemDetail itemDetail = template.queryForObject(sql,param,item_detail_rowMapper);
		return itemDetail;
	}
	
//	add item originalテーブルに登録
	public ItemDetail addItem(ItemDetail itemDetail) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(itemDetail);
//		String insertSql = "INSERT INTO original (name,price,category_name,brand_name,item_condition_id,item_description) "
//				+ "VALUES(:name,:price,:name_all,:brand_name,:item_condition_id,:item_description)";
		
		String insertSql = "INSERT INTO testOriginal (name,item_condition_id,brand_name,price,item_description) "
				+ "VALUES (:itemName, :conditionId, :brandName, :price, :description)";
		template.update(insertSql, param);
		return itemDetail;
	}
	
	
	
}
