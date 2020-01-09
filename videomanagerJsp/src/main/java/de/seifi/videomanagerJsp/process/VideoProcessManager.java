package de.seifi.videomanagerJsp.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VideoProcessManager {

  private final List<IVideoProcess> processList;

  public VideoProcessManager() {

    this.processList = new ArrayList<>();

  }

  public IVideoProcess add(final IVideoProcess videoProcess) {

    if (!this.processByOutputExists(videoProcess.getOutputPath())) {
      this.processList.add(videoProcess);
      return videoProcess;
    }
    return null;
  }

  public IVideoProcess removeByOutput(final String output) {

    final int idx = this.processIndexByOutput(output);
    if (idx > -1) {
      final IVideoProcess p = this.processList.get(idx);
      this.processList.remove(idx);
      return p;
    }
    return null;
  }

  public IVideoProcess processByOutput(final String output) {

    for (final IVideoProcess p : this.processList) {

      if (p.getOutputPath().toLowerCase().equals(output.toLowerCase())) {
        return p;
      }
    }
    return null;
  }

  public int processIndexByOutput(final String output) {

    for (int i = 0; i < this.processList.size(); i++) {
      final IVideoProcess p = this.processList.get(i);
      if (p.getOutputPath().toLowerCase().equals(output.toLowerCase())) {
        return i;
      }
    }
    return -1;
  }

  public boolean processByOutputExists(final String output) {

    return this.processByOutput(output) != null;
  }

  public boolean startProcessByOutput(final String output) {

    final IVideoProcess p = this.processByOutput(output);
    if (p != null) {
      p.start();
      return true;
    }
    return false;
  }

  public boolean stopProcessByOutput(final String output) {

    final IVideoProcess p = this.processByOutput(output);
    if (p != null) {
      p.stop();
      return true;
    }
    return false;
  }

  public int size() {

    return this.processList.size();
  }

  public IVideoProcess get(final int index) {

    return this.processList.get(index);
  }

}
