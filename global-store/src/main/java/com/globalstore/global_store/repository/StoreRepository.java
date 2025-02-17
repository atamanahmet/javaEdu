package com.globalstore.global_store.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.globalstore.global_store.Item;

@Repository
public class StoreRepository {
    private List<Item> itemList = new ArrayList<>();
    private Map<String, Date> oldDates = new HashMap<>();

    public List<Item> getItemList() {

        return this.itemList;
    }

    public Item getItem(int index) {

        return itemList.get(index);
    }

    public void addItem(Item item) {

        itemList.add(item);
    }

    public void updateItem(Item item, int index) {

        itemList.set(index, item);
    }

    public Map<String, Date> getOldDateMap() {
        return this.oldDates;
    }

    public void setoldDates(Map<String, Date> oldDates) {

        this.oldDates = oldDates;
    }

    public void putOldDate(String id, Date date) {

        oldDates.put(id, date);
    }

}
