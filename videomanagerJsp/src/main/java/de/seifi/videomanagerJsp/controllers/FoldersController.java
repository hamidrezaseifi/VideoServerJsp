package de.seifi.videomanagerJsp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.seifi.videomanagerJsp.dao.IFolderDao;

@Controller
@RequestMapping(value = "/folders")
public class FoldersController {

  @Autowired
  IFolderDao folderDao;

  @GetMapping({ "", "/", "/index" })
  public String index(final Model model) {

    model.addAttribute("folders", this.folderDao.readAll());

    return "folders";
  }

  @GetMapping({ "/edit/{id}" })
  public String showEdit(final Model model, @PathVariable final Long id) {

    model.addAttribute("folders", this.folderDao.readById(id));

    return "folder-edit";
  }

  @GetMapping({ "/delete/{id}" })
  public String showDelete(final Model model, @PathVariable final Long id) {

    model.addAttribute("folders", this.folderDao.readById(id));

    return "folder-delete";
  }

}
