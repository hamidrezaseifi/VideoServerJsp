package de.seifi.videomanagerJsp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.seifi.videomanagerJsp.dao.IFolderDao;

@Controller
public class MainController {

  @Autowired
  IFolderDao folderDao;

  @GetMapping({ "/", "/index" })
  public String home(final Model model) {

    model.addAttribute("name", "test");
    model.addAttribute("folders", this.folderDao.readAll());

    return "index";
  }

}
