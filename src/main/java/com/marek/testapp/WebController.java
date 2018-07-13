package com.marek.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class WebController {
    private final StorageService storageService;


    @Autowired
    public WebController(StorageService storageService) {
        this.storageService = storageService;
    }

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

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String personFormSubmit(@RequestParam("file") MultipartFile file,
                                   @ModelAttribute PersonForm personForm,
                                   Model model) {
        if (file != null) {
            storageService.store(file);
        }

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(WebController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "results";
    }
}
