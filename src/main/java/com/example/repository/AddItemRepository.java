package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AddItem;

@Repository
@Transactional
public class AddItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper <AddItem> addItem_row_mapper = (rs,i) -> {
		AddItem ai = new AddItem();
		ai.setItemName(rs.getString("name"));
		ai.setPrice(rs.getInt("price"));
//		ai.setParentCategory(rs.getString("category_name"));
//		ai.setChildCategory(rs.getString("category_name"));
//		ai.setGrandChildCategory(rs.getString("category_name"));
		ai.setBrandName(rs.getString("brand_name"));
		ai.setConditionId(rs.getInt("item_condition_id"));
		ai.setDescription(rs.getString("item_description"));
		return ai;
	};
	
	
//	original table　全件取得
	public List <AddItem> findOriginal(){
		
		String sql = "SELECT name,price,brand_name,item_condition_id,item_description from testOriginal";
		List <AddItem> addItemList = template.query(sql, addItem_row_mapper);
		return addItemList;
	}
	
	
//	add item originalテーブルに登録
	public AddItem addItem(AddItem addItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(addItem);
//		String insertSql = "INSERT INTO original (name,price,category_name,brand_name,item_condition_id,item_description) "
//				+ "VALUES(:name,:price,:name_all,:brand_name,:item_condition_id,:item_description)";
		
		String insertSql = "INSERT INTO testOriginal (name,item_condition_id,brand_name,price,item_description) "
				+ "VALUES (:itemName, :conditionId, :brandName, :price, :description)";
		template.update(insertSql, param);
		return addItem;
	}
	
	
	
}
