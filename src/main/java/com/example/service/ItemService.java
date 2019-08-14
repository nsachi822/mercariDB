package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ChildCategory;
import com.example.domain.GrandChildCategory;
import com.example.domain.Item;
import com.example.domain.ParentCategory;
import com.example.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository ;
	
	public List<Item> findAllItems(){
		return repository.findAllItems();
	}
	
	public List<ParentCategory> findParentCategory(){
		return repository.findParentCategory();
	}
	
	public List<ChildCategory> findChildCategory(){
		return repository.findChildCategory();
	}
	
//	public ChildCategory findChildCategory(Integer parentId) {
//		return repository.findChildCategory(parentId);
//	}
	
	public List<GrandChildCategory> findGrandChildCategory(){
		return repository.findGrandChildCategory();
	}
	
}
