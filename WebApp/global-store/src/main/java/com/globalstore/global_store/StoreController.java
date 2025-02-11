package com.globalstore.global_store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {
    List<Item> itemList = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(value = "id", required = false) String id) {
        getCategories();
        int index = findItemIndex(id);
        Item item = (index == Constants.NOT_FOUND) ? new Item() : itemList.get(index);

        model.addAttribute("item", item);
        model.addAttribute("categories", categories);

        return "form.html";
    }

    @GetMapping("/inventory")
    public String getMethodName(Model model) {

        model.addAttribute("items", itemList);

        return "inventory.html";
    }

    @PostMapping("/submitItem")
    public String postMethodName(Item item) {
        itemList.add(item);
        categories.add(item.getCategory());

        return "redirect:/inventory";
    }

    public int findItemIndex(String id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public void getCategories() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("categories.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                categories.add(line);
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
