package de.seifi.videomanagerJsp.process;

public class ProcessInfo {

  private String outputFileName;
  private String outputFilePath;
  private String outputFilePathHash;
  private String folderPath;
  private String processType;
  private String status;
  private String percentString;
  private int percent;
  private boolean isRunning;
  private boolean isFinished;
  private int hash;

  public ProcessInfo(final IVideoProcess proc) {

    this.outputFileName = proc.getFileData().getName();
    this.outputFilePath = proc.getOutputPath();
    this.outputFilePathHash = proc.getFileData().getOutputFilePathHash();
    this.folderPath = proc.getFileData().getFolderPath();
    this.processType = proc.getProcessType();
    this.status = proc.getState().name();
    this.percent = proc.getPercent();
    this.percentString = proc.getPercentString();

    this.isRunning = proc.isRunning();
    this.isFinished = proc.getState() == ProcessState.Finished;
    this.hash = proc.getHashData();

  }

  public String getOutputFileName() {

    return this.outputFileName;
  }

  public void setOutputFileName(final String outputFileName) {

    this.outputFileName = outputFileName;
  }

  public String getOutputFilePath() {

    return this.outputFilePath;
  }

  public void setOutputFilePath(final String outputFilePath) {

    this.outputFilePath = outputFilePath;
  }

  public String getOutputFilePathHash() {

    return this.outputFilePathHash;
  }

  public void setOutputFilePathHash(final String outputFilePathHash) {

    this.outputFilePathHash = outputFilePathHash;
  }

  public String getFolderPath() {

    return this.folderPath;
  }

  public void setFolderPath(final String folderPath) {

    this.folderPath = folderPath;
  }

  public String getProcessType() {

    return this.processType;
  }

  public void setProcessType(final String processType) {

    this.processType = processType;
  }

  public String getStatus() {

    return this.status;
  }

  public void setStatus(final String status) {

    this.status = status;
  }

  public String getPercentString() {

    return this.percentString;
  }

  public void setPercentString(final String percentString) {

    this.percentString = percentString;
  }

  public int getPercent() {

    return this.percent;
  }

  public void setPercent(final int percent) {

    this.percent = percent;
  }

  public boolean isRunning() {

    return this.isRunning;
  }

  public void setRunning(final boolean isRunning) {

    this.isRunning = isRunning;
  }

  public boolean isFinished() {

    return this.isFinished;
  }

  public void setFinished(final boolean isFinished) {

    this.isFinished = isFinished;
  }

  public int getHash() {

    return this.hash;
  }

  public void setHash(final int hash) {

    this.hash = hash;
  }

}
