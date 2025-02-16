package com.globalstore.global_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globalstore.global_store.Item;
import com.globalstore.global_store.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {
    private StoreService storeService = StoreService.getInstance();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false, value = "id") String id) {
        model.addAttribute("item", storeService.getItem(id));
        return "form";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "form";
        }

        redirectAttributes.addFlashAttribute("status", storeService.addOrUpdateItem(item));
        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", storeService.getItemList());
        return "inventory";
    }

}
