package de.seifi.videomanagerJsp.helper;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class FileData {

  private static String OutputExtentionPart = "_out";

  private final String path;
  private String outputPath;
  private final String pathHash;
  private final String folderPath;
  private String subtitlePath;
  private final String subtitlePathHash;
  private String subtitleUrl;
  private final String name;

  private boolean isInProcess;
  private boolean isInConvertProcess;
  private boolean isInSubtitleProcess;

  public String MkvMergPath;

  public static boolean isFileMedia(final String path) {

    final List<String> mediaExtentions = Arrays.asList(new String[] { "mpg", "mp4", "mkv", "avi" });
    final String extension = getFileExtention(path);

    return mediaExtentions.contains(extension);
  }

  public static boolean isFileSubtitle(final String path) {

    return getFileExtention(path).toLowerCase().equals("srt");
  }

  public static String getFileExtention(final String path) {

    String extension = "";

    final int i = path.lastIndexOf('.');
    if (i > 0) {
      extension = path.substring(i + 1);

    }

    return extension;
  }

  public FileData(final String path) {

    this.MkvMergPath = this.getClass().getClassLoader().getResource("static/mkv/mkvmerge.exe").getPath();

    final File file = new File(path);

    this.name = file.getName();
    this.folderPath = file.getParent();
    this.path = path;
    this.subtitlePath = "";
    final String extension = getFileExtention(path);
    if (extension.length() > 0) {
      this.subtitlePath = path.substring(0, path.length() - 4) + ".srt";

      this.outputPath = path.substring(0, path.length() - 4) + OutputExtentionPart + "." + extension;
    }

    byte[] bytesEncoded = Base64.getEncoder().encode(this.path.getBytes());
    this.pathHash = new String(bytesEncoded);

    bytesEncoded = Base64.getEncoder().encode(this.subtitlePath.getBytes());
    this.subtitlePathHash = new String(bytesEncoded);

    this.subtitleUrl = "";

    // final PathSubtitleModel subpath = this.pathSubtitlesHandler.getPathSubtitlesFromPath(this.FolderPath);

    // if (subpath != null) {
    // this.SubtitleUrl = subpath.getSuburl();
    // }

    this.isInProcess = false;
    this.isInConvertProcess = false;
    this.isInSubtitleProcess = false;
  }

  public String getOutputFilePathHash() {

    final byte[] bytesEncoded = Base64.getEncoder().encode(this.outputPath.getBytes());
    return new String(bytesEncoded);

  }

  public String getOutputPath() {

    return this.outputPath;
  }

  public void setOutputPath(final String outputPath) {

    this.outputPath = outputPath;
  }

  public String getSubtitlePath() {

    return this.subtitlePath;
  }

  public void setSubtitlePath(final String subtitlePath) {

    this.subtitlePath = subtitlePath;
  }

  public boolean isHasSubtitle() {

    return (new File(this.subtitlePath)).exists();
  }

  public String getMkvMergPath() {

    return this.MkvMergPath;
  }

  public void setMkvMergPath(final String mkvMergPath) {

    this.MkvMergPath = mkvMergPath;
  }

  public String getPath() {

    return this.path;
  }

  public String getBase64Path() {

    return this.pathHash;
  }

  public String getFolderPath() {

    return this.folderPath;
  }

  public String getBase64SubtitlePath() {

    return this.subtitlePathHash;
  }

  public String getSubtitleUrl() {

    return this.subtitleUrl;
  }

  public boolean getHasSubtitleUrl() {

    return this.subtitleUrl.equals("") == false;
  }

  public void setSubtitleUrl(final String subtitleUrl) {

    this.subtitleUrl = subtitleUrl;
  }

  public String getName() {

    return this.name;
  }

  public boolean isInProcess() {

    return this.isInProcess;
  }

  public void setInProcess(final boolean isInProcess) {

    this.isInProcess = isInProcess;
  }

  public boolean isInConvertProcess() {

    return this.isInConvertProcess;
  }

  public void setInConvertProcess(final boolean isInConvertProcess) {

    this.isInConvertProcess = isInConvertProcess;
  }

  public boolean isInSubtitleProcess() {

    return this.isInSubtitleProcess;
  }

  public void setInSubtitleProcess(final boolean isInSubtitleProcess) {

    this.isInSubtitleProcess = isInSubtitleProcess;
  }

  public boolean isMedia() {

    return isFileMedia(this.path);
  }

  public boolean isConverted() {

    return this.name.indexOf(OutputExtentionPart) > 0;
  }

  public boolean isHasConverted() {

    return new File(this.outputPath).exists();
  }

}
