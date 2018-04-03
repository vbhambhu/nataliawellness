package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.services.CategoryService;
import com.nataliawellness.nataliawellness.services.MenuService;
import com.nataliawellness.nataliawellness.services.PageService;
import com.nataliawellness.nataliawellness.services.PostService;
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

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/admin/menu/list", method = RequestMethod.GET)
    public String listMenu(Model model, Menu menu){

        setModelAttrs(model);
        return "menu/list";
    }



    @RequestMapping(value = "/admin/menu/create", method = RequestMethod.POST)
    public String addPage(Model model, @Valid Menu menu, BindingResult bindingResult,
                          @RequestParam("type") String type){

        if(bindingResult.hasErrors()) {
            setModelAttrs(model);
            return "menu/list";
        }

        if(type.equals("cat")){
            menu.setSlug("/cat/"+menu.getSlug());
        } else{
            menu.setSlug("/"+menu.getSlug());
        }

        menuService.create(menu);
        return "redirect:/admin/menu/list";
    }

    private Model setModelAttrs(Model model){

        model.addAttribute("menuList",  menuService.getSiteMenu());
        String[] jsFiles = {"select2.min.js","jquery-ui.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        model.addAttribute("posts",  postService.getAllPosts());
        model.addAttribute("pages",  pageService.findAll());
        model.addAttribute("categories",  categoryService.findAll());
        return model;
    }


    @RequestMapping(value = "/admin/menu/delete", method = RequestMethod.GET)
    public String deleteMenu(@RequestParam Long id, RedirectAttributes redirectAttributes){

        menuService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMsg", "Menu has been deleted successfully!");
        return "redirect:/admin/menu/list";

    }





}
