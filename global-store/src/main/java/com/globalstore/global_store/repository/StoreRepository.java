package com.globalstore.global_store.repository;

import java.util.ArrayList;
import java.util.List;

import com.globalstore.global_store.Constants;
import com.globalstore.global_store.Item;

public class StoreRepository {
    private List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return this.itemList;
    }

    public Item getItem(String id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return itemList.get(i);
            }
        }
        return new Item();
    }

    public int getItemIndex(String id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public String addOrUpdateItem(Item item) {
        int index = getItemIndex(item.getId());
        if (index == Constants.NOT_FOUND) {
            itemList.add(item);
            return Constants.SUCCESS;
        } else {
            itemList.set(index, item);
            return Constants.SUCCESS;
        }
    }
}
