package de.seifi.videomanagerJsp.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import de.seifi.videomanagerJsp.dao.IFolderDao;
import de.seifi.videomanagerJsp.helper.FileData;
import de.seifi.videomanagerJsp.helper.FileHelper;
import de.seifi.videomanagerJsp.models.FolderModel;

@Controller
@RequestMapping(value = "/folders")
public class FoldersController {

  @Autowired
  IFolderDao folderDao;

  @Autowired
  FileHelper fileHelper;

  @GetMapping({ "", "/", "/index" })
  public String index(final Model model) {

    model.addAttribute("folders", this.folderDao.readAll(true));

    return "folders";
  }

  @GetMapping({ "/edit/{id}" })
  public String showEdit(final Model model, @PathVariable final Long id,
      @RequestParam(name = "msg", required = false, defaultValue = "") final String msg) {

    model.addAttribute("folder", this.folderDao.readById(id));
    model.addAttribute("msg", msg);
    model.addAttribute("hasMessage", msg != "");

    return "folder-edit";
  }

  @PostMapping({ "/postedit" })
  @ResponseBody
  public RedirectView postEdit(@RequestParam final Long folderid, @RequestParam final String foldername,
      @RequestParam final String folderpath,
      @RequestParam final int state) {

    final File folderFile = new File(folderpath);

    if (folderFile.exists() == false) {

      return new RedirectView("/folders/edit?msg=invalid folder path");
    }
    else {
      final FolderModel folder = new FolderModel();
      folder.setName(foldername);
      folder.setPath(folderpath);
      folder.setState(state);
      folder.setId(folderid);
      this.folderDao.save(folder);

      return new RedirectView("/folders");
    }

  }

  @GetMapping({ "/create" })
  public String showCreate(final Model model, @RequestParam(name = "msg", required = false, defaultValue = "") final String msg) {

    model.addAttribute("msg", msg);
    model.addAttribute("hasMessage", msg != "");

    return "folder-create";
  }

  @PostMapping({ "/postcreate" })
  @ResponseBody
  public RedirectView postCreate(@RequestParam final String foldername, @RequestParam final String folderpath) {

    final File folderFile = new File(folderpath);

    if (folderFile.exists() == false) {

      return new RedirectView("/folders/create?msg=invalid folder path");
    }
    else {
      final FolderModel folder = new FolderModel();
      folder.setName(foldername);
      folder.setPath(folderpath);
      folder.setState(1);
      this.folderDao.save(folder);

      return new RedirectView("/folders");
    }

  }

  @GetMapping({ "/delete/{id}" })
  public String showDelete(final Model model, @PathVariable final Long id) {

    model.addAttribute("folder", this.folderDao.readById(id));

    return "folder-delete";
  }

  @PostMapping({ "/postdelete" })
  @ResponseBody
  public RedirectView postDelete(@RequestParam final Long folderid) {

    final FolderModel folder = this.folderDao.readById(folderid);
    this.folderDao.delete(folder);

    return new RedirectView("/folders");

  }

  @GetMapping({ "/listfiles/{id}" })
  @ResponseBody
  public List<FileData> listFolderFiles(final Model model, @PathVariable final Long id) {

    // model.addAttribute("folder", this.folderDao.readById(id));

    final List<FileData> files = this.fileHelper.getAllMediaFiles(this.folderDao.readById(id));

    return files;
  }

}
