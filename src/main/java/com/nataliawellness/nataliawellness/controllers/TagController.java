package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Tag;
import com.nataliawellness.nataliawellness.services.CategoryService;
import com.nataliawellness.nataliawellness.services.TagService;
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
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/admin/tag/list", method = RequestMethod.GET)
    public String listTags(Model model){
        model.addAttribute("tags", tagService.findAll());
        return "tags/list";
    }


    @RequestMapping(value = "/admin/tag/create", method = RequestMethod.GET)
    public String createPost(Tag tag){
        return "tags/create";
    }

    @RequestMapping(value = "/admin/tag/create", method = RequestMethod.POST)
    public String validateAndSavePost(@Valid Tag tag,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirAttrs){

        if(tagService.findBySlug(tag.getSlug()) != null){
            bindingResult.rejectValue("slug", "slug","Tag with this slug already exists.");
        }

        if(bindingResult.hasErrors()){
            return "tags/create";
        }

        tagService.create(tag);
        redirAttrs.addFlashAttribute("successMsg", "New tag has been created successfully!");

        return "redirect:/admin/tag/list";

    }


    @RequestMapping(value = "/admin/tag/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model){
        Tag tag = tagService.getById(id);
        model.addAttribute("tag", tag);
        return "tags/edit";
    }

    @RequestMapping(value = "/admin/tag/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Tag tag, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){

        if(bindingResult.hasErrors()){
            return "tags/edit";
        }


        tagService.create(tag);
        redirAttrs.addFlashAttribute("successMsg", "New tag has been updated successfully!");

        return "redirect:/admin/tag/list";
    }


    @RequestMapping(value = "/admin/tag/delete", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, RedirectAttributes redirectAttributes){
        
        tagService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMsg", "Tag has been deleted successfully!");
        return "redirect:/admin/tag/list";

    }


}
