package de.seifi.videomanagerJsp.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import de.seifi.videomanagerJsp.helper.FileData;
import de.seifi.videomanagerJsp.helper.GuiSocketMessage;

public class MkvProcess implements Runnable, IVideoProcess {

  private Process pMainProcess;
  private final List<String> command;
  private final List<String> statusList;
  private final List<String> errorList;
  private final FileData fileData;
  private ProcessState state;
  private int lastPercent;
  private String lastPercentString;
  private int hashData;
  private final SimpMessagingTemplate simpMessagingTemplate;

  public MkvProcess(final String filepathhash, final String lang, final SimpMessagingTemplate simpMessagingTemplate) {

    final byte[] realpathdata = Base64.getDecoder().decode(filepathhash.getBytes());
    final String realfilePath = new String(realpathdata);

    this.fileData = new FileData(realfilePath);
    this.command = new ArrayList<>();
    this.statusList = new ArrayList<>();
    this.errorList = new ArrayList<>();
    this.state = ProcessState.Idle;

    this.command.add("\"C:\\Program Files\\MKVToolNix\\mkvmerge\"");
    this.command.add("-o");
    this.command.add(this.fileData.getOutputPath());
    this.command.add(this.fileData.getPath());
    this.command.add("--language");
    this.command.add("\"0:" + lang + "\"");
    this.command.add(this.fileData.getSubtitlePath());

    this.lastPercent = 0;
    this.lastPercentString = "0";
    this.pMainProcess = null;
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  @Override
  public void run() {

    final ProcessBuilder builder = new ProcessBuilder(this.command);

    try {
      this.pMainProcess = builder.start();
      this.state = ProcessState.Running;

      final InputStream is = this.pMainProcess.getInputStream();
      final InputStreamReader isr = new InputStreamReader(is);
      final BufferedReader br = new BufferedReader(isr);

      final GuiSocketMessage msg = GuiSocketMessage.generate("ok");
      msg.setCommand("status");
      msg.setProcessHash(this.hashData);

      String line;
      while ((line = br.readLine()) != null) {
        this.statusList.add(line);
        line = line.trim();
        if (line.startsWith("Progress")) {

          final String s = line.replace("Progress", "").replace(":", "").replace("%", "").trim();

          this.lastPercentString = s;
          try {
            this.lastPercent = Integer.parseInt(s);
          }
          catch (final Exception ex) {
            this.lastPercentString = line + " : " + s + " : err: " + ex.getMessage();
          }

          msg.setProcessInfo(this.getInfo());
          this.simpMessagingTemplate.convertAndSend("/socket/messages", msg);

        }
      }
      this.state = ProcessState.Finished;
      msg.setProcessInfo(this.getInfo());
      this.simpMessagingTemplate.convertAndSend("/socket/messages", msg);
    }
    catch (final IOException e) {
      // e.printStackTrace();
      this.state = ProcessState.Error;
      this.errorList.add(e.getMessage());
    }

  }

  @Override
  public void start() {

    final Thread t = new Thread(this);
    t.start();
  }

  @Override
  public void stop() {

    if (this.pMainProcess != null) {
      this.pMainProcess.destroy();
    }

  }

  @Override
  public String getOutputPath() {

    return this.fileData.getOutputPath();
  }

  @Override
  public String getInputPath() {

    return this.fileData.getPath();
  }

  @Override
  public Process getProcess() {

    return this.pMainProcess;
  }

  @Override
  public ProcessState getState() {

    return this.state;
  }

  @Override
  public FileData getFileData() {

    return this.fileData;
  }

  @Override
  public String getProcessType() {

    return "MKV";
  }

  @Override
  public ProcessInfo getInfo() {

    final ProcessInfo info = new ProcessInfo(this);

    return info;
  }

  @Override
  public boolean isRunning() {

    return this.state == ProcessState.Running;
  }

  @Override
  public int getPercent() {

    return this.lastPercent;
  }

  @Override
  public String getPercentString() {

    return this.lastPercentString;
  }

  @Override
  public int getHashData() {

    return this.hashData;
  }

  @Override
  public void setHashData(final int hashData) {

    this.hashData = hashData;
  }

}
