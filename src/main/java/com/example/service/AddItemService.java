package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.AddItem;
import com.example.repository.AddItemRepository;

@Service
public class AddItemService {
	
	@Autowired
	private AddItemRepository addItemRepository;

	public List<AddItem> findOriginal(){
		return addItemRepository.findOriginal();
	}
	
	public AddItem addItem(AddItem addItem) {
		return addItemRepository.addItem(addItem);
		
	}
}
