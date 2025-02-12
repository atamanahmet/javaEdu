package com.globalstore.global_store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {
    List<Item> itemList = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date prevDate;

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(value = "id", required = false) String id) {
        categories = new ArrayList<>();

        getCategories();
        int index = findItemIndex(id);
        Item item = (index == Constants.NOT_FOUND) ? new Item() : itemList.get(index);

        if (index != Constants.NOT_FOUND) {
            prevDate = itemList.get(index).getDate();
        }

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
    public String postMethodName(Item item, RedirectAttributes redirectAttributes) {
        String status;

        int index = findItemIndex(item.getId());
        if (index == Constants.NOT_FOUND) {
            itemList.add(item);
            status = "success";

        } else {
            int timeDifference = Math
                    .abs((int) (prevDate.getTime() - item.getDate().getTime()) / (1000 * 60 * 60 * 24));
            if (timeDifference > 5) {
                status = "failed";
            } else {
                itemList.set(index, item);
                status = "success";
            }

        }
        redirectAttributes.addFlashAttribute("status", status);
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
