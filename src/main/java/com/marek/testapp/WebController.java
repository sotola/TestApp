package com.marek.testapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


@Controller
public class WebController {
    @GetMapping("/")
    public String personFormForm(Model model) {
        model.addAttribute("personForm", new PersonForm());

        List<String> countries = new LinkedList<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            if (!locale.getDisplayCountry().isEmpty() && !countries.contains(locale.getDisplayCountry())) {
                countries.add(locale.getDisplayCountry());
            }
        }
        countries.sort(Comparator.naturalOrder());
        model.addAttribute("countries", countries);

        return "form";
    }

    @PostMapping("/")
    public String personFormSubmit(@ModelAttribute PersonForm personForm) {
        return "results";
    }
}
