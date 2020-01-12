package de.seifi.videomanagerJsp.process;

import de.seifi.videomanagerJsp.helper.FileData;

public interface IVideoProcess {

  public FileData getFileData();

  public String getOutputPath();

  public String getInputPath();

  public Process getProcess();

  public ProcessState getState();

  public String getProcessType();

  public int getPercent();

  public String getPercentString();

  public ProcessInfo getInfo();

  public boolean isRunning();

  public void start();

  public void stop();

  public int getHashData();

  void setHashData(final int hashData);

}
