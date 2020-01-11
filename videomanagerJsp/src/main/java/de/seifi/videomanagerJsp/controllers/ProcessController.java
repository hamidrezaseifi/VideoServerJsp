package de.seifi.videomanagerJsp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.seifi.videomanagerJsp.dao.IFolderDao;
import de.seifi.videomanagerJsp.helper.FileHelper;
import de.seifi.videomanagerJsp.helper.SubtitleRequest;
import de.seifi.videomanagerJsp.process.IVideoProcess;
import de.seifi.videomanagerJsp.process.MkvProcess;
import de.seifi.videomanagerJsp.process.ProcessInfo;
import de.seifi.videomanagerJsp.process.VideoProcessManager;

@Controller
@RequestMapping(value = "/process")
public class ProcessController {

  @Autowired
  IFolderDao folderDao;

  @Autowired
  FileHelper fileHelper;

  @Autowired
  VideoProcessManager videoProcessManager;

  @PostMapping({ "/add" })
  @ResponseBody
  public Map<String, String> addProcess(@RequestBody final SubtitleRequest request) {

    final Map<String, String> list = new HashMap<>();

    final MkvProcess proc = (MkvProcess) this.videoProcessManager.add(new MkvProcess(request.getFile(), request.getLanguage()));
    if (proc != null) {
      list.put("outpath", proc.getOutputPath());
    }
    else {
      list.put("outpath", "");
    }

    list.put("result", proc != null ? "ok" : "exists");
    list.put("count", "" + this.videoProcessManager.size());

    return list;
  }

  @RequestMapping(value = "/proclist", produces = { "application/json" })
  @ResponseBody
  public List<ProcessInfo> processList() {

    final List<ProcessInfo> list = this.videoProcessManager.getInfoList();

    return list;
  }

  @PostMapping(value = "/changeproc", produces = { "application/json" })
  @ResponseBody
  public Map<String, Object> toggleProcess(@RequestBody final SubtitleRequest request) {

    final Map<String, Object> list = new HashMap<>();

    final IVideoProcess proc = this.videoProcessManager.processByOutput(request.getFile());
    if (proc != null) {

      if (request.isCommandStart()) {
        proc.start();
        list.put("act", "start");
      }
      if (request.isCommandStop()) {
        proc.stop();
        list.put("act", "stop");
      }
      if (request.isCommandDelete()) {
        if (proc.isRunning()) {
          list.put("act", "running");
        }
        else {

          final int size = this.videoProcessManager.size();
          this.videoProcessManager.removeByOutput(request.getFile());
          list.put("act", size > this.videoProcessManager.size() ? "del" : "nodel");
        }

      }

      try {
        Thread.sleep(500);
      }
      catch (final InterruptedException e) {

      }
      list.put("info", proc.getInfo());

    }
    else {
      list.put("outpath", "");
    }

    list.put("found", proc != null ? "ok" : "no");

    return list;
  }

  @PostMapping(value = "/checkproc", produces = { "application/json" })
  @ResponseBody
  public Map<String, Object> checkProcess(@RequestBody final SubtitleRequest request) {

    final Map<String, Object> list = new HashMap<>();

    final IVideoProcess proc = this.videoProcessManager.processByOutput(request.getFile());
    if (proc != null) {
      list.put("info", proc.getInfo());
    }
    else {
      list.put("outpath", "");
    }

    list.put("found", proc != null ? "ok" : "no");

    return list;
  }

}
