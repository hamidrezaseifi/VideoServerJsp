package de.seifi.videomanagerJsp.helper;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import de.seifi.videomanagerJsp.process.ProcessInfo;

public class GuiSocketMessage extends HashMap<String, Object> {

  private static final long serialVersionUID = 3425395554514027407L;

  public GuiSocketMessage() {

  }

  public GuiSocketMessage(final int initialCapacity) {

    super(initialCapacity);
  }

  public GuiSocketMessage(final Map<? extends String, ? extends String> m) {

    super(m);

  }

  public GuiSocketMessage(final int initialCapacity, final float loadFactor) {

    super(initialCapacity,loadFactor);
  }

  public boolean hasMessageReload() {

    return this.containsKey(ESocketCommands.MESSAGE_RELOAD.getValue());
  }

  public String getStatus() {

    return this.get(ESocketCommands.STATUS.getValue()).toString();
  }

  public void setStatus(final String status) {

    this.put(ESocketCommands.STATUS.getValue(), status);
  }

  public String getCommand() {

    return this.get(ESocketCommands.COMMAND.getValue()).toString();
  }

  public void setCommand(final String command) {

    this.put(ESocketCommands.COMMAND.getValue(), command);
  }

  public String getErrorMessage() {

    return this.get(ESocketCommands.ERROR_MESSAGE.getValue()).toString();
  }

  public void setErrorMessage(final String error) {

    this.put(ESocketCommands.ERROR_MESSAGE.getValue(), error);
  }

  public String getErrorDetail() {

    return this.get(ESocketCommands.ERROR_DETAIL.getValue()).toString();
  }

  public void setErrorDetail(final String error) {

    this.put(ESocketCommands.ERROR_DETAIL.getValue(), error);
  }

  public boolean hasProcessFileHash() {

    return this.containsKey(ESocketCommands.PROCESS_FILEHASH.getValue());
  }

  public boolean hasProcessHash() {

    return this.containsKey(ESocketCommands.PROCESS_HASHDATA.getValue());
  }

  public Integer getProcessHash() {

    return (Integer) this.get(ESocketCommands.PROCESS_HASHDATA.getValue());
  }

  public void setProcessHash(final Integer data) {

    this.put(ESocketCommands.PROCESS_HASHDATA.getValue(), data);
  }

  public String getProcessFileHash() {

    return this.get(ESocketCommands.PROCESS_FILEHASH.getValue()).toString();
  }

  public void setProcessFileHash(final String data) {

    this.put(ESocketCommands.PROCESS_FILEHASH.getValue(), data);
  }

  public int getProcessPercent() {

    return (Integer) this.get(ESocketCommands.PROCESS_PERCENT.getValue());
  }

  public void setProcessPercent(final int data) {

    this.put(ESocketCommands.PROCESS_PERCENT.getValue(), data);
  }

  public String getProcessPercentStr() {

    return this.get(ESocketCommands.PROCESS_PERCENTSTR.getValue()).toString();
  }

  public void setProcessPercentStr(final String data) {

    this.put(ESocketCommands.PROCESS_PERCENTSTR.getValue(), data);
  }

  public ProcessInfo getProcessInfo() {

    return (ProcessInfo) this.get(ESocketCommands.PROCESS_INFO.getValue());
  }

  public void setProcessInfo(final ProcessInfo data) {

    this.put(ESocketCommands.PROCESS_INFO.getValue(), data);
  }

  public static GuiSocketMessage generate(final String status) {

    final GuiSocketMessage message = new GuiSocketMessage();
    message.put(ESocketCommands.STATUS.getValue(), status);
    return message;
  }

  public static String decodeHashPath(final String encodedString) {

    final String decodedString = new String(Base64.getDecoder().decode(encodedString));

    return decodedString;
  }

  public static String encodeHashPath(final String filepath) {

    final String encodedString = Base64.getEncoder().encodeToString(filepath.getBytes());

    return encodedString;
  }

  @Override
  public GuiSocketMessage clone() {

    final GuiSocketMessage message = new GuiSocketMessage();
    for (final String key : this.keySet()) {
      message.put(key, this.get(key));
    }

    return message;

  }

}
