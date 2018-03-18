package com.nataliawellness.nataliawellness.restcontrollers;


import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.entities.Post;
import com.nataliawellness.nataliawellness.repositories.MenuRepository;
import com.nataliawellness.nataliawellness.services.MenuService;
import com.nataliawellness.nataliawellness.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/post", produces="application/json")
public class PostRestController {


    @Autowired
    PostService postService;

    @Autowired
    MenuService menuService;

    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post create(@Valid @RequestBody Post post){
        //return postService.create(post);
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Post> listPosts(){
        //return postService.findAll();
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public List<Menu> dsadada(){


        return menuService.getSiteMenu();

    }
}
