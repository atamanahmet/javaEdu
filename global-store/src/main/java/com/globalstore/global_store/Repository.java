package com.globalstore.global_store;

import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

// @Repository
class DataAccess {
    private List<Item> itemList = new ArrayList<>();

    public Item getItem(String id) {
        int index = getItemIndexIfExist(id);
        return (index == Constants.NOT_FOUND) ? new Item() : itemList.get(index);
    }

    public String updateItemInfo(Item item) {
        int index = getItemIndexIfExist(item.getId());

        String status = Constants.SUCCESS;

        if (index == Constants.NOT_FOUND) {
            itemList.add(item);
        } else {
            itemList.set(index, item);
        }
        return status;
    }

    public int getItemIndexIfExist(String id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

}
