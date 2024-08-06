package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "Harley");
        return "greeting";
    }

    @GetMapping("/bye")
    public String seeYouLater(Model model) {
        model.addAttribute("nickname", "readyoun");
        return "goodbye";
    }

}
