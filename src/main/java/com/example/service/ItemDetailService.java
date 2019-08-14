package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.domain.ItemDetail;
import com.example.repository.ItemDetailRepository;

@Service
public class ItemDetailService {

	@Autowired
	private ItemDetailRepository itemDetailRepository;

	
//	public List<ItemDetail> findItemDetail(){
//		return itemDetailRepository.findItemDetail(); 
//	}
	
	public ItemDetail load(String itemName) {
		return itemDetailRepository.load(itemName);
	}
	
//	public ItemDetail addItem (ItemDetail itemDetail) {
//		return itemDetailRepository.addItem(itemDetail);		
//	}

}
