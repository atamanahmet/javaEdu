package com.globalstore.global_store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalstore.global_store.Constants;
import com.globalstore.global_store.Item;
import com.globalstore.global_store.repository.StoreRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepo;

    public StoreService() {
    }

    // public static StoreService getInstance() {

    // if (instance == null) {
    // instance = new StoreService();
    // storeRepo = new StoreRepository();
    // }
    // return instance;
    // }

    public int getItemIndex(String id) {
        List<Item> itemList = new ArrayList<>(getItemList());

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public String submitItem(Item item) {

        int index = getItemIndex(item.getId());

        if (index == Constants.NOT_FOUND) {
            storeRepo.putOldDate(item.getId(), item.getDate());
            storeRepo.addItem(item);
        } else {
            storeRepo.putOldDate(item.getId(), item.getDate());
            storeRepo.updateItem(item, index);
        }
        return Constants.SUCCESS;
    }

    public Item getItem(String id) {

        int index = getItemIndex(id);
        if (index == Constants.NOT_FOUND) {
            return new Item();
        } else {
            return storeRepo.getItem(index);
        }
    }

    public List<Item> getItemList() {

        return storeRepo.getItemList();
    }

    public String updateItem(Item item, int index) {

        storeRepo.updateItem(item, index);
        return Constants.SUCCESS;
    }

    public String addItem(Item item) {

        storeRepo.addItem(item);
        return Constants.SUCCESS;

    }

    public Date getOldDate(String id) {

        return storeRepo.getOldDateMap().get(id);
    }
}
