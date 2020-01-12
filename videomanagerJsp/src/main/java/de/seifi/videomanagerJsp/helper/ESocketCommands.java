package de.seifi.videomanagerJsp.helper;

public enum ESocketCommands {

  STATUS("status"),
  COMMAND("command"),
  ERROR_MESSAGE("errorMessage"),
  ERROR_DETAIL("errorDetail"),

  MESSAGE_RELOAD("message-reload"),

  PROCESS_HASHDATA("hash"),
  PROCESS_FILEHASH("filehash"),
  PROCESS_PERCENT("percent"),
  PROCESS_PERCENTSTR("percentstr"),
  PROCESS_INFO("info");

  private final String value;

  ESocketCommands(final String value) {

    this.value = value;
  }

  public String getValue() {

    return this.value;
  }

}
