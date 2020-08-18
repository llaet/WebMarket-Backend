package com.saleshub.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.OrderedItem;
import com.saleshub.repositories.OrderedItemRepository;

@Service
public class OrderedItemService {
	
	@Autowired
	private OrderedItemRepository repository;
	
	public List<OrderedItem> saveAll(Set<OrderedItem> orderedItems){
	
		return this.repository.saveAll(orderedItems);
	}

}
