package com.marek.testapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WebController {
    @GetMapping("/")
    public String personFormForm(Model model) {
        model.addAttribute("personForm", new PersonForm());
        return "form";
    }

    @PostMapping("/")
    public String personFormSubmit(@ModelAttribute PersonForm personForm) {
        return "results";
    }
}
