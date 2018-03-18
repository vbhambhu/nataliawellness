package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Post;
import com.nataliawellness.nataliawellness.helpers.SiteHelper;
import com.nataliawellness.nataliawellness.services.PostService;
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
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    TagService tagService;


    @RequestMapping(value = "/admin/post/list", method = RequestMethod.GET)
    public String listPosts(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/list";
    }

    @RequestMapping(value = "/admin/post/create", method = RequestMethod.GET)
    public String createPost(Model model, Post post){

        String jsFiles[] = {"select2.min.js","tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        model.addAttribute("cats", postService.getAllCategories());
        model.addAttribute("tags", tagService.findAll());

        return "posts/create";
    }

    @RequestMapping(value = "/admin/post/create", method = RequestMethod.POST)
    public String validateAndSavePost(Model model, @Valid Post post, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("cats", postService.getAllCategories());
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);

            model.addAttribute("tags", tagService.findAll());
            return "posts/create";
        }

        postService.create(post);
        return "redirect:/admin/post/list";

    }

    @RequestMapping(value = "/admin/post/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model,Post post){

        String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);


        model.addAttribute("post", postService.getById(id));
        model.addAttribute("cats", postService.getAllCategories());
        model.addAttribute("tags", tagService.findAll());

        return "posts/edit";
    }

    @RequestMapping(value = "/admin/post/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Post post, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){


        post.setSlug(SiteHelper.encodeForUrl(post.getSlug()));

        if(postService.isDuplicateSlug(post)){
            bindingResult.rejectValue("slug", "article.slug","Slug already exists.");
        }


        if(bindingResult.hasErrors()){
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            model.addAttribute("cats", postService.getAllCategories());
            return "posts/edit";
        }




        Post articleDb = postService.getById(id);
        post.setId(articleDb.getId());
        post.setCreatedAt(articleDb.getCreatedAt());
        post.setAuthor(articleDb.getAuthor());
        postService.update(post);
        return "redirect:/admin/post/list";


    }


    @RequestMapping(value = "/admin/post/delete", method = RequestMethod.POST)
    public String saveEditedPost(Post post, RedirectAttributes redirAttrs){

       postService.delete(post);

        redirAttrs.addFlashAttribute("message", "Post deleted successfully!");
        return "redirect:/admin/post/list";

    }
}
