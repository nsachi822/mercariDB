package com.example.web;

public class AddItemForm {
	
	private String itemName;
	private Integer price;
	private String ParentCategory;
	private String ChildCategory;
	private String GrandChildCategory;
	private String brandName;
	private Integer conditionId;
	private String description;
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getParentCategory() {
		return ParentCategory;
	}
	public void setParentCategory(String parentCategory) {
		ParentCategory = parentCategory;
	}
	public String getChildCategory() {
		return ChildCategory;
	}
	public void setChildCategory(String childCategory) {
		ChildCategory = childCategory;
	}
	public String getGrandChildCategory() {
		return GrandChildCategory;
	}
	public void setGrandChildCategory(String grandChildCategory) {
		GrandChildCategory = grandChildCategory;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrand(String brandName) {
		this.brandName = brandName;
	}
	public Integer getConditionId() {
		return conditionId;
	}
	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
