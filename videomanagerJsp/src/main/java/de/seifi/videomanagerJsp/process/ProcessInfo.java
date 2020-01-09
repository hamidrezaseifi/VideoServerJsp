package de.seifi.videomanagerJsp.process;

public class ProcessInfo {

  public String OutputFileName;
  public String OutputFilePath;
  public String Base64OutputFilePath;
  public String FolderPath;
  public String ProcessType;
  public String Status;
  public String PercentString;
  public int Percent;
  public boolean isRunning;
  public boolean isFinished;

  public ProcessInfo(final IVideoProcess proc) {

    this.OutputFileName = proc.getFileData().Name;
    this.OutputFilePath = proc.getOutputPath();
    this.Base64OutputFilePath = proc.getFileData().getBase64OutputFilePath();
    this.FolderPath = proc.getFileData().FolderPath;
    this.ProcessType = proc.getProcessType();
    this.Status = proc.getState().name();
    this.Percent = proc.getPercent();
    this.PercentString = proc.getPercentString();

    this.isRunning = proc.isRunning();
    this.isFinished = proc.getState() == ProcessState.Finished;

  }

}
