package com.stc.managingFiles.service;



import java.io.IOException;

import com.stc.managingFiles.entity.Item;
import com.stc.managingFiles.enums.ItemType;

public interface ItemService {
	Item createItem(Item item, ItemType itemType) throws IOException;
}
