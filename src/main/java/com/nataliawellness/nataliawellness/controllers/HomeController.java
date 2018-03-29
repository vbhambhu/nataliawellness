package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Page;
import com.nataliawellness.nataliawellness.entities.Post;
import com.nataliawellness.nataliawellness.services.CategoryService;
import com.nataliawellness.nataliawellness.services.PageService;
import com.nataliawellness.nataliawellness.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PageService pageService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getHomePagePosts());
        return "special/home";

    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactPage() {

        return "special/contact";

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String aboutPage(@RequestParam String q) {
        System.out.println(q);
        return "special/search";
    }



    @RequestMapping(value = "/blog/{slug}", method = RequestMethod.GET)
    public String blogPost(Model model, @PathVariable(name = "slug", required = false) String slug) {

        //check for post
        Post post = postService.getBySlug(slug);
        if(post != null){
            model.addAttribute("post", post);
            model.addAttribute("metaTitle", post.getTitle());
            model.addAttribute("metaDescription", post.getMetaDescription());
            return "special/blog";
        }


        model.addAttribute("page404", true);
        return "show_404";

    }



    @RequestMapping(value = "/category/{slug}", method = RequestMethod.GET)
    public String dasdda(Model model, @PathVariable(name = "slug", required = false) String slug) {


        return "dd";
//        //category
//        if(categoryService.findBySlug(slug) != null){
//
//            Category category = categoryService.findBySlug(slug);
//            model.addAttribute("category", category);
//
//            if(category.getParent() == null){
//                System.out.println("child category");
//                return "special/parent_category";
//            }
//
//            //otherwise single category
//            System.out.println("parent category");
//            return "special/child_category";
//
//        }
//
//
//        //check for page
//        Page page = pageService.getBySlug(slug);
//        if(page != null){
//            model.addAttribute("metaTitle", page.getTitle());
//            model.addAttribute("metaDescription", page.getMetaDescription());
//            model.addAttribute("page", page);
//            return "show_page";
//        }
//
//        //check for post
//        Post post = postService.getBySlug(slug);
//        if(post != null){
//            model.addAttribute("post", post);
//            model.addAttribute("metaTitle", post.getTitle());
//            model.addAttribute("metaDescription", post.getMetaDescription());
//            return "show_post";
//        }
//
//
//        model.addAttribute("page404", true);
//        return "show_404";

    }

}
