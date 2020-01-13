package de.seifi.videomanagerJsp.controllers;

import java.io.File;

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

import de.seifi.videomanagerJsp.dao.IPathSubtitleDao;
import de.seifi.videomanagerJsp.helper.FileHelper;
import de.seifi.videomanagerJsp.models.PathSubtitleModel;

@Controller
@RequestMapping(value = "/subtitles")
public class SubtitlePathController {

  @Autowired
  IPathSubtitleDao pathSubtitleDao;

  @Autowired
  FileHelper fileHelper;

  @GetMapping({ "", "/", "/index" })
  public String index(final Model model) {

    model.addAttribute("subtitles", this.pathSubtitleDao.readAll());

    return "subtitles";
  }

  @GetMapping({ "/edit/{id}" })
  public String showEdit(final Model model, @PathVariable final Long id,
      @RequestParam(name = "msg", required = false, defaultValue = "") final String msg) {

    model.addAttribute("subtitle", this.pathSubtitleDao.readById(id));
    model.addAttribute("msg", msg);
    model.addAttribute("hasMessage", msg != "");

    return "subtitle-edit";
  }

  @PostMapping({ "/postedit" })
  @ResponseBody
  public RedirectView postEdit(@RequestParam final Long id, @RequestParam final String path, @RequestParam final String suburl) {

    final File folderFile = new File(path);

    if (folderFile.exists() == false) {

      return new RedirectView("/subtitles/edit/" + id + "?msg=invalid subtitle path");
    }
    else {
      PathSubtitleModel sub = this.pathSubtitleDao.getPathSubtitlesFromPath(path);
      if (sub != null && sub.getId() != id) {
        return new RedirectView("/subtitles/edit/" + id + "?msg=path exists!");
      }
      else {
        sub = this.pathSubtitleDao.getPathSubtitlesFromSubUrl(suburl);
        if (sub != null && sub.getId() != id) {
          return new RedirectView("/subtitles/edit/" + id + "?msg=sub-url exists");
        }
        else {
          final PathSubtitleModel subtitle = new PathSubtitleModel();
          subtitle.setPath(path);
          subtitle.setSuburl(suburl);

          subtitle.setId(id);
          this.pathSubtitleDao.save(subtitle);

          return new RedirectView("/subtitles");
        }
      }

    }

  }

  @GetMapping({ "/create" })
  public String showCreate(final Model model, @RequestParam(name = "msg", required = false, defaultValue = "") final String msg) {

    model.addAttribute("msg", msg);
    model.addAttribute("hasMessage", msg != "");

    return "subtitle-create";
  }

  @PostMapping({ "/postcreate" })
  @ResponseBody
  public RedirectView postCreate(@RequestParam final String path, @RequestParam final String suburl) {

    final File folderFile = new File(path);

    if (folderFile.exists() == false) {

      return new RedirectView("/subtitles/create?msg=invalid subtitle path");
    }
    else {
      PathSubtitleModel sub = this.pathSubtitleDao.getPathSubtitlesFromPath(path);
      if (sub != null) {
        return new RedirectView("/subtitles/create?msg=path exists!");
      }
      else {
        sub = this.pathSubtitleDao.getPathSubtitlesFromSubUrl(suburl);
        if (sub != null) {
          return new RedirectView("/subtitles/create?msg=sub-url exists");
        }
        else {
          final PathSubtitleModel subtitle = new PathSubtitleModel();
          subtitle.setPath(path);
          subtitle.setSuburl(suburl);
          this.pathSubtitleDao.save(subtitle);

          return new RedirectView("/subtitles");
        }
      }

    }

  }

  @GetMapping({ "/delete/{id}" })
  public String showDelete(final Model model, @PathVariable final Long id) {

    model.addAttribute("subtitle", this.pathSubtitleDao.readById(id));

    return "subtitle-delete";
  }

  @PostMapping({ "/postdelete" })
  @ResponseBody
  public RedirectView postDelete(@RequestParam final Long folderid) {

    final PathSubtitleModel subtitle = this.pathSubtitleDao.readById(folderid);
    this.pathSubtitleDao.delete(subtitle);

    return new RedirectView("/subtitles");

  }

}
