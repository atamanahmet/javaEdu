package com.globalstore.global_store.service;

import java.util.List;

import com.globalstore.global_store.Item;
import com.globalstore.global_store.repository.StoreRepository;

public class StoreService {
    StoreRepository storeRepo;

    public StoreService() {
        this.storeRepo = new StoreRepository();
    }

    public int getItemIndex(String id) {
        return storeRepo.getItemIndex(id);
    }

    public Item getItem(String id) {
        return storeRepo.getItem(id);
    }

    public List<Item> getItemList() {
        return storeRepo.getItemList();
    }

    public String addOrUpdateItem(Item item) {
        return storeRepo.addOrUpdateItem(item);
    }
}
