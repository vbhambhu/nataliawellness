package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Page;
import com.nataliawellness.nataliawellness.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/admin/page/list", method = RequestMethod.GET)
    public String listPages(Model model){
        model.addAttribute("pages", pageService.findAll());
        return "pages/list";
    }

    @RequestMapping(value = "/admin/page/create", method = RequestMethod.GET)
    public String createPage(Page page){
        return "pages/create";
    }


    @RequestMapping(value = "/admin/page/create", method = RequestMethod.POST)
    public String createPages(Model model, @Valid Page page,
                              BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "pages/create";
        }

        pageService.create(page);
        return "redirect:/admin/page/list";

    }

    @RequestMapping(value = "/admin/page/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model, Page page){

        String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        model.addAttribute("page", pageService.getById(id));
        return "pages/edit";
    }

    @RequestMapping(value = "/admin/page/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam Long id, Model model, @Valid Page page,
                           BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            return "pages/edit";
        }

        pageService.create(page);
        return "redirect:/admin/page/list";

    }






}
