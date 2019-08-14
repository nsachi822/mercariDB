package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.AddItem;
import com.example.domain.ChildCategory;
import com.example.domain.GrandChildCategory;
import com.example.domain.ParentCategory;
import com.example.service.AddItemService;
import com.example.service.ItemDetailService;
import com.example.service.ItemService;

@Controller
@RequestMapping(value="/")
public class AddItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDetailService itemDetailService;
	
	@Autowired
	private AddItemService addItemService;
	
	@ModelAttribute
	public AddItemForm setUpForm() {
		return new AddItemForm();
	}
	
	
//	add表示
	@RequestMapping(value="/addform")
	public String addItem(Model model) {
		
//		add　Category　parentカテゴリーごとの表示
			List<ParentCategory> ParentCategoryList = itemService.findParentCategory();
			model.addAttribute("ParentCategoryList",ParentCategoryList);			

			//		add Category	childカテゴリーごとの表示
			List<ChildCategory> childCategoryList = itemService.findChildCategory();
			model.addAttribute("ChildCategoryList",childCategoryList);

//		add Category grandChildカテゴリーごとの表示
			List<GrandChildCategory> grandChildCategoryList = itemService.findGrandChildCategory();
			model.addAttribute("GrandChildCategoryList",grandChildCategoryList);
		
			
		return "add";
	}

	@RequestMapping (value= "/completeadd")
	public String compadd(AddItemForm form,RedirectAttributes redirectAttributes) {
		
		String itemName = form.getItemName();
		Integer conditionId = form.getConditionId();
		String brandName = form.getBrandName();
		Integer price = form.getPrice();
		String description = form.getDescription();
		System.out.println(description);
		System.out.println(itemName);
		
		AddItem addItem = new AddItem();	
		addItem.setItemName(itemName);
		addItem.setConditionId(conditionId);
		addItem.setBrandName(brandName);
		addItem.setPrice(price);
		addItem.setDescription(description);
		System.out.println(description);
		
//		BeanUtils.copyProperties(form, itemDetail);
		List <AddItem> addItemList = addItemService.findOriginal();
		addItemService.addItem(addItem);
		System.out.println(description);
		System.out.println(itemName);
		redirectAttributes.addFlashAttribute("addItem",addItemList);
		
		
		return "redirect:list";
		
	}
}
