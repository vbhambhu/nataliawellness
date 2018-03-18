package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String listMenu(Model model){

        model.addAttribute("menuList",  menuService.getAllMenuItems());
        return "menu/list";
    }


    @RequestMapping(value = "/menu/create", method = RequestMethod.GET)
    public String createMenu(Model model, Menu menu){

        model.addAttribute("menuList",  menuService.getAllMenuItems());

        String jsFiles[] = {"select2.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        return "menu/create";

    }

    @RequestMapping(value = "/menu/create", method = RequestMethod.POST)
    public String saveMenu(@Valid Menu menu, Model model, BindingResult bindingResult){


//        if(menu.getType() == 1 && menu.getArticleId() == null){
//            bindingResult.rejectValue("articleId", "menu.articleId", "Invalid article selected.");
//        }


        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("menuList",  menuService.getAllMenuItems());
            String jsFiles[] = {"select2.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            return "menu/create";
        }



        return null;


    }




}
