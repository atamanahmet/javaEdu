package com.globalstore.global_store.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.globalstore.global_store.Item;
import com.globalstore.global_store.repository.StoreRepository;

@Service
public class StoreService {
    private static StoreRepository storeRepo;
    private static StoreService instance;

    public StoreService() {

    }

    public static StoreService getInstance() {
        if (instance == null) {
            instance = new StoreService();
            storeRepo = new StoreRepository();
        }
        return instance;

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

    public Map<String, Date> getOldDateMap() {
        return storeRepo.getOldDateMap();
    }
}
