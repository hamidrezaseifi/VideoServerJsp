package de.seifi.videomanagerJsp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.seifi.videomanagerJsp.dao.IFolderDao;
import de.seifi.videomanagerJsp.helper.FileData;
import de.seifi.videomanagerJsp.helper.FileHelper;

@Controller
@RequestMapping(value = "/folders")
public class FoldersController {

  @Autowired
  IFolderDao folderDao;

  @Autowired
  FileHelper fileHelper;

  @GetMapping({ "", "/", "/index" })
  public String index(final Model model) {

    model.addAttribute("folders", this.folderDao.readAll());

    return "folders";
  }

  @GetMapping({ "/edit/{id}" })
  public String showEdit(final Model model, @PathVariable final Long id) {

    model.addAttribute("folder", this.folderDao.readById(id));

    return "folder-edit";
  }

  @GetMapping({ "/delete/{id}" })
  public String showDelete(final Model model, @PathVariable final Long id) {

    model.addAttribute("folder", this.folderDao.readById(id));

    return "folder-delete";
  }

  @GetMapping({ "/listfiles/{id}" })
  @ResponseBody
  public List<FileData> listFolderFiles(final Model model, @PathVariable final Long id) {

    // model.addAttribute("folder", this.folderDao.readById(id));

    final List<FileData> files = this.fileHelper.getAllMediaFiles(this.folderDao.readById(id));

    return files;
  }

}
