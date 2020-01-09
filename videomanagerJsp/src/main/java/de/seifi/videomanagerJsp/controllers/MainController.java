package de.seifi.videomanagerJsp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping({ "/", "/index" })
  public String hello(final Model model) {

    model.addAttribute("name", "test");
    return "index";
  }

}
