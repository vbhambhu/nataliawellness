package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.services.CategoryService;
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
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/admin/category/list", method = RequestMethod.GET)
    public String listPosts(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }


    @RequestMapping(value = "/admin/category/create", method = RequestMethod.GET)
    public String createPost(Model model, Category category){
        model.addAttribute("cats", categoryService.findAll());
        return "categories/create";
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
    public String validateAndSavePost(Model model, @Valid Category category,
                                      BindingResult bindingResult, RedirectAttributes redirAttrs){

        if(categoryService.findBySlug(category.getSlug()) != null){
            bindingResult.rejectValue("slug", "slug","Category with this slug already exists.");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("cats", categoryService.findAll());
            return "categories/create";
        }

        categoryService.create(category);
        redirAttrs.addFlashAttribute("successMsg", "New category has been created successfully!");

        return "redirect:/admin/category/list";

    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model){

        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("cats", categoryService.findAll());
        return "categories/edit";
    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Category category, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){

        if(bindingResult.hasErrors()){
            model.addAttribute("cats", categoryService.findAll());
            return "categories/edit";
        }


        categoryService.create(category);
        redirAttrs.addFlashAttribute("successMsg", "New category has been updated successfully!");

        return "redirect:/admin/category/list";
    }


    @RequestMapping(value = "/admin/category/delete", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, RedirectAttributes redirectAttributes){

        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMsg", "Category has been deleted successfully!");
        return "redirect:/admin/category/list";

    }


}
