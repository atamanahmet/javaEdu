package com.globalstore.global_store;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Logic {
    private DataAccess dataAccess;

    public Logic() {
        this.dataAccess = new DataAccess();
    }

    public Item getItem(String id) {
        return this.dataAccess.getItem(id);
    }

    public int getItemIndexIfExist(String id) {
        return this.dataAccess.getItemIndexIfExist(id);
    }

    public String updateItemInfo(Item item) {
        return this.dataAccess.updateItemInfo(item);
    }

    public List<Item> getItemList() {
        return this.dataAccess.getItemList();
    }
}
