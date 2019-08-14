package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.ChildCategory;
import com.example.domain.GrandChildCategory;
import com.example.domain.Item;
import com.example.domain.ParentCategory;

@Repository
@Transactional
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> itemRowMapper = (rs,i) -> {
		Item item = new Item();
		item.setItemName(rs.getString("name"));
		item.setItemPrice(rs.getInt("price"));
		item.setCategory(rs.getString("name_all"));
		item.setBrand(rs.getString("brand_name"));
		item.setConditionId(rs.getInt("item_condition_id"));
		return item;
		
	};		
//		categoryごと		
		private static final RowMapper<ParentCategory> parent_category_rowMapper = (rs,i) -> {
		ParentCategory pc = new ParentCategory();	
		pc.setParentId(rs.getInt("id"));
		pc.setParentCategory(rs.getString("parentCategory"));
		return pc;
		
		};
		
		private static final RowMapper<ChildCategory> child_category_rowMapper = (rs,i) -> {
		ChildCategory cc = new ChildCategory();
		cc.setChildId(rs.getInt("id"));
		cc.setChildCategory(rs.getString("childCategory"));
		cc.setParentId(rs.getInt("parent_id"));
		return cc;
		
		};
		
		private static final RowMapper<GrandChildCategory> grandChild_category_rowMapper = (rs,i) -> {
		GrandChildCategory gc = new GrandChildCategory();
		gc.setGrandChildid(rs.getInt("id"));
		gc.setGrandChildCategory(rs.getString("grandChildCategory"));
		gc.setParentId(rs.getInt("parent_id"));
		gc.setNameAll(rs.getString("name_all"));
		return gc;
	};
	
//	items&categoryテーブルを表示(join)----List.jsp
	public List<Item> findAllItems(){
		
		String sql = "SELECT name,price,brand_name,item_condition_id,name_all from items join category on items.id=category.id "
				+ "where name_all is not null";
		List<Item> itemList = template.query(sql, itemRowMapper);		
		return itemList;
	}
	
//	search form-parentCategory
	public List<ParentCategory> findParentCategory(){
		String sql = "SELECT id , category_name as parentCategory from category where parent_id is null";

		List<ParentCategory> parentCategoryList = template.query(sql, parent_category_rowMapper);
		return parentCategoryList;
		
	}
	
//	search form-childCategory
	public List<ChildCategory> findChildCategory(){
//		String sql = "SELECT  id,category_name as childCategory,parent_id from category where parent_id = :parentId";
//		String sql = "SELECT DISTINCT split_part(category_name,'/',2) as childCategory from original "
//				+ "Where split_part(category_name,'/',2) is not null";
		String sql = "select DISTINCT ON (category_name) id,parent_id,category_name as childCategory from category where parent_id >0 and parent_id <11";
		
		List<ChildCategory> childCategoryList = template.query(sql, child_category_rowMapper);
		return childCategoryList;		
	}
	
//	parent_idを引数にして引っ張ってくるやつ
//	public ChildCategory findChildCategory(Integer parentId) {
//		String sql = "SELECT id,category_name as childCategory,parent_id from category where parent_id = :parentId";
//		SqlParameterSource param  =new MapSqlParameterSource().addValue("parent_id",parentId);
//		ChildCategory childCategory  = template.queryForObject(sql,param,child_category_rowMapper);
//		return childCategory;
//	}
	
	
	
//	search form-grandChildCategory
	public List<GrandChildCategory> findGrandChildCategory(){
//		String sql = "SELECT DISTINCT split_part(category_name,'/',3) as grandChildCategory from original "
//				+ "Where split_part(category_name,'/',3) is not null";
		String sql = "SELECT distinct on(category_name )id,category_name as grandChildCategory,parent_id,name_all from category where parent_id >10";
		List<GrandChildCategory> grandChildCategoryList = template.query(sql, grandChild_category_rowMapper);
		return grandChildCategoryList;
	}
	
//	add item originalテーブルに登録
//	public Item addItem(Item item) {
//		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
//		String insertSql = "INSERT INTO original (name,price,category_name,brand_name,item_condition_id,item_description) "
//				+ "VALUES(:name,:price,:category_name,:brand_name,:item_condition_id,:item_description)";
//		template.update(insertSql, param);
//		return item;
//	}
	
	

}
