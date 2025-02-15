package com.globalstore.global_store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StoreController {
    private List<Item> itemList = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false, value = "id") String id) {

        int index = getItemIndexIfExist(id);
        Item item = (index == Constants.NOT_FOUND) ? new Item() : itemList.get(index);

        model.addAttribute("item", item);

        return "form.html";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "form";
        }
        int index = getItemIndexIfExist(item.getId());
        String status = Constants.SUCCESS;
        if (index == Constants.NOT_FOUND) {
            itemList.add(item);
        } else {
            if (isItWithinFiveDays(item, itemList.get(index))) {
                itemList.set(index, item);
            } else {
                status = Constants.FAILED;
            }
        }

        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", itemList);
        return "inventory.html";
    }

    public int getItemIndexIfExist(String id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public boolean isItWithinFiveDays(Item oldItem, Item newItem) {
        long difference = Math.abs(oldItem.getDate().getTime() - newItem.getDate().getTime());
        System.out.println(TimeUnit.MILLISECONDS.toDays(difference));
        if ((int) TimeUnit.MILLISECONDS.toDays(difference) > 5) {
            return false;
        }
        return true;
    }

    public boolean isPriceValid(Item item) {
        if (item.getPrice() < item.getDiscount()) {
            return false;
        }
        return true;
    }

}
