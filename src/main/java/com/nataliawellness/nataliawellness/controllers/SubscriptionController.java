package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Page;
import com.nataliawellness.nataliawellness.entities.Post;
import com.nataliawellness.nataliawellness.entities.Subscription;
import com.nataliawellness.nataliawellness.services.CategoryService;
import com.nataliawellness.nataliawellness.services.PageService;
import com.nataliawellness.nataliawellness.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SubscriptionController {


    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public String subscribeUser(Model model, @Valid Subscription subscription, BindingResult bindingResult) {


        return "special/home";

    }


}
