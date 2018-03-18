package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Post;
import com.nataliawellness.nataliawellness.entities.Setting;
import com.nataliawellness.nataliawellness.services.CategoryService;
import com.nataliawellness.nataliawellness.services.PostService;
import com.nataliawellness.nataliawellness.services.SettingService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getHomePagePosts());
        return "home";

    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactPage() {

        return "special/contact";

    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutPage() {
        return "special/about";
    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String abloutPage() {
        return "special/blog";
    }

    @RequestMapping(value = "{slug}", method = RequestMethod.GET)
    public String homePage(Model model, @PathVariable(name = "slug", required = false) String slug) {

        //check for page

        //check for post
        model.addAttribute("post", postService.getBySlug(slug));
        return "show_post";


    }

}
