package de.seifi.videomanagerJsp.helper;

public class SubtitleRequest {

  private String file;

  private String language;

  private String command;

  public String getFile() {

    return this.file;
  }

  public void setFile(final String file) {

    this.file = file;
  }

  public String getLanguage() {

    return this.language;
  }

  public void setLanguage(final String language) {

    this.language = language;
  }

  public String getCommand() {

    return this.command;
  }

  public void setCommand(final String command) {

    this.command = command;
  }

  public boolean isCommandStart() {

    return this.command != null && this.command.equals("start");
  }

  public boolean isCommandStop() {

    return this.command != null && this.command.equals("stop");
  }

  public boolean isCommandStatus() {

    return this.command != null && this.command.equals("status");
  }

  public boolean isCommandDelete() {

    return this.command != null && this.command.equals("delete");
  }

}
