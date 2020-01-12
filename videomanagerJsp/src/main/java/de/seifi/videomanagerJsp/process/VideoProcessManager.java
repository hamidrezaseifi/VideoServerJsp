package de.seifi.videomanagerJsp.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class VideoProcessManager {

  private final Map<Integer, IVideoProcess> processList;

  public VideoProcessManager() {

    this.processList = new HashMap<>();

  }

  public IVideoProcess add(final IVideoProcess videoProcess) {

    final int hash = videoProcess.hashCode();

    if (!this.processByHashDataExists(hash)) {

      videoProcess.setHashData(hash);

      this.processList.put(hash, videoProcess);
      return videoProcess;
    }
    return null;
  }

  public void removeByHashData(final int hash) {

    this.processList.remove(hash);

  }

  public IVideoProcess getByHashData(final int hash) {

    if (this.processList.containsKey(hash)) {
      return this.processList.get(hash);
    }

    return null;
  }

  public boolean processByHashDataExists(final int hash) {

    return this.getByHashData(hash) != null;
  }

  public int size() {

    return this.processList.size();
  }

  public IVideoProcess get(final int index) {

    return this.processList.get(index);
  }

  public List<ProcessInfo> getInfoList() {

    return this.processList.values().stream().map(p -> p.getInfo()).collect(Collectors.toList());
  }
}
