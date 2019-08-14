package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ChildCategory;
import com.example.domain.GrandChildCategory;
import com.example.domain.Item;
import com.example.domain.ItemDetail;
import com.example.domain.ParentCategory;
import com.example.service.ItemDetailService;
import com.example.service.ItemService;

@Controller
@RequestMapping(value="/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDetailService itemDetailService;
	
//	list.jspへ全件表示
	@RequestMapping(value="/list")
	public String index(Model model) {
		List<Item> itemList = itemService.findAllItems();
		model.addAttribute("itemList",itemList);
		
//	search　Form　parentカテゴリーごとの表示
		List<ParentCategory> ParentCategoryList = itemService.findParentCategory();
		model.addAttribute("ParentCategoryList",ParentCategoryList);

		
//	search Form	childカテゴリーごとの表示
		List<ChildCategory> childCategoryList = itemService.findChildCategory();
		model.addAttribute("ChildCategoryList",childCategoryList);

//	search Form grandChildカテゴリーごとの表示
		List<GrandChildCategory> grandChildCategoryList = itemService.findGrandChildCategory();
		model.addAttribute("GrandChildCategoryList",grandChildCategoryList);
	
	return "list";
	}
	
//	detail表示	
	@RequestMapping(value="/detail/{itemName}")
	public String detail(Model model,@PathVariable String itemName) {
//		List <ItemDetail> itemDetailList = itemDetailService.findItemDetail();
//		model.addAttribute("itemDetailList",itemDetailList);
		
		ItemDetail itemDetail = itemDetailService.load(itemName);
		model.addAttribute("itemDetail",itemDetail);
		
		return "detail";
	}
	


}
