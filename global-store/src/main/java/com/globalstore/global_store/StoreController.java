package com.globalstore.global_store;

import java.util.Date;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class StoreController {
    private Logic logic = new Logic();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false, value = "id") String id) {

        model.addAttribute("item", logic.getItem(id));

        return "form.html";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(
            @Valid Item item,
            BindingResult result,
            RedirectAttributes redirectAttributes, HttpSession session) {

        int index = logic.getItemIndexIfExist(item.getId());
        if (index != Constants.NOT_FOUND) {
            session.setAttribute("oldDate", logic.getItemList().get(index).getDate());
        }
        // else {
        // session.setAttribute("oldDate", item.getDate());
        // }

        if (result.hasErrors()) {
            return "form";
        }
        String status = logic.updateItemInfo(item);

        redirectAttributes.addFlashAttribute("status", status);

        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {

        model.addAttribute("items", logic.getItemList());

        return "inventory.html";
    }

}
