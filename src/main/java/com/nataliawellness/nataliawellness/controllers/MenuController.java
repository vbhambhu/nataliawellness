package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/admin/menu/list", method = RequestMethod.GET)
    public String listMenu(Model model){

        model.addAttribute("menuList",  menuService.findAll());
        return "menu/list";
    }


    @RequestMapping(value = "/admin/menu/create", method = RequestMethod.GET)
    public String createMenu(Model model, Menu menu){

        model.addAttribute("menuList",  menuService.findAll());
        String jsFiles[] = {"select2.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        return "menu/create";

    }

    @RequestMapping(value = "/admin/menu/create", method = RequestMethod.POST)
    public String saveMenu( Model model, @Valid Menu menu, BindingResult bindingResult){


//        if(menu.getType() == 1 && menu.getArticleId() == null){
//            bindingResult.rejectValue("articleId", "menu.articleId", "Invalid article selected.");
//        }


        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("menuList",  menuService.findAll());
            String jsFiles[] = {"select2.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            return "menu/create";
        }

        menuService.create(menu);
        return "redirect:/admin/menu/list";

    }

    @RequestMapping(value = "/admin/menu/edit", method = RequestMethod.GET)
    public String editMenu(@RequestParam Long id, Model model){

        Menu menu = menuService.getById(id);
        model.addAttribute("menu", menu);
        model.addAttribute("menuList",  menuService.findAll());
        return "menu/edit";
    }

    @RequestMapping(value = "/admin/menu/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Menu menu, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){

        if(bindingResult.hasErrors()){
            model.addAttribute("menuList",  menuService.findAll());
            return "menu/edit";
        }


        menuService.create(menu);
        redirAttrs.addFlashAttribute("successMsg", "New menu has been updated successfully!");

        return "redirect:/admin/menu/list";
    }


    @RequestMapping(value = "/admin/menu/delete", method = RequestMethod.GET)
    public String deleteMenu(@RequestParam Long id, RedirectAttributes redirectAttributes){

        menuService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMsg", "Menu has been deleted successfully!");
        return "redirect:/admin/menu/list";

    }





}
