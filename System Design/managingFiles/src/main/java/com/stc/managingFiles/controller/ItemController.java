package com.stc.managingFiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stc.managingFiles.entity.Item;
import com.stc.managingFiles.enums.ItemType;
import com.stc.managingFiles.service.ItemService;

import java.io.IOException;

@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	ItemService serviceItem;

	@PostMapping(path = "/{itemType}")
	public ResponseEntity<Item> CreateItem(@RequestBody Item item, @PathVariable ItemType itemType) throws IOException {
		Item savedItem = serviceItem.createItem(item, itemType);

		if (savedItem != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}